package org.EzAz.Layer3Driver.Axiomatics;

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
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributeAssignmentType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributeType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributeValueType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributesType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.ContentType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.DecisionType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.ObligationType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.ObligationsType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.RequestType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.ResponseType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.ResultType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.StatusCodeType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.StatusDetailType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.StatusType;

import org.EzAz.AdviceObligationHandlers.AdviceObligationHandler;
import org.EzAz.Layer2.AdviceObligation;
import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.AttributeAssignment;
import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.abstractMap;
import org.EzAz.Layer2.abstractSet;
import org.EzAz.Layer3.PDPserviceFactory;
import org.EzAz.Layer3Driver.AsyncEmulator.AsyncEmulator;
import org.EzAz.generic.Layer2.genericAdviceObligation;
import org.EzAz.generic.Layer2.genericAttribute;
import org.EzAz.generic.Layer2.genericAttributeAssignment;
import org.EzAz.generic.Layer2.genericAttributeEntity;
import org.EzAz.generic.Layer2.genericLayer2RuntimeException;
import org.EzAz.generic.Layer2.genericResponse;
import org.EzAz.generic.Layer2.genericResult;
import org.EzAz.generic.Layer2.genericStatus;

import com.axiomatics.delegent.pdpsimple.v1.DelegentPDP;
import com.axiomatics.delegent.pdpsimple.v1.DelegentPDPPortType;

public class AxiomaticsDriver extends AsyncEmulator {

    public AxiomaticsDriver() {

    }

    @Override
    public Response evaluate(Request request) throws Exception {
	request.setImmutable();
	RequestType axioRequest = createAxioRequest(request);
	ResponseType axioResp = sendAxioRequest(axioRequest);
	// ResponseType axioResp = readFile();
	Response ezAzResponse = generateEzAzResponse(axioResp);
	return ezAzResponse;
	// return shuffle (request);
	// genericResponse resp=new genericResponse();
	// genericResult result=new genericResult();
	// result.setDecision(Result.RESULT_PERMIT);
	// resp.addResult(result);
    }

    // public ResponseType readFile() {
    // try {
    // InputStream xmlFile = this
    // .getClass()
    // .getClassLoader()
    // .getResourceAsStream(
    // "org/EzAz/examples/simpleRequest/obligations.xml");
    // // JAXBContext jaxbContext =
    // // JAXBContext.newInstance(ResponseType.class);
    // // Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    // // ResponseType resp = (ResponseType)
    // // jaxbUnmarshaller.unmarshal(xmlFile);
    // JAXBContext jaxbContext = JAXBContext
    // .newInstance(InstanceAccessQuery3Response.class);
    // Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    // InstanceAccessQuery3Response resp = (InstanceAccessQuery3Response)
    // jaxbUnmarshaller
    // .unmarshal(xmlFile);
    // System.out.println(resp);
    // return resp.getResponse();
    // } catch (JAXBException e) {
    // e.printStackTrace();
    // }
    // System.exit(0);
    // return null;
    // }

    private RequestType createAxioRequest(Request request) throws Exception {
	// ObjectFactory of = new ObjectFactory();
	RequestType req = new RequestType();

	// Look at the request categories
	abstractMap<Identifier, AttributeEntity> entities = request.getCategoriesEntities();
	for (Identifier cat : entities.keySet()) {
	    // Create Axio cat
	    AttributesType atList = new AttributesType();
	    atList.setCategory(cat.toString());
	    // Fetch attributes
	    AttributeEntity entity = entities.get(cat);
	    abstractSet<Attribute> attributes = entity.getAttributes();
	    for (Attribute at : attributes) {
		// Create Axio attribute
		AttributeType axioAt = new AttributeType();
		axioAt.setAttributeId(at.getId().toString());
		axioAt.setIssuer(at.getIssuer());
		axioAt.setIncludeInResult(at.getIncludeInResult());
		AttributeValueType avt = new AttributeValueType();
		avt.setDataType(at.getType().toString());
		avt.getContent().add(at.getValue().toString());
		axioAt.getAttributeValue().add(avt);
		atList.getAttribute().add(axioAt);
	    }

	    // Add Axio cat to Axio req
	    req.getAttributes().add(atList);
	}
	return req;
    }

    private ResponseType sendAxioRequest(RequestType axioReq) throws Exception {
	DelegentPDP e = new DelegentPDP();
	DelegentPDPPortType port = e.getDelegentPDPPort();
	BindingProvider bindingProvider = (BindingProvider) port;
	Map<String,Object> requestContext = bindingProvider.getRequestContext();
	requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
	requestContext.put(BindingProvider.USERNAME_PROPERTY, userName);
	requestContext.put(BindingProvider.PASSWORD_PROPERTY, passWord);

	ResponseType resp = port.instanceAccessQuery3(instanceName, axioReq);
	return resp;
    }

    private org.EzAz.Layer2.Response generateEzAzResponse(ResponseType axioResp) {
	// create EzAz Response
	Response EzAzResp = new genericResponse();

	// evaluate response
	List<ResultType> axioResults = axioResp.getResult();

	// Iterate through results
	for (ResultType AxioRes : axioResults) {
	    // Create new EzAz Result
	    genericResult ezAzRes = new genericResult();
	    // Set Decision
	    DecisionType axioDecision = AxioRes.getDecision();
	    if (axioDecision.compareTo(DecisionType.PERMIT) == 0)
		ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_PERMIT);
	    else if (axioDecision.compareTo(DecisionType.DENY) == 0)
		ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_DENY);
	    else if (axioDecision.compareTo(DecisionType.NOT_APPLICABLE) == 0)
		ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_NOT_APPLICABLE);
	    else if (axioDecision.compareTo(DecisionType.INDETERMINATE) == 0)
		ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_INDETERMINATE);
	    // Set Status
	    StatusType axioStatus = AxioRes.getStatus();
	    genericStatus ezAzStatus = new genericStatus();
	    StatusCodeType AxioStatusCode = axioStatus.getStatusCode();
	    if (AxioStatusCode != null)
		ezAzStatus.setStatusCode(AxioStatusCode.getValue());
	    StatusDetailType AxioStatusDetail = axioStatus.getStatusDetail();
	    if (AxioStatusDetail != null)
		ezAzStatus.setStatusDetail(AxioStatusDetail.toString());
	    String axioStatusMessage = axioStatus.getStatusMessage();
	    if (axioStatusMessage != null)
		ezAzStatus.setStatusMessage(axioStatusMessage.toString());
	    // Set Status to EzAz Result
	    ezAzRes.setStatus(ezAzStatus);
	    // TODO: Set Advice
	    // TODO: Set Obligations
	    ObligationsType axioObligationObject = AxioRes.getObligations();
	    if (axioObligationObject != null) {
		ezAzRes.setObligations(new abstractSet<AdviceObligation>());
		List<ObligationType> axioObligations = axioObligationObject.getObligation();
		for (ObligationType axioObligation : axioObligations) {
		    genericAdviceObligation ezAzObl = new genericAdviceObligation();
		    ezAzObl.setId(Identifier.create(axioObligation.getObligationId()));
		    List<AttributeAssignmentType> axioAttrAssignList = axioObligation.getAttributeAssignment();
		    if (axioAttrAssignList != null) {
			for (AttributeAssignmentType axioAssign : axioAttrAssignList) {
			    AttributeAssignment ezAzAssign = convertAxioAttributeAssignment(axioAssign);
			    ezAzObl.getAttributeAssignments().add(ezAzAssign);
			}
		    }
		    ezAzRes.getObligations().add(ezAzObl);
		}
	    }
	    // TODO: Set PolicyIdList
	    // TODO: Set AttributeEntities
	    List<AttributesType> AxioAttrEntities = AxioRes.getAttributes();
	    for (AttributesType AxioAttrEntity : AxioAttrEntities) {
		genericAttributeEntity ezAzEntity = new genericAttributeEntity();
		ezAzEntity.setCategory(Identifier.create(AxioAttrEntity.getCategory()));
		abstractMap<Identifier, AttributeEntity> ezAzAttributeEntities = ezAzRes.getCategoriesEntities();
		if (ezAzAttributeEntities == null) {
		    ezAzAttributeEntities = new abstractMap<Identifier, AttributeEntity>();
		    ezAzRes.setCategoriesEntities(ezAzAttributeEntities);
		}
		ezAzAttributeEntities.put(ezAzEntity.getCategory(), ezAzEntity);
		ContentType content = AxioAttrEntity.getContent();
		if (content != null)
		    ezAzEntity.setContent(content.getContent());
		if (AxioAttrEntity.getId() != null)
		    System.out.println("----TODO---- AxioAttrEntity.getId()=" + AxioAttrEntity.getId().toString());
		// Attributes within the entity
		List<AttributeType> AxioAttributes = AxioAttrEntity.getAttribute();
		for (AttributeType AxioAttr : AxioAttributes) {
		    Attribute ezAzAttr = convertAxioAttr(AxioAttr);
		    abstractSet<Attribute> attributes = ezAzEntity.getAttributes();
		    if (attributes == null) {
			attributes = new abstractSet<Attribute>();
			ezAzEntity.setAttributes(attributes);
		    }
		    attributes.add(ezAzAttr);
		}
	    }
	    // Add EzAz Result to Response
	    EzAzResp.getResults().add(ezAzRes);
	}
	return EzAzResp;
    }

    private AttributeAssignment convertAxioAttributeAssignment(AttributeAssignmentType axioAssign) {
	genericAttributeAssignment ezAzAssign = new genericAttributeAssignment();
	ezAzAssign.setCategory(Identifier.create(axioAssign.getCategory()));
	genericAttribute ezAzAttr = new genericAttribute();
	ezAzAttr.setId(Identifier.create(axioAssign.getAttributeId()));
	ezAzAttr.setIssuer(axioAssign.getIssuer());
	ezAzAttr.setType(Identifier.create(axioAssign.getDataType()));
	abstractSet<Object> avs = new abstractSet<Object>();
	for (Object o : axioAssign.getContent()) {
	    avs.add(o);
	}
	if (avs.size() > 1) {
	    ezAzAttr.setValue(avs);
	} else {
	    // Set the first object directly
	    ezAzAttr.setValue(avs.iterator().next());
	}
	ezAzAssign.setAttribute(ezAzAttr);
	return ezAzAssign;
    }

    private Attribute convertAxioAttr(AttributeType AxioAttr) {
	genericAttribute ezAzAttr = new genericAttribute();
	ezAzAttr.setId(Identifier.create(AxioAttr.getAttributeId()));
	ezAzAttr.setIssuer(AxioAttr.getIssuer());
	ezAzAttr.setIncludeInResult(AxioAttr.isIncludeInResult());
	// Attribute values
	List<AttributeValueType> AxioAttrVals = AxioAttr.getAttributeValue();
	if (AxioAttrVals != null) {
	    abstractSet<Object> avs = new abstractSet<Object>();
	    for (AttributeValueType AxioAV : AxioAttrVals) {
		ezAzAttr.setType(Identifier.create(AxioAV.getDataType()));
		for (Object o : AxioAV.getContent()) {
		    avs.add(o);
		}
	    }
	    if (avs.size() > 1) {
		ezAzAttr.setValue(avs);
	    } else {
		// Set the first object directly
		ezAzAttr.setValue(avs.iterator().next());
	    }
	}
	return ezAzAttr;
    }

    @Override
    public Response evaluate(Request request, Object appContext) throws Exception {
	return evaluate(request);
    }

    @Override
    public Response extendedOperation(String operation, Request request, Object extendedContext) throws Exception {
	throw new genericLayer2RuntimeException("AxiomaticsDriver does not implement extendedOperation!");
    }

    @Override
    public Response extendedOperation(String operation, Request request, Object extendedContext, Object appContext)
	    throws Exception {
	throw new genericLayer2RuntimeException("AxiomaticsDriver does not implement extendedOperation!");
    }

    @Override
    public Response evaluate(Request request, Object appContext,
	    abstractSet<AdviceObligationHandler> obligationHandlers,
	    abstractSet<AdviceObligationHandler> adviceListeners) throws Exception {
	return evaluate(request);
    }

    @Override
    public Response extendedOperation(String operation, Request request, Object extendedContext, Object appContext,
	    abstractSet<AdviceObligationHandler> adviceHandlers, abstractSet<AdviceObligationHandler> obligationHandlers)
	    throws Exception {
	throw new genericLayer2RuntimeException("AxiomaticsDriver does not implement extendedOperation!");
    }
    
    private static String PROP_INSTANCE_NAME="instance";
    private static String PROP_ENDPOINT="endpoint";
    private static String PROP_USERNAME="username";
    private static String PROP_PASSWORD="password";
    private String instanceName;
    private String endPoint;
    private String userName;
    private String passWord;

    @Override
    public void setupConnection(Properties properties) {
	// find out the name of the PDP in the properties
	// grab PDP name from the keys.
	//
	// Just grab the first one, and then try to derive the PDP name and prefix
	String s = properties.keySet().toArray(new String[] {})[0];
	String pdpName_tmp = s.substring(PDPserviceFactory.PREFIX_PDP.length());
	String pdpName = pdpName_tmp.substring(0, pdpName_tmp.indexOf("."));
	System.out.println("Found PDP Name: " + pdpName);
	String prefix=PDPserviceFactory.PREFIX_PDP+pdpName;
	System.out.println("Found Prefix for these properties: "+prefix);
	// Check for instance name
	String p=prefix+"."+PROP_INSTANCE_NAME;
	instanceName = properties.getProperty(p);
	if (instanceName == null || instanceName.length()==0) {
	    throw new RuntimeException("AxiomaticsSOAPdriver: property "+p+" cannot be empty!");
	}
	// Check for endpoint address
	p=prefix+"."+PROP_ENDPOINT;
	endPoint = properties.getProperty(p);
	if (endPoint == null || endPoint.length()==0) {
	    throw new RuntimeException("AxiomaticsSOAPdriver: property "+p+" cannot be empty!");
	}
	// Check for user name and password
	p=prefix+"."+PROP_USERNAME;
	userName = properties.getProperty(p);
	p=prefix+"."+PROP_PASSWORD;
	passWord = properties.getProperty(p);
    }

}
