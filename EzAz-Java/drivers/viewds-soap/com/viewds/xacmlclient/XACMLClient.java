/*
 * Copyright (C) 2013 eNitiatives.com Pty. Ltd.
 */
package com.viewds.xacmlclient;

import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import oasis.names.tc.saml._2_0.assertion.AssertionType;
import oasis.names.tc.saml._2_0.assertion.StatementAbstractType;
import oasis.names.tc.saml._2_0.protocol.ResponseType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributeType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributeValueType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributesType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.RequestType;
import oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.assertion.wd_13.XACMLAuthzDecisionStatementType;
import oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.protocol.wd_13.XACMLAuthzDecisionQueryType;

/**
 *
 * @author mennis
 */
public class XACMLClient
{
    final static String XACML_ATTRIBUTE_SUBJECT_ID =
            "urn:oasis:names:tc:xacml:1.0:subject:subject-id";
    
    final static String XACML_CATEGORY_ACCESS_SUBJECT =
            "urn:oasis:names:tc:xacml:1.0:subject-category:access-subject";
    
    final static String XACML_DATA_TYPE_STRING =
            "http://www.w3.org/2001/XMLSchema#string";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
            throws DatatypeConfigurationException
    {
        AssertionType assertion;
        AttributeType attribute;
        AttributesType attributes;
        AttributeValueType value;
        DatatypeFactory df;
        List<AttributeType> attributeList;
        List<AttributesType> categories;
        List<AttributeValueType> values;
        List<Object> assertions;
        List<StatementAbstractType> statements;
        Object object;
        RequestType request;
        ResponseType response;
        XACMLAuthzDecisionQueryType query;
        XACMLAuthzDecisionStatementType decision;
        XMLGregorianCalendar now;

        df = DatatypeFactory.newInstance();
        now = df.newXMLGregorianCalendar(new GregorianCalendar());

        query = new XACMLAuthzDecisionQueryType();
        query.setID("XACMLClient");
        query.setVersion("2.0");
        query.setIssueInstant(now);

        request = new RequestType();
        categories = request.getAttributes();
        attributes = new AttributesType();
        attributes.setCategory(XACML_CATEGORY_ACCESS_SUBJECT);
        attributeList = attributes.getAttribute();
        attribute = new AttributeType();
        attribute.setAttributeId(XACML_ATTRIBUTE_SUBJECT_ID);
        attribute.setIncludeInResult(true);
        values = attribute.getAttributeValue();
        value = new AttributeValueType();
        value.setDataType(XACML_DATA_TYPE_STRING);
        //value.setValue("luren");
        values.add(value);
        attributeList.add(attribute);
        categories.add(attributes);

        query.setRequest(request);
        System.out.println("request = " + query.toString());

        response = getAuthorizationDecision(query);
        System.out.println("response = " + response.toString());
        
        assertions = response.getAssertionOrEncryptedAssertion();
        for (int i = 0; i < assertions.size(); i += 1) {
            object = assertions.get(i);
            if (object instanceof AssertionType) {
                assertion = (AssertionType)object;
                statements = assertion.getStatementOrAuthnStatementOrAuthzDecisionStatement();
                for (int j = 0; j < statements.size(); j += 1) {
                    object = statements.get(j);
                    if (object instanceof XACMLAuthzDecisionStatementType) {
                        decision = (XACMLAuthzDecisionStatementType)object;
                        System.out.println("decision = " + decision.getResponse().getResult().get(0).getDecision().value());
                    }
                    else {
                        System.out.println("statement = " + object.toString());
                    }
                }
            }
            else {
                System.out.println("assertion = " + object.toString());
            }
        }
    }

    private static ResponseType getAuthorizationDecision(java.lang.Object body)
    {
        com.viewds.wsdl.xacmlv3wd17pdp.AuthorizationDecisionService service =
                new com.viewds.wsdl.xacmlv3wd17pdp.AuthorizationDecisionService();
        com.viewds.wsdl.xacmlv3wd17pdp.AuthorizationDecisionPort port =
                service.getAuthorizationDecisionPort();
        return port.getAuthorizationDecision(body);
    }
}
