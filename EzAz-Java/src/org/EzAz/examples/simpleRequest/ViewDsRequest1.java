/**
 * Copyright 2012-2013 Felix Gaehtgens
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.EzAz.examples.simpleRequest;

import java.io.InputStream;
import java.util.Properties;

import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer3.PDPService;
import org.EzAz.Layer3.PDPserviceFactory;
import org.EzAz.generic.Layer2.genericAttribute;
import org.EzAz.generic.Layer2.genericRequest;
import org.EzAz.generic.Layer2.genericResponse;

public class ViewDsRequest1 {

    public ViewDsRequest1() {

    }

    public void loadProps() {
	Properties prop = new Properties();
	// Load properties
	InputStream propFile = this.getClass().getClassLoader()
		.getResourceAsStream("org/EzAz/examples/simpleRequest/viewds.properties");
	try {
	    prop.load(propFile);
	    PDPserviceFactory.initSDK(prop);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	ViewDsRequest1 r = new ViewDsRequest1();
	// Load properties file
	r.loadProps();
	// Initialize connection to PDP (policy decision point)
	// called "dummy" and get a handle. This name is defined in
	// the properties file.
	// We need this handle to communicate with the PDP.
	PDPService pdp = PDPserviceFactory.getPDP("axio");
	// Now construct a new request.
	Request req = PDPserviceFactory.newRequest();

	// Now create attributes on the request.
	// h.addAttribute(AttributeEntity.CAT_SUBJECT,"subject-id", null,
	// "jack");

	req.addAttribute(AttributeEntity.CAT_ACTION, "urn:oasis:names:tc:xacml:1.0:action:action-id", "", "RemoveType")
		.setIncludeInResult(true);
	genericAttribute attr = new genericAttribute();
	attr.setType(Attribute.TYPE_X500NAME);
	attr.setId(Identifier.create("urn:oasis:names:tc:xacml:1.0:subject:subject-id"));
	attr.setValue("cn=Lucy Uren,ou=Human Resources Group,ou=Deltawing InfoSystems,o=Deltawing");
	req.addAttribute(AttributeEntity.CAT_SUBJECT, attr).setIncludeInResult(true);

	attr = new genericAttribute();
	attr.setType(Attribute.TYPE_X500NAME);
	attr.setId(Identifier.create("urn:oasis:names:tc:xacml:1.0:resource:resource-id"));
	attr.setValue("ou=Deltawing Automotive Ltd.,o=Deltawing");
	req.addAttribute(AttributeEntity.CAT_RESOURCE, attr).setIncludeInResult(true);

	attr = new genericAttribute();
	attr.setType(Attribute.TYPE_ANYURI);
	attr.setId(Identifier.create("http://viewds.com/xacml/resource/attribute-type"));
	attr.setValue("urn:oid:0.9.2342.19200300.100.1.10");
	req.addAttribute(AttributeEntity.CAT_RESOURCE, attr).setIncludeInResult(true);

	// Print out the request
	genericRequest.prettyPrint(System.out, "", req);

	// Now call the PDP using the evaluate() function.
	Response resp;
	try {
	    resp = pdp.evaluate(req);
	} catch (Exception e) {
	    e.printStackTrace();
	    return;
	}
	genericResponse.prettyPrint(System.out, "", resp);
	// Check whether the response allows our request.
	System.out.println("IS ALLOWED? " + resp.isAllowed(false));
    }

}
