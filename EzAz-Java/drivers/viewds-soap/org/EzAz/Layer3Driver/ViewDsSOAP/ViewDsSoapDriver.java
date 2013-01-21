package org.EzAz.Layer3Driver.ViewDsSOAP;

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
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import oasis.names.tc.saml._2_0.assertion.StatementAbstractType;
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
import oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.assertion.wd_13.XACMLAuthzDecisionStatementType;
import oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.protocol.wd_13.XACMLAuthzDecisionQueryType;

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

import com.viewds.wsdl.xacmlv3wd17pdp.AuthorizationDecisionPort;
import com.viewds.wsdl.xacmlv3wd17pdp.AuthorizationDecisionService;

public class ViewDsSoapDriver extends AsyncEmulator {

    public ViewDsSoapDriver() {

    }

    @Override
    public Response evaluate(Request request) throws Exception {
	request.setImmutable();
	RequestType viewDsRequest = createViewDsRequest(request);
	ResponseType viewDsResp = sendViewDsRequest(viewDsRequest);
	Response ezAzResponse = generateEzAzResponse(viewDsResp);
	return ezAzResponse;

    }

    private RequestType createViewDsRequest(Request request) throws Exception {
	// ObjectFactory of = new ObjectFactory();
	RequestType req = new RequestType();

	// Look at the request categories
	abstractMap<Identifier, AttributeEntity> entities = request.getCategoriesEntities();
	for (Identifier cat : entities.keySet()) {
	    // Create ViewDS cat
	    AttributesType atList = new AttributesType();
	    atList.setCategory(cat.toString());
	    // Fetch attributes
	    AttributeEntity entity = entities.get(cat);
	    abstractSet<Attribute> attributes = entity.getAttributes();
	    for (Attribute at : attributes) {
		// Create ViewDS attribute
		AttributeType viewDsAt = new AttributeType();
		viewDsAt.setAttributeId(at.getId().toString());
		viewDsAt.setIssuer(at.getIssuer());
		viewDsAt.setIncludeInResult(at.getIncludeInResult());
		AttributeValueType avt = new AttributeValueType();
		avt.setDataType(at.getType().toString());
		avt.getContent().add(at.getValue().toString());
		viewDsAt.getAttributeValue().add(avt);
		atList.getAttribute().add(viewDsAt);
	    }

	    // Add ViewDs cat to ViewDs req
	    req.getAttributes().add(atList);
	}
	return req;
    }

    private ResponseType sendViewDsRequest(RequestType oasisXacmlReq) throws Exception {
	AuthorizationDecisionService e = new AuthorizationDecisionService();
	AuthorizationDecisionPort port = e.getAuthorizationDecisionPort();
	BindingProvider bindingProvider = (BindingProvider) port;
	Map<String, Object> requestContext = bindingProvider.getRequestContext();
	requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
	requestContext.put(BindingProvider.USERNAME_PROPERTY, userName);
	requestContext.put(BindingProvider.PASSWORD_PROPERTY, passWord);

	XACMLAuthzDecisionQueryType query = new XACMLAuthzDecisionQueryType();
	query.setID("XACMLClient");
	query.setVersion("2.0");
	DatatypeFactory df = DatatypeFactory.newInstance();
	XMLGregorianCalendar now = df.newXMLGregorianCalendar(new GregorianCalendar());
	query.setIssueInstant(now);

	query.setRequest(oasisXacmlReq);

	oasis.names.tc.saml._2_0.protocol.ResponseType resp = port.getAuthorizationDecision(query);
	ArrayList<ResponseType> allResponses=new ArrayList<ResponseType>();
	List<Object> assertions = resp.getAssertionOrEncryptedAssertion();
	for (Object oo : assertions) {
	    System.out.println("Got back assertion type: " + oo);
	    if (oo instanceof oasis.names.tc.saml._2_0.assertion.AssertionType) {
		oasis.names.tc.saml._2_0.assertion.AssertionType assertion =
			(oasis.names.tc.saml._2_0.assertion.AssertionType) oo;
		List<StatementAbstractType> list = assertion.getStatementOrAuthnStatementOrAuthzDecisionStatement();
		for (Object ol: list) {
		    System.out.println("statement type: "+ol.toString());
		    if (ol instanceof XACMLAuthzDecisionStatementType) {
			XACMLAuthzDecisionStatementType st=(XACMLAuthzDecisionStatementType) ol;
			ResponseType response = st.getResponse();
			allResponses.add(response);
		    }
		}
	    }
	}
	if (allResponses.size() != 1) {
	    throw new RuntimeException ("Did not get exactly one response for AuthZRequest");
	} else 
	    return allResponses.get(0);
    }

    private org.EzAz.Layer2.Response generateEzAzResponse(ResponseType oasisXacmlRes) {
	// create EzAz Response
	Response EzAzResp = new genericResponse();

	// evaluate response
	List<ResultType> viewDsResults = oasisXacmlRes.getResult();

	// Iterate through results
	for (ResultType viewDsRes : viewDsResults) {
	    // Create new EzAz Result
	    genericResult ezAzRes = new genericResult();
	    // Set Decision
	    DecisionType viewDsDecision = viewDsRes.getDecision();
	    if (viewDsDecision.compareTo(DecisionType.PERMIT) == 0)
		ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_PERMIT);
	    else if (viewDsDecision.compareTo(DecisionType.DENY) == 0)
		ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_DENY);
	    else if (viewDsDecision.compareTo(DecisionType.NOT_APPLICABLE) == 0)
		ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_NOT_APPLICABLE);
	    else if (viewDsDecision.compareTo(DecisionType.INDETERMINATE) == 0)
		ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_INDETERMINATE);
	    // Set Status
	    StatusType viewDsStatus = viewDsRes.getStatus();
	    genericStatus ezAzStatus = new genericStatus();
	    StatusCodeType viewDsStatusCode = viewDsStatus.getStatusCode();
	    if (viewDsStatusCode != null)
		ezAzStatus.setStatusCode(viewDsStatusCode.getValue());
	    StatusDetailType viewDsStatusDetail = viewDsStatus.getStatusDetail();
	    if (viewDsStatusDetail != null)
		ezAzStatus.setStatusDetail(viewDsStatusDetail.toString());
	    String viewDsStatusMessage = viewDsStatus.getStatusMessage();
	    if (viewDsStatusMessage != null)
		ezAzStatus.setStatusMessage(viewDsStatusMessage.toString());
	    // Set Status to EzAz Result
	    ezAzRes.setStatus(ezAzStatus);
	    // TODO: Set Advice
	    // TODO: Set Obligations
	    ObligationsType viewDsObligationObject = viewDsRes.getObligations();
	    if (viewDsObligationObject != null) {
		ezAzRes.setObligations(new abstractSet<AdviceObligation>());
		List<ObligationType> viewDsObligations = viewDsObligationObject.getObligation();
		for (ObligationType viewDsObligation : viewDsObligations) {
		    genericAdviceObligation ezAzObl = new genericAdviceObligation();
		    ezAzObl.setId(Identifier.create(viewDsObligation.getObligationId()));
		    List<AttributeAssignmentType> viewDsAttrAssignList = viewDsObligation.getAttributeAssignment();
		    if (viewDsAttrAssignList != null) {
			for (AttributeAssignmentType viewDsAssign : viewDsAttrAssignList) {
			    AttributeAssignment ezAzAssign = convertViewDsAttributeAssignment(viewDsAssign);
			    ezAzObl.getAttributeAssignments().add(ezAzAssign);
			}
		    }
		    ezAzRes.getObligations().add(ezAzObl);
		}
	    }
	    // TODO: Set PolicyIdList
	    // TODO: Set AttributeEntities
	    List<AttributesType> viewDsAttrEntities = viewDsRes.getAttributes();
	    for (AttributesType viewDsAttrEntity : viewDsAttrEntities) {
		genericAttributeEntity ezAzEntity = new genericAttributeEntity();
		ezAzEntity.setCategory(Identifier.create(viewDsAttrEntity.getCategory()));
		abstractMap<Identifier, AttributeEntity> ezAzAttributeEntities = ezAzRes.getCategoriesEntities();
		if (ezAzAttributeEntities == null) {
		    ezAzAttributeEntities = new abstractMap<Identifier, AttributeEntity>();
		    ezAzRes.setCategoriesEntities(ezAzAttributeEntities);
		}
		ezAzAttributeEntities.put(ezAzEntity.getCategory(), ezAzEntity);
		ContentType content = viewDsAttrEntity.getContent();
		if (content != null)
		    ezAzEntity.setContent(content.getContent());
		if (viewDsAttrEntity.getId() != null)
		    System.out.println("----TODO---- ViewDsAttrEntity.getId()=" + viewDsAttrEntity.getId().toString());
		// Attributes within the entity
		List<AttributeType> viewDsAttributes = viewDsAttrEntity.getAttribute();
		for (AttributeType viewDsAttr : viewDsAttributes) {
		    Attribute ezAzAttr = convertViewDsAttr(viewDsAttr);
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

    private AttributeAssignment convertViewDsAttributeAssignment(AttributeAssignmentType viewDsAssign) {
	genericAttributeAssignment ezAzAssign = new genericAttributeAssignment();
	ezAzAssign.setCategory(Identifier.create(viewDsAssign.getCategory()));
	genericAttribute ezAzAttr = new genericAttribute();
	ezAzAttr.setId(Identifier.create(viewDsAssign.getAttributeId()));
	ezAzAttr.setIssuer(viewDsAssign.getIssuer());
	ezAzAttr.setType(Identifier.create(viewDsAssign.getDataType()));
	abstractSet<Object> avs = new abstractSet<Object>();
	for (Object o : viewDsAssign.getContent()) {
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

    private Attribute convertViewDsAttr(AttributeType viewDsAttr) {
	genericAttribute ezAzAttr = new genericAttribute();
	ezAzAttr.setId(Identifier.create(viewDsAttr.getAttributeId()));
	ezAzAttr.setIssuer(viewDsAttr.getIssuer());
	ezAzAttr.setIncludeInResult(viewDsAttr.isIncludeInResult());
	// Attribute values
	List<AttributeValueType> viewDsAttrVals = viewDsAttr.getAttributeValue();
	if (viewDsAttrVals != null) {
	    abstractSet<Object> avs = new abstractSet<Object>();
	    for (AttributeValueType viewDsAV : viewDsAttrVals) {
		ezAzAttr.setType(Identifier.create(viewDsAV.getDataType()));
		for (Object o : viewDsAV.getContent()) {
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
	throw new genericLayer2RuntimeException("ViewDsSoapDriver does not implement extendedOperation!");
    }

    @Override
    public Response extendedOperation(String operation, Request request, Object extendedContext, Object appContext)
	    throws Exception {
	throw new genericLayer2RuntimeException("ViewDsSoapDriver does not implement extendedOperation!");
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
	throw new genericLayer2RuntimeException("ViewDsSoapDriver does not implement extendedOperation!");
    }

    private static String PROP_ENDPOINT = "endpoint";
    private static String PROP_USERNAME = "username";
    private static String PROP_PASSWORD = "password";
    private String endPoint;
    private String userName;
    private String passWord;

    @Override
    public void setupConnection(Properties properties) {
	// find out the name of the PDP in the properties
	// grab PDP name from the keys.
	//
	// Just grab the first one, and then try to derive the PDP name and
	// prefix
	String s = properties.keySet().toArray(new String[] {})[0];
	String pdpName_tmp = s.substring(PDPserviceFactory.PREFIX_PDP.length());
	String pdpName = pdpName_tmp.substring(0, pdpName_tmp.indexOf("."));
	System.out.println("Found PDP Name: " + pdpName);
	String prefix = PDPserviceFactory.PREFIX_PDP + pdpName;
	System.out.println("Found Prefix for these properties: " + prefix);
	// Check for endpoint address
	String p = prefix + "." + PROP_ENDPOINT;
	endPoint = properties.getProperty(p);
	if (endPoint == null || endPoint.length() == 0) {
	    throw new RuntimeException("ViewDsSOAPdriver: property " + p + " cannot be empty!");
	}
	// Check for user name and password
	p = prefix + "." + PROP_USERNAME;
	userName = properties.getProperty(p);
	p = prefix + "." + PROP_PASSWORD;
	passWord = properties.getProperty(p);
    }

}
