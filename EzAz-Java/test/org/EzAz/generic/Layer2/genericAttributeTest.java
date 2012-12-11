package org.EzAz.generic.Layer2;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.abstractSet;
import org.EzAz.generic.Layer2.genericAttribute;
import org.EzAz.generic.Layer2.genericRequest;
import org.junit.Test;

public class genericAttributeTest {
		
	@Test
	public void testIsBoolean() {
		// Create one boolean attribute
		genericAttribute test=new genericAttribute();
		test.setValue(new Boolean (true));
		test.setType(Attribute.TYPE_BOOLEAN);
		if (!test.isBoolean()) {
			fail("setValue(Boolean) fails isBool()==true");
		}
		
		// Create a non-boolean attribute
		test=new genericAttribute();
		test.setValue(new Integer (12345));
		test.setType(Attribute.TYPE_INTEGER);
		if (test.isBoolean()) {
			fail("setValue(Boolean) fails isBool()==false");
		}

	}
	
	@Test
	public void test2IsBoolean() {
		// Create one attribute using the addAttribute() function
		Identifier id=Identifier.create ("subject-bool");
		// Issuer is null
		genericRequest req=new genericRequest();
		req.addAttribute(AttributeEntity.CAT_SUBJECT, id, null, true);
		// Now check whether this attribute has the isBoolean set
		AttributeEntity attributeEntity = req.getCategoriesEntities().get(AttributeEntity.CAT_SUBJECT);
		if (attributeEntity == null) {
			fail("Could not get the attribute back that I just set!");
		}
		abstractSet<Attribute> attributes = attributeEntity.getAttributes();
		boolean tmpPass=false;
		for (Attribute a: attributes) {
			if (a.getId().equals(id) && a.isBoolean())
				tmpPass=true;
		}
		if (!tmpPass) {
			fail("boolean attribute fails isBool()");
		}
		genericAttribute test=new genericAttribute();

		test.setValue(new Boolean (true));
		test.setType(Attribute.TYPE_BOOLEAN);
		if (!test.isBoolean()) {
			fail("setValue(Boolean) fails isBool()");
		}
	}
	
	@Test
	public void testGetSetId() {
		genericAttribute test=new genericAttribute();
		Identifier id=Identifier.create ("foo");
		Identifier id2=Identifier.create ("foo");
		test.setId (id);
		Identifier id3=Identifier.create("bar");
		Identifier getId=test.getId();
		if (!getId.equals(id2))
			fail ("testGetSetId - equality");
		if (getId.equals(id3))
			fail ("testGetSetId - non equality");
	}
	
	@Test
	public void testGetSetType() {
		genericAttribute test=new genericAttribute();
		Identifier id=Identifier.create ((Attribute.TYPE_STRING).toString());
		
		test.setType (Attribute.TYPE_STRING);
		if (!test.getType().equals(id))
			fail ("testGetSetType - equality");
		if (!test.getType().equals(Attribute.TYPE_STRING))
			fail ("testGetSetType - equality");
		if (test.getType().equals(Attribute.TYPE_INTEGER))
			fail ("testGetSetType - non equality");
	}
	
	@Test
	public void testGetSetIssuer() {
		genericAttribute test=new genericAttribute();
		String nullStr=test.getIssuer();
		if (nullStr != null) {
			fail ("Issuer should be null, not empty");
		}
		String issuer1="foo-issuer1";
		String issuer2="foo-issuer1";
		String issuer3="bar-issuer3";
		test.setIssuer(issuer1);
		if (!test.getIssuer().equals(issuer2))
			fail ("testGetSetIssuer - equality");
		if (test.getIssuer().equals(issuer3))
			fail ("testGetSetIssuer - non equality");
	}

	@Test
	public void testGetSetValue() {
		genericAttribute test=new genericAttribute();
		String nullStr=test.getIssuer();
		if (nullStr != null) {
			fail ("Issuer should be null, not empty");
		}
		String str1="onetwothree";
		String str2="onetwothreefour";
		test.setValue(str1);
		Object oo=test.getValue();
		assertEquals("Strings should be the same!", oo, "onetwothree");
		assertTrue("Strings should be the same!", (oo.equals("onetwothree")));
		assertTrue("Strings should not be the same!", (!oo.equals(str2)));
	}
	
	@Test
	public void testIsMultiValue() {
		genericAttribute test=new genericAttribute();
		String a="bla";
		test.setValue(a);
		assertTrue("String is NOT multi-value!", !test.isMultiValue());
		
		test=new genericAttribute();
		Integer i=new Integer(2);
		test.setValue(i);
		assertTrue("Integer is NOT multi-value!", !test.isMultiValue());

		test=new genericAttribute();
		Boolean b=new Boolean(true);
		test.setValue(b);
		assertTrue("Boolean is NOT multi-value!", !test.isMultiValue());

		test=new genericAttribute();
		Double d=new Double(2.0);
		test.setValue(d);
		assertTrue("Double is NOT multi-value!", !test.isMultiValue());
		
		test=new genericAttribute();
		ArrayList al=new ArrayList();
		test.setValue(al);
		assertTrue("Arraylist is multi-value!", test.isMultiValue());
	}

}
