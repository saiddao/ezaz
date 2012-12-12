package org.EzAz.Layer2;
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

/**
 * @author felix
 * @version 1.0
 * @created 12-Dec-2012 22:54:49
 */
public interface AttributeEntity {

	public static Identifier CAT_SUBJECT = Identifier.create ("urn:oasis:names:tc:xacml:1.0:subject-category:access-subject");
	public static Identifier CAT_RESOURCE = Identifier.create ("urn:oasis:names:tc:xacml:3.0:attribute-category:resource");
	public static Identifier CAT_ACTION = Identifier.create ("urn:oasis:names:tc:xacml:3.0:attribute-category:action");
	public static Identifier CAT_ENVIRONMENT = Identifier.create ("urn:oasis:names:tc:xacml:3.0:attribute-category:environment");

	/**
	 * Returns all Attributes in this AttributeEntity as a Set.
	 */
	public abstractSet<Attribute> getAttributes();

	/**
	 * Returns the ID of the category of this AttributeEntity.
	 */
	public Identifier getCategory();

	/**
	 * Returns the <Content> for this AttributeEntity. This is returned as a Java
	 * Object.
	 */
	public Object getContent();

	/**
	 * Sets all Attributes for this AttributeEntity at once.
	 * 
	 * @param attributes    A set of Attributes for this AttributeEntity.
	 */
	public void setAttributes(abstractSet<Attribute> attributes);

	/**
	 * Sets the ID of the category of this AttributeEntity.
	 * 
	 * @param id    id
	 */
	public void setCategory(Identifier id);

	/**
	 * Sets the Content for this AttributeEntity.
	 * 
	 * @param content    An Object with the Content to set for this AttributeEntity.
	 * Depending on the Layer 3 implementation, this could either be a String
	 * containing XML, or some object representing an XML node.
	 */
	public void setContent(Object content);

}