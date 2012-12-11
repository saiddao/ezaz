package org.EzAz.Layer3;

/**
 * Copyright 2012 Felix Gaehtgens
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
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.Result;
import java.util.Properties;
import org.EzAz.Layer2.abstractSet;
import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.ResponseSetter;
import org.EzAz.Layer2.abstractMap;
import org.EzAz.Layer3.PDPService;

/**
 * @author felix
 * @version 1.0
 * @created 05-Dec-2012 00:07:44
 */
public class PDPserviceFactory {

	private static final String PROP_LAYER2 = "org.EzAz.layer2Implementation";
	private static final String PROP_LAYER2_DEFAULT = "org.EzAz.generic.Layer2";
	static String PREFIX = "org.EzAz.";
	static String PREFIX_PDP = "org.EzAz.pdp.";
	static Class<Request> requestClass = null;
	static Class<Result> resultClass = null;
	static Class<Response> responseClass = null;
	static Class<Attribute> attributeClass = null;
	private static org.EzAz.Layer2.layer2Bootstrapper layer2BootStrapper;
	private static abstractMap<String, PDPService> pdps = new abstractMap<String, PDPService>();

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
			throw new RuntimeException("Instance " + instance
					+ " is not defined, or not available!");
		return pdps.get(instance);
	}

	/**
	 * Creates an empty new Request object. This function will create an object
	 * of the Request interface of the loaded Layer 2 library.
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
			Class bootstrap = (Class<Request>) classLoader.loadClass(l2Impl
					+ ".layer2Bootstrapper");
			layer2BootStrapper = (org.EzAz.Layer2.layer2Bootstrapper) bootstrap
					.newInstance();
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

		abstractMap<String, Properties> mm = new abstractMap<String, Properties>();

		for (String s : properties.stringPropertyNames()) {
			// Check for PDP definitions.
			// For every PDP definition, we create a new Properties object
			// and copy all of the PDP's properties to this Properties object.
			if (s.regionMatches(true, 0, PREFIX_PDP, 0, PREFIX_PDP.length())) {
				// grab PDP name from the keys.
				String pdpName_tmp = s.substring(PREFIX_PDP.length());
				String pdpName = pdpName_tmp.substring(0,
						pdpName_tmp.indexOf("."));
				// System.out.println("Found PDP Name: " + pdpName);
				Properties pdpProp;
				if (!mm.containsKey(pdpName)) {
					pdpProp = new Properties();
					mm.put(pdpName, pdpProp);
				} else {
					pdpProp = mm.get(pdpName);
				}
				// copy this property to the PDP property
				pdpProp.put(s, properties.get(s));
			}
		}
		// Init all PDPs
		// and also Print out all the PDP definitions
		for (String s : mm.keySet()) {
			Properties pp = mm.get(s);
			System.out.println("PROPERTIES FOR: " + s + "\n" + pp.toString());
			// Find the class of the driver
			String driverClassProp = PREFIX_PDP + s + ".driver";
			System.out.println("Fetching driver: " + driverClassProp);
			String driverClass = pp.getProperty(driverClassProp);
			if (driverClass != null) {
				Class pdp;
				try {
					pdp = (Class<PDPService>) classLoader
							.loadClass(driverClass);
					org.EzAz.Layer3.PDPService pdpService = (org.EzAz.Layer3.PDPService) pdp
							.newInstance();
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