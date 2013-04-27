package org.EzAz.Layer3;

/**
 * Copyright 2012-2013 Felix Gaehtgens
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import org.EzAz.AdviceObligationHandlers.AdviceObligationHandler;
import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.ResponseSetter;
import org.EzAz.Layer2.Result;
import org.EzAz.Layer2.abstractMap;
import org.EzAz.Layer2.abstractSet;

/**
 * @author felix
 * @version 1.0
 * @created 05-Dec-2012 00:07:44
 */
public class PDPserviceFactory {

    private static final String PROP_LAYER2 = "org.EzAz.layer2Implementation";
    private static final String PROP_LAYER2_DEFAULT = "org.EzAz.generic.Layer2";
    static String PREFIX = "org.EzAz.";
    public static String PREFIX_PDP = "org.EzAz.pdp.";
    public static String PREFIX_HANDLER = "org.EzAz.handler.";
    public static String POSTFIX_PRECHAIN_HANDLER = ".prechain.handler";
    public static String POSTFIX_POSTCHAIN_HANDLER = ".postchain.handler";
    static Class<Request> requestClass = null;
    static Class<Result> resultClass = null;
    static Class<Response> responseClass = null;
    static Class<Attribute> attributeClass = null;
    private static org.EzAz.Layer2.layer2Bootstrapper layer2BootStrapper;
    private static abstractMap<String, PDPService> pdps = new abstractMap<String, PDPService>();
    private static abstractMap<String, AdviceObligationHandler> handlers = new abstractMap<String, AdviceObligationHandler>();

    public PDPserviceFactory() {

    }

    /**
     * 
     * @exception Throwable
     *                Throwable
     */
    public void finalize() throws Throwable {

    }

    /**
     * 
     * @param instance
     *            Name of the PDP instance. This instance must be defined in the
     *            configuration file.
     */
    public static PDPService getPDP(String instance) {
	if (!pdps.containsKey(instance))
	    throw new RuntimeException("Instance " + instance + " is not defined, or not available!");
	return pdps.get(instance);
    }

    /**
     * Creates an empty new Request object. This function will create an object
     * of the Request interface of the loaded Layer 2 library.
     * 
     * TODO: Make it create either XACML 2 or 3. We need an input parameter
     * here!!!
     */
    public static Request newRequest() {
	try {
	    return requestClass.newInstance();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
    }

    /**
     * Creates a Layer 2 object.
     */
    public Object create(Object c) {
	if (layer2BootStrapper == null) {
	    throw new RuntimeException("Cannot call create() before initialization of Layer 2!");
	}
	try {
	    return layer2BootStrapper.create(c);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    /**
     * Creates an empty new Response object. This function will create an object
     * of the Response interface of the loaded Layer 2 library.
     */
    public Response newResponse() {
	try {
	    return responseClass.newInstance();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
    }

    /**
     * Creates an empty new Response object. This function will create an object
     * of the Response interface of the loaded Layer 2 library, and add the
     * single Result that is passed in as a parameter.
     * 
     * @param result
     *            result
     */
    public Response newResponse(Result result) {
	Response rr = newResponse();
	ResponseSetter rrs = (ResponseSetter) rr;
	rrs.addResult(result);
	return rr;
    }

    /**
     * Creates an empty new Response object. This function will create an object
     * of the Response interface of the loaded Layer 2 library, and add the
     * Results that are passed in as a parameter.
     * 
     * @param results
     *            results
     */
    public Response newResponse(abstractSet<Result> results) {
	Response rr = newResponse();
	ResponseSetter rrs = (ResponseSetter) rr;
	rrs.addResults(results);
	return rr;
    }

    /**
     * Creates an empty new Result object. This function will create an object
     * of the Result interface of the loaded Layer 2 library.
     */
    public Result newResult() {
	try {
	    return resultClass.newInstance();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
    }

    public static abstractMap<String, Properties> splitProperties(Properties prop, String prefix) {
	abstractMap<String, Properties> propMap = new abstractMap<String, Properties>();
	Set<Object> keySet = prop.keySet();
	Iterator<Object> it = keySet.iterator();
	while (it.hasNext()) {
	    String propName = it.next().toString();
	    if (propName.regionMatches(true, 0, prefix, 0, prefix.length())) {
		// grab name from the keys.
		String s_tmp = propName.substring(prefix.length() + 1);
		String s = s_tmp.substring(0, s_tmp.indexOf("."));
		Properties pdpProp;
		if (!propMap.containsKey(s)) {
		    pdpProp = new Properties();
		    propMap.put(s, pdpProp);
		} else {
		    pdpProp = propMap.get(s);
		}
		// copy this property to the PDP property
		pdpProp.put(propName, prop.get(propName));
		it.remove(); // Remove this from the properties
	    }
	}
	return propMap;
    }

    /**
     * Initializes the SDK.
     * 
     * @param properties
     *            The properties with which to initialize the SDK.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void initSDK(Properties properties) {

	// Handle SDK specific properties
	String l2Impl = properties.getProperty(PROP_LAYER2, PROP_LAYER2_DEFAULT);

	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	// ClassLoader classLoader =
	// PDPserviceFactory.class.getClassLoader().getSystemClassLoader();
	try {
	    Class bootstrap = (Class<Request>) classLoader.loadClass(l2Impl + ".layer2Bootstrapper");
	    layer2BootStrapper = (org.EzAz.Layer2.layer2Bootstrapper) bootstrap.newInstance();
	    attributeClass = layer2BootStrapper.classAttribute();
	    requestClass = layer2BootStrapper.classRequest();
	    responseClass = layer2BootStrapper.classResponse();
	    resultClass = layer2BootStrapper.classResult();
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InstantiationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	abstractMap<String, Properties> pdpMap = splitProperties(properties, "org.EzAz.pdp");
	abstractMap<String, Properties> handlerMap = splitProperties(properties, "org.EzAz.handler");

	// Init all handlers
	// and also print out all handler definitions
	for (String s : handlerMap.keySet()) {
	    Properties pp = handlerMap.get(s);
	    System.out.println("PROPERTIES FOR HANDLER DRIVER: " + s + "\n" + pp.toString());
	    // Find the class of the driver
	    String driverClassProp = PREFIX_HANDLER + s + ".class";
	    System.out.println("Fetching handler driver: " + driverClassProp);
	    String driverClass = pp.getProperty(driverClassProp);
	    if (driverClass != null) {
		Class handlerClass;
		try {
		    handlerClass = (Class<AdviceObligationHandler>) classLoader.loadClass(driverClass);
		    org.EzAz.AdviceObligationHandlers.AdviceObligationHandler handler = (org.EzAz.AdviceObligationHandlers.AdviceObligationHandler) handlerClass
			    .newInstance();
		    handler.init(pp);
		    handlers.put(s, handler);
		} catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (InstantiationException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }

	}
	// Init all PDPs
	// and also Print out all the PDP definitions
	for (String s : pdpMap.keySet()) {
	    Properties pp = pdpMap.get(s);
	    System.out.println("PROPERTIES FOR PDP DRIVER: " + s + "\n" + pp.toString());
	    // Find the class of the driver
	    String driverClassProp = PREFIX_PDP + s + ".driver";
	    System.out.println("Fetching PDP driver: " + driverClassProp);
	    String driverClass = pp.getProperty(driverClassProp);
	    if (driverClass != null) {
		Class pdp;
		try {
		    pdp = (Class<PDPService>) classLoader.loadClass(driverClass);
		    org.EzAz.Layer3.PDPService pdpService = (org.EzAz.Layer3.PDPService) pdp.newInstance();
		    // Load handler chains
		    String preChainHandlers = (String) pp.get(PREFIX_PDP + s + ".handlers.prechain");
		    System.out.println("PRE CHAIN HANDLERS LIST: " + preChainHandlers);
		    if (preChainHandlers != null) {
			String[] handlerNames = preChainHandlers.split(",\\s*");
			for (int x = 0; x < handlerNames.length; x++) {
			    System.out.println("GOT HANDLER: " + handlerNames[x]);
			    AdviceObligationHandler handler = handlers.get(handlerNames[x]);
			    if (handler == null) {
				System.out.println("ERROR: Cannot find handler " + handlerNames[x]);
			    } else {
				pdpService.preChain.add(handler);
			    }
			}
		    }
		    String postChainHandlers = (String) pp.get(PREFIX_PDP + s + ".handlers.postchain");
		    System.out.println("POST CHAIN HANDLERS LIST: " + postChainHandlers);
		    if (postChainHandlers != null) {
			String[] handlerNames = postChainHandlers.split(",\\s*");
			for (int x = 0; x < handlerNames.length; x++) {
			    System.out.println("GOT POST HANDLER: [" + handlerNames[x] + "]");
			    AdviceObligationHandler handler = handlers.get(handlerNames[x]);
			    if (handler == null) {
				System.out.println("ERROR: Cannot find handler " + handlerNames[x]);
			    } else {
				pdpService.postChain.add(handler);
			    }
			}
		    }

		    pdpService.setupConnection(pp);
		    pdps.put(s, pdpService);
		} catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (InstantiationException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
    }

}