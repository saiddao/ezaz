package org.EzAz.generic.Layer2;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.abstractMap;
import org.EzAz.Layer2.abstractSet;
import org.EzAz.generic.Layer2.genericAttribute;
import org.EzAz.generic.Layer2.genericAttributeEntity;
import org.EzAz.generic.Layer2.genericRequest;
import org.junit.Before;
import org.junit.Test;

public class genericRequestTest {

	genericRequest req;

	@Before
	public void setupReq() {
		req = new genericRequest();

		// Create one attribute using the addAttribute() function
		Identifier id = Identifier.create("subject-bool");
		// Issuer is null
		req.addAttribute(AttributeEntity.CAT_SUBJECT, id, null, true);
		// Now check whether this attribute has the isBoolean set
		AttributeEntity attributeEntity = req.getCategoriesEntities().get(
				AttributeEntity.CAT_SUBJECT);
		if (attributeEntity == null) {
			fail("Could not get the attribute back that I just set!");
		}
		abstractSet<Attribute> attributes = attributeEntity.getAttributes();
		boolean tmpPass = false;
		for (Attribute a : attributes) {
			if (a.getId().equals(id) && a.isBoolean())
				tmpPass = true;
		}
		if (!tmpPass) {
			fail("boolean attribute fails isBool()");
		}
		genericAttribute test = new genericAttribute();

		test.setValue(new Boolean(true));
		test.setType(Attribute.TYPE_BOOLEAN);
		if (!test.isBoolean()) {
			fail("setValue(Boolean) fails isBool()");
		}
	}

	@Test
	public void testHasRepeatedCategories() {
		assertTrue("Request should not have repeated Categories!",
				!req.hasRepeatedCategories());
	}

	@Test
	public void testIsMultipleRequest() {
		assertTrue("Request should not be multiple Request type!",
				!req.isMultipleRequest());
	}

	@Test
	public void testIsMutable() {
		assertTrue("Request should be mutable!", req.isMutable());
		req.setImmutable();
		assertTrue("Request should NOT be mutable!", !req.isMutable());
		try {
			req.addAttribute(AttributeEntity.CAT_SUBJECT,
					Identifier.create("foo"), null, "bar");
		} catch (Exception ee) {
			return; // Everything OK
		}
		fail("testIsMutable: should have caught an exception trying to modify an immutable request!");
	}
	
	@Test
	public void testAddAttributeIdentifierIdentifierStringIdentifierObject() {
		// Create boolean attribute
		req.addAttribute(AttributeEntity.CAT_ACTION, Identifier.create ("action-id"), "myissuer", Attribute.TYPE_BOOLEAN, new Boolean (false));
		Attribute attr=getAttribute (AttributeEntity.CAT_ACTION, Identifier.create ("action-id"));
		assertNotNull ("Attribute not found!", attr);
		verifyAttribute(attr, Boolean.class, Attribute.TYPE_BOOLEAN, "myissuer", new Boolean(false));
	}

	@Test
	public void testAddAttributeIdentifierIdentifierStringBoolean() {
		// Create boolean attribute
		req.addAttribute(AttributeEntity.CAT_ACTION, Identifier.create ("action-id"), "myissuer2", true);
		Attribute attr=getAttribute (AttributeEntity.CAT_ACTION, Identifier.create ("action-id"));
		assertNotNull ("Attribute not found!", attr);
		verifyAttribute(attr, Boolean.class, Attribute.TYPE_BOOLEAN, "myissuer2", new Boolean(true));
	}

	@Test
	public void testAddAttributeIdentifierIdentifierStringInt() {
		// Create Integer attribute
		req.addAttribute(AttributeEntity.CAT_ACTION, Identifier.create ("action-id"), "myissuer3", 12345);
		Attribute attr=getAttribute (AttributeEntity.CAT_ACTION, Identifier.create ("action-id"));
		assertNotNull ("Attribute not found!", attr);
		verifyAttribute(attr, Integer.class, Attribute.TYPE_INTEGER, "myissuer3", new Integer(12345));
	}

	@Test
	public void testAddAttributeIdentifierIdentifierStringString() {
		// Create String attribute
		req.addAttribute(AttributeEntity.CAT_ACTION, Identifier.create ("action-id"), "myissuer4", "foobar");
		Attribute attr=getAttribute (AttributeEntity.CAT_ACTION, Identifier.create ("action-id"));
		assertNotNull ("Attribute not found!", attr);
		verifyAttribute(attr, String.class, Attribute.TYPE_STRING, "myissuer4", new String("foobar"));
	}

	@Test
	public void testAddAttributeIdentifierAttribute() {
		// Create Date attribute
		Calendar cc=new GregorianCalendar();
		cc.clear();
		cc.set(112 + 1900, 11, 22, 21, 23, 0);
		Attribute attr=new genericAttribute();
		attr.setType(Attribute.TYPE_DATETIME);
		attr.setIssuer("myissuer");
		attr.setValue(cc);
		attr.setId(Identifier.create ("action-date"));
		req.addAttribute(AttributeEntity.CAT_ACTION, attr);
		Attribute attr2=getAttribute (AttributeEntity.CAT_ACTION, Identifier.create ("action-date"));
		assertNotNull ("Attribute not found!", attr2);
		Object o=attr2.getValue();
		if (!(o instanceof Calendar))
			fail ("Return value is not Calendar, but "+o.getClass().toString());
		if (!attr2.getType().equals(Attribute.TYPE_DATETIME))
			fail ("Return type is not DATETIME, but "+attr2.getType().toString());
		if (!attr2.getIssuer().equals("myissuer"))
			fail ("Attribute issuer does not match!");
		assertTrue ("DATE/TIME value does not match!", ((Calendar)o).equals (cc));
	}

	@Test
	public void testAddCategoryIdentifierAbstractSetOfAttribute() {
		abstractSet<Attribute> attrs=createTwoAttributes();
		
		req.addCategory(AttributeEntity.CAT_ACTION, attrs);
		checkTwoAttributes();
	}	

	@Test
	public void testAddCategoryIdentifierAttributeEntity() {
		abstractSet<Attribute> attrs=createTwoAttributes();
		AttributeEntity ent = new genericAttributeEntity();
		ent.setCategory(AttributeEntity.CAT_ACTION);
		ent.setAttributes(attrs);
		req.addCategory(ent);
		checkTwoAttributes();
		
	}

	@Test
	public void testCloneRequest() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCategoriesAttributes() {
		// Add two action attributes to request
		req.addCategory(AttributeEntity.CAT_ACTION, createTwoAttributes());
		abstractMap<Identifier, abstractSet<Attribute>> categoriesAttributes = req.getCategoriesAttributes();
		assertNotNull ("getCategoriesAttributes() returns null!", categoriesAttributes);

		// Collect action and subject category attributes
		abstractSet<Attribute> actionAttrs = categoriesAttributes.get(AttributeEntity.CAT_ACTION);
		assertNotNull ("cannot get CAT_ACTION from getCategoriesAttributes() !", actionAttrs);
		abstractSet<Attribute> subjectAttrs = categoriesAttributes.get(AttributeEntity.CAT_SUBJECT);
		assertNotNull ("cannot get CAT_SUBJECT from getCategoriesAttributes() !", actionAttrs);
		
		// Check the attributes in the sets
		Attribute a=searchForAttrInSet(Identifier.create ("action-id"), actionAttrs);
		assertNotNull ("Attribute not found!", a);
		a=searchForAttrInSet(Identifier.create ("action-date"), actionAttrs);
		assertNotNull ("Attribute not found!", a);
		a=searchForAttrInSet(Identifier.create ("subject-bool"), subjectAttrs);
		assertNotNull ("Attribute not found!", a);
	}

	@Test
	public void testGetCategoriesEntities() {
		// Add two action attributes to request
		req.addCategory(AttributeEntity.CAT_ACTION, createTwoAttributes());
		checkSubjectAndActionAttributes();
	}
	
	public void checkSubjectAndActionAttributes() {
		abstractMap<Identifier,AttributeEntity> ents = req.getCategoriesEntities();
		assertNotNull ("getCategoriesEntities returns null!", ents);

		// Check action and subject category entities
		AttributeEntity actionEnt = ents.get(AttributeEntity.CAT_ACTION);
		assertNotNull ("cannot get CAT_ACTION from getCategoriesEntities() !", actionEnt);
		AttributeEntity subjectEnt = ents.get(AttributeEntity.CAT_SUBJECT);
		assertNotNull ("cannot get CAT_ACTION from getCategoriesEntities() !", actionEnt);
		
		// Check the entities
		assertTrue ("Entity does not have category ACTION!", actionEnt.getCategory().equals(AttributeEntity.CAT_ACTION));
		assertTrue ("Entity does not have category SUBJECT!", subjectEnt.getCategory().equals(AttributeEntity.CAT_SUBJECT));
		abstractSet<Attribute> actionAttrs = actionEnt.getAttributes();
		abstractSet<Attribute> subjectAttrs = subjectEnt.getAttributes();
		// Check the attributes in the sets
		Attribute a=searchForAttrInSet(Identifier.create ("action-id"), actionAttrs);
		assertNotNull ("Attribute not found!", a);
		a=searchForAttrInSet(Identifier.create ("action-date"), actionAttrs);
		assertNotNull ("Attribute not found!", a);
		a=searchForAttrInSet(Identifier.create ("subject-bool"), subjectAttrs);
		assertNotNull ("Attribute not found!", a);
	}

	@Test
	public void testRemoveAttribute() {
		// Add two action attributes to request
		req.addCategory(AttributeEntity.CAT_ACTION, createTwoAttributes());
		abstractSet<Attribute> actionAttrs=req.getCategoriesAttributes().get(AttributeEntity.CAT_ACTION);
		// Collect action and subject category attributes
		assertNotNull ("cannot get CAT_ACTION from getCategoriesAttributes() !", actionAttrs);
		assertTrue ("There should be two ACTION attributes, not "+actionAttrs.size(), actionAttrs.size()==2);
		Attribute a=searchForAttrInSet(Identifier.create ("action-date"), actionAttrs);
		assertNotNull ("Attribute not found!", a);
		// Remove an attribute
		req.removeAttribute(AttributeEntity.CAT_ACTION, Identifier.create ("action-date"), "myissuer");
		assertTrue ("There should be one ACTION attributes after removeAttribute(), not "+actionAttrs.size(), actionAttrs.size()==1);
		a=searchForAttrInSet(Identifier.create ("action-date"), actionAttrs);
		assertTrue ("Attribute is still there even though it should have been removed by removeAttribute()!", a==null);
		a=searchForAttrInSet(Identifier.create ("action-id"), actionAttrs);
		assertNotNull ("Attribute not found!", a);
	}

	@Test
	public void testSetCategoriesAttributeEntity() {
		genericAttributeEntity ent=new genericAttributeEntity();
		ent.setCategory(AttributeEntity.CAT_ACTION);
		ent.setAttributes(createTwoAttributes());
		abstractMap<Identifier, AttributeEntity> categories=new abstractMap<Identifier, AttributeEntity>();
		categories.put(ent.getCategory(), ent);
		req.setCategoriesAttributeEntity(categories);

		abstractSet<Attribute> subjectAttrs=req.getCategoriesAttributes().get(AttributeEntity.CAT_SUBJECT);
		assertTrue ("There should be no SUBJECT attributes!", subjectAttrs == null);
		checkTwoAttributes();
	}

	@Test
	public void testSetCategoriesList() {
		abstractMap<Identifier, abstractSet<Attribute>> categories=new abstractMap<Identifier, abstractSet<Attribute>>();
		// Copy the original subject attributes
		categories.put(AttributeEntity.CAT_SUBJECT, req.getCategoriesAttributes().get(AttributeEntity.CAT_SUBJECT));
		// Now set the action attributes
		categories.put(AttributeEntity.CAT_ACTION, createTwoAttributes());
		req.setCategoriesList(categories);
		
		// Now check whether the entities have been created properly
		checkSubjectAndActionAttributes();
	}

	abstractSet<Attribute> createTwoAttributes() {
		abstractSet<Attribute> attrs=new abstractSet<Attribute>();
		// Create Date attribute
		Calendar cc=new GregorianCalendar();
		cc.clear();
		cc.set(112 + 1900, 11, 22, 21, 23, 0);
		Attribute attr=new genericAttribute();
		attr.setType(Attribute.TYPE_DATETIME);
		attr.setIssuer("myissuer");
		attr.setValue(cc);
		attr.setId(Identifier.create ("action-date"));
		attrs.add(attr);
		// Create String attribute
		attr=new genericAttribute();
		attr.setType(Attribute.TYPE_STRING);
		attr.setIssuer("myotherissuer");
		attr.setValue("MyAction");
		attr.setId(Identifier.create ("action-id"));
		attrs.add(attr);
		
		return attrs;

	}
	
	// Utility Functions
	Attribute getAttribute(Identifier category, Identifier id) {
		AttributeEntity attributeEntity = req.getCategoriesEntities().get(
				category);
		if (attributeEntity == null) {
			return null;
		}
		abstractSet<Attribute> attributes = attributeEntity.getAttributes();
		return searchForAttrInSet(id, attributes);
	}
	
	Attribute searchForAttrInSet(Identifier id, abstractSet<Attribute> attrs) {
		for (Attribute a : attrs) {
			if (a.getId().equals(id))
				return a;
		}
		return null;
	}

	void checkTwoAttributes() {
		Attribute attr2=getAttribute (AttributeEntity.CAT_ACTION, Identifier.create ("action-date"));
		assertNotNull ("Attribute1 not found!", attr2);
		Object o=attr2.getValue();
		if (!(o instanceof Calendar))
			fail ("Return value is not Calendar, but "+o.getClass().toString());
		if (!attr2.getType().equals(Attribute.TYPE_DATETIME))
			fail ("Return type is not DATETIME, but "+attr2.getType().toString());
		if (!attr2.getIssuer().equals("myissuer"))
			fail ("Attribute issuer does not match!");
		Calendar cc=new GregorianCalendar();
		cc.clear();
		cc.set(112 + 1900, 11, 22, 21, 23, 0);
		
		assertTrue ("DATE/TIME value does not match!", ((Calendar)o).equals (cc));
		// Check for second attribute
		attr2=getAttribute (AttributeEntity.CAT_ACTION, Identifier.create ("action-id"));
		assertNotNull ("Attribute2 not found!", attr2);
		o=attr2.getValue();
		if (!(o instanceof String))
			fail ("Return value is not String, but "+o.getClass().toString());
		if (!attr2.getType().equals(Attribute.TYPE_STRING))
			fail ("Return type is not STRING, but "+attr2.getType().toString());
		if (!attr2.getIssuer().equals("myotherissuer"))
			fail ("Attribute issuer does not match!");
		assertTrue ("String attribute value does not match!", ((String)o).equals ("MyAction"));
	}

	@SuppressWarnings("rawtypes")
	boolean verifyAttribute(Attribute attr, Class c, Identifier type, String issuer, Object value) {
		Object o=attr.getValue();
		if (!(c.isInstance(o)))
			fail ("Attribute value is not "+c.getName()+", but "+o.getClass().toString());
		if (!attr.getType().equals(type))
			fail ("Attribute type is not "+type+", but "+attr.getType().toString());
		if (issuer == null || !attr.getIssuer().equals(issuer))
			fail ("Attribute issuer does not match!");
		assertTrue ("Attribute value does not match!", value.equals(o));

		return false;
	}

}
