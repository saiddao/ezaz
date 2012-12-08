/**
 * Copyright 2012 Felix Gaehtgens
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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.AttributeHelper;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer3.PDPService;
import org.EzAz.Layer3.PDPserviceFactory;
import org.EzAz.generic.Layer2.genericLayer2Utils;

public class Request1 {

	public Request1() {
		
	}
	
	public void loadProps() {
		Properties prop=new Properties();
		// Load properties
		InputStream propFile = this.getClass().getClassLoader().getResourceAsStream("org/EzAz/examples/simpleRequest/sdk.properties");
        try {
        	prop.load(propFile);
        	PDPserviceFactory.initSDK(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Request1 r=new Request1();
		r.loadProps();
		
		Request req=PDPserviceFactory.newRequest();
		AttributeHelper h=(AttributeHelper)req;
		h.addAttribute(AttributeEntity.CAT_SUBJECT,
				Identifier.create("subject-id"), null, "jack");
		h.addAttribute(AttributeEntity.CAT_ACTION,
				Identifier.create("action-id"), null, "print");
		h.addAttribute(AttributeEntity.CAT_RESOURCE,
				Identifier.create("resource-type"), null, "document");
		h.addAttribute(AttributeEntity.CAT_RESOURCE,
				Identifier.create("resource-id"), null, "TPSreport25.xls");
		genericLayer2Utils.prettyPrint(req);
		PDPService pdp=PDPserviceFactory.getPDP("dummy");
		Response resp;
		try {
			resp = pdp.evaluate(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		System.out.println("IS ALLOWED? "+resp.isAllowed(false));
	}
	

}
