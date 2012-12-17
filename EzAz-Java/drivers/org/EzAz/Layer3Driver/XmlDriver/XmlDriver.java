package org.EzAz.Layer3Driver.XmlDriver;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.AdviceType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.AssociatedAdviceType;
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
import org.EzAz.Layer3Driver.AsyncEmulator.AsyncEmulator;
import org.EzAz.generic.Layer2.genericAdviceObligation;
import org.EzAz.generic.Layer2.genericAttribute;
import org.EzAz.generic.Layer2.genericAttributeAssignment;
import org.EzAz.generic.Layer2.genericAttributeEntity;
import org.EzAz.generic.Layer2.genericLayer2RuntimeException;
import org.EzAz.generic.Layer2.genericResponse;
import org.EzAz.generic.Layer2.genericResult;
import org.EzAz.generic.Layer2.genericStatus;

public class XmlDriver extends AsyncEmulator {

	public XmlDriver() {

	}

	@Override
	public Response evaluate(Request request) throws Exception {
		request.setImmutable();
		@SuppressWarnings("unused")
		RequestType xmlRequest = createXmlRequest(request);
		// Placeholder - do your connection handling here!
		ResponseType xmlResp = readFile();
		Response ezAzResponse = generateEzAzResponse(xmlResp);
		return ezAzResponse;
	}
	
	public void sendit(RequestType xmlRequest) {
		
	}
	
	public void sendRequest() throws Exception {
		throw new Exception("Sending a request is not implemented");
	}

	public ResponseType readFile() throws Exception {
		InputStream xmlFile = this
				.getClass()
				.getClassLoader()
				.getResourceAsStream(
						"org/EzAz/examples/simpleRequest/xmlWithAdvice.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(ResponseType.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		@SuppressWarnings("rawtypes")
		JAXBElement o = (JAXBElement) jaxbUnmarshaller.unmarshal(xmlFile);
		ResponseType resp = (ResponseType) o.getValue();
		return resp;
	}

	private RequestType createXmlRequest(Request request) throws Exception {
		RequestType req = new RequestType();

		// Look at the request categories
		abstractMap<Identifier, AttributeEntity> entities = request
				.getCategoriesEntities();
		for (Identifier cat : entities.keySet()) {
			// Create Xml cat
			AttributesType atList = new AttributesType();
			atList.setCategory(cat.toString());
			// Fetch attributes
			AttributeEntity entity = entities.get(cat);
			abstractSet<Attribute> attributes = entity.getAttributes();
			for (Attribute at : attributes) {
				// Create Xml attribute
				AttributeType xmlAt = new AttributeType();
				xmlAt.setAttributeId(at.getId().toString());
				xmlAt.setIssuer(at.getIssuer());
				xmlAt.setIncludeInResult(at.getIncludeInResult());
				AttributeValueType avt = new AttributeValueType();
				avt.setDataType(at.getType().toString());
				avt.getContent().add(at.getValue().toString());
				xmlAt.getAttributeValue().add(avt);
				atList.getAttribute().add(xmlAt);
			}

			// Add xml cat to xml req
			req.getAttributes().add(atList);
		}
		return req;
	}

	private ResponseType sendXmlRequest(RequestType xmlReq) throws Exception {
		throw new Exception("Not yet implemented!");
	}

	private org.EzAz.Layer2.Response generateEzAzResponse(ResponseType xmlResp) {
		// create EzAz Response
		Response EzAzResp = new genericResponse();

		// evaluate response
		List<ResultType> xmlResults = xmlResp.getResult();

		// Iterate through results
		for (ResultType xmlRes : xmlResults) {
			// Create new EzAz Result
			genericResult ezAzRes = new genericResult();
			// Set Decision
			DecisionType xmlDecision = xmlRes.getDecision();
			if (xmlDecision.compareTo(DecisionType.PERMIT) == 0)
				ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_PERMIT);
			else if (xmlDecision.compareTo(DecisionType.DENY) == 0)
				ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_DENY);
			else if (xmlDecision.compareTo(DecisionType.NOT_APPLICABLE) == 0)
				ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_NOT_APPLICABLE);
			else if (xmlDecision.compareTo(DecisionType.INDETERMINATE) == 0)
				ezAzRes.setDecision(org.EzAz.Layer2.Result.RESULT_INDETERMINATE);
			// Set Status
			StatusType xmlStatus = xmlRes.getStatus();
			genericStatus ezAzStatus = new genericStatus();
			StatusCodeType xmlStatusCode = xmlStatus.getStatusCode();
			if (xmlStatusCode != null)
				ezAzStatus.setStatusCode(xmlStatusCode.getValue());
			StatusDetailType xmlStatusDetail = xmlStatus.getStatusDetail();
			if (xmlStatusDetail != null)
				ezAzStatus.setStatusDetail(xmlStatusDetail.toString());
			String xmlStatusMessage = xmlStatus.getStatusMessage();
			if (xmlStatusMessage != null)
				ezAzStatus.setStatusMessage(xmlStatusMessage.toString());
			// Set Status to EzAz Result
			ezAzRes.setStatus(ezAzStatus);
			// TODO: Set Advice
			AssociatedAdviceType xmlAdviceObject = xmlRes.getAssociatedAdvice();
			if (xmlAdviceObject != null) {
				List<AdviceType> xmlAdvice = xmlAdviceObject.getAdvice();
				for (AdviceType xmlObligation : xmlAdvice) {
					genericAdviceObligation ezAzObl = xmlAdviceToEzAzObligation(xmlObligation);
					abstractSet<AdviceObligation> advice = ezAzRes
							.getAssociatedAdvice();
					if (advice == null) {
						advice = new abstractSet<AdviceObligation>();
						ezAzRes.setAssociatedAdvice(advice);
					}
					advice.add(ezAzObl);
				}
			}
			// TODO: Set Obligations
			ObligationsType xmlObligationObject = xmlRes.getObligations();
			if (xmlObligationObject != null) {
				List<ObligationType> xmlObligations = xmlObligationObject
						.getObligation();
				for (ObligationType xmlObligation : xmlObligations) {
					genericAdviceObligation ezAzObl = xmlObligationToEzAzObligation(xmlObligation);
					ezAzRes.getObligations().add(ezAzObl);
				}
			}
			// TODO: Set PolicyIdList
			// TODO: Set AttributeEntities
			List<AttributesType> xmlAttrEntities = xmlRes.getAttributes();
			for (AttributesType xmlAttrEntity : xmlAttrEntities) {
				genericAttributeEntity ezAzEntity = new genericAttributeEntity();
				ezAzEntity.setCategory(Identifier.create(xmlAttrEntity
						.getCategory()));
				abstractMap<Identifier, AttributeEntity> ezAzAttributeEntities = ezAzRes
						.getCategoriesEntities();
				if (ezAzAttributeEntities == null) {
					ezAzAttributeEntities = new abstractMap<Identifier, AttributeEntity>();
					ezAzRes.setCategoriesEntities(ezAzAttributeEntities);
				}
				ezAzAttributeEntities.put(ezAzEntity.getCategory(), ezAzEntity);
				ContentType content = xmlAttrEntity.getContent();
				if (content != null)
					ezAzEntity.setContent(content.getContent());
				if (xmlAttrEntity.getId() != null)
					System.out.println("----TODO---- xmlAttrEntity.getId()="
							+ xmlAttrEntity.getId().toString());
				// Attributes within the entity
				List<AttributeType> xmlAttributes = xmlAttrEntity
						.getAttribute();
				for (AttributeType xmlAttr : xmlAttributes) {
					Attribute ezAzAttr = convertXmlAttr(xmlAttr);
					abstractSet<Attribute> attributes = ezAzEntity
							.getAttributes();
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

	private genericAdviceObligation xmlAdviceToEzAzObligation(
			AdviceType xmlAdvice) {
		genericAdviceObligation ezAzObl = new genericAdviceObligation();
		ezAzObl.setAttributeAssignments(new abstractSet<AttributeAssignment>());
		ezAzObl.setId(Identifier.create(xmlAdvice.getAdviceId()));
		List<AttributeAssignmentType> xmlAttrAssignList = xmlAdvice.getAttributeAssignment();
		if (xmlAttrAssignList != null) {
			for (AttributeAssignmentType xmlAssign : xmlAttrAssignList) {
				AttributeAssignment ezAzAssign = convertXmlAttributeAssignment(xmlAssign);
				ezAzObl.getAttributeAssignments().add(ezAzAssign);
			}
		}
		return ezAzObl;
	}

	private genericAdviceObligation xmlObligationToEzAzObligation(
			ObligationType xmlObligation) {
		genericAdviceObligation ezAzObl = new genericAdviceObligation();
		ezAzObl.setId(Identifier.create(xmlObligation.getObligationId()));
		List<AttributeAssignmentType> xmlAttrAssignList = xmlObligation
				.getAttributeAssignment();
		if (xmlAttrAssignList != null) {
			for (AttributeAssignmentType xmlAssign : xmlAttrAssignList) {
				AttributeAssignment ezAzAssign = convertXmlAttributeAssignment(xmlAssign);
				ezAzObl.getAttributeAssignments().add(ezAzAssign);
			}
		}
		return ezAzObl;
	}

	private AttributeAssignment convertXmlAttributeAssignment(
			AttributeAssignmentType xmlAssign) {
		genericAttributeAssignment ezAzAssign = new genericAttributeAssignment();
		ezAzAssign.setCategory(Identifier.create(xmlAssign.getCategory()));
		genericAttribute ezAzAttr = new genericAttribute();
		ezAzAttr.setId(Identifier.create(xmlAssign.getAttributeId()));
		ezAzAttr.setIssuer(xmlAssign.getIssuer());
		ezAzAttr.setType(Identifier.create(xmlAssign.getDataType()));
		abstractSet<Object> avs = new abstractSet<Object>();
		for (Object o : xmlAssign.getContent()) {
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

	private Attribute convertXmlAttr(AttributeType xmlAttr) {
		genericAttribute ezAzAttr = new genericAttribute();
		ezAzAttr.setId(Identifier.create(xmlAttr.getAttributeId()));
		ezAzAttr.setIssuer(xmlAttr.getIssuer());
		ezAzAttr.setIncludeInResult(xmlAttr.isIncludeInResult());
		// Attribute values
		List<AttributeValueType> xmlAttrVals = xmlAttr.getAttributeValue();
		if (xmlAttrVals != null) {
			abstractSet<Object> avs = new abstractSet<Object>();
			for (AttributeValueType xmlAV : xmlAttrVals) {
				ezAzAttr.setType(Identifier.create(xmlAV.getDataType()));
				for (Object o : xmlAV.getContent()) {
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
	public Response evaluate(Request request, Object appContext)
			throws Exception {
		return evaluate(request);
	}

	@Override
	public Response extendedOperation(String operation, Request request,
			Object extendedContext) throws Exception {
		throw new genericLayer2RuntimeException(
				"XmlDriver does not implement extendedOperation!");
	}

	@Override
	public Response extendedOperation(String operation, Request request,
			Object extendedContext, Object appContext) throws Exception {
		throw new genericLayer2RuntimeException(
				"XmlDriver does not implement extendedOperation!");
	}

	@Override
	public Response evaluate(Request request, Object appContext,
			abstractSet<AdviceObligationHandler> obligationHandlers,
			abstractSet<AdviceObligationHandler> adviceListeners)
			throws Exception {
		return evaluate(request);
	}

	@Override
	public Response extendedOperation(String operation, Request request,
			Object extendedContext, Object appContext,
			abstractSet<AdviceObligationHandler> adviceHandlers,
			abstractSet<AdviceObligationHandler> obligationHandlers)
			throws Exception {
		throw new genericLayer2RuntimeException(
				"XmlDriver does not implement extendedOperation!");
	}

	@Override
	public void setupConnection(Properties properties) {
	}

}
