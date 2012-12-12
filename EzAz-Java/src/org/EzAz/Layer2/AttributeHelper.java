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
public interface AttributeHelper {

	/**
	 * Add an attribute.
	 * 
	 * @param category    The ID of the category.
	 * @param id    The ID of the attribute.
	 * @param issuer    The issuer of the Attribute.
	 * @param type    The data type of the attribute.
	 * @param value    The attribute's value.
	 */
	public Attribute addAttribute(Identifier category, Identifier id, String issuer, Identifier type, Object value);

	/**
	 * Add an attribute of type Boolean.
	 * 
	 * @param category    The ID of the category.
	 * @param id    The ID of the attribute.
	 * @param issuer    The issuer of the Attribute.
	 * @param val    The attribute's Boolean value.
	 */
	public Attribute addAttribute(Identifier category, Identifier id, String issuer, boolean val);

	/**
	 * Add an attribute of type String.
	 * 
	 * @param category    The ID of the category.
	 * @param id    The ID of the attribute.
	 * @param issuer    The issuer of the Attribute.
	 * @param val    The attribute's Integer value.
	 */
	public Attribute addAttribute(Identifier category, Identifier id, String issuer, int val);

	/**
	 * Add an attribute of type String.
	 * 
	 * @param category    The ID of the category.
	 * @param id    The ID of the attribute.
	 * @param issuer    The issuer of the Attribute.
	 * @param val    The Attribute's String value.
	 */
	public Attribute addAttribute(Identifier category, Identifier id, String issuer, String val);

	/**
	 * Add an attribute.
	 * 
	 * @param category    The ID of the category.
	 * @param attr    The Attribute to be added.
	 */
	public Attribute addAttribute(Identifier category, Attribute attr);

	/**
	 * Add an entire category using a list of Attributes.
	 * 
	 * @param cat    The ID of the category.
	 * @param attributes    A list of Attributes.
	 * @exception Exception Exception
	 */
	public void addCategory(Identifier cat, abstractSet<Attribute> attributes)
	  throws Exception;

	/**
	 * Add an entire category using an AttributeEntity.
	 * 
	 * @param entity    An AttributeEntity that contains all the Attributes.
	 * @exception Exception Exception
	 */
	public void addCategory(AttributeEntity entity)
	  throws Exception;

	/**
	 * Clone a request.
	 * 
	 * @param request    Original Request to create a copy of.
	 */
	public Request clone(Request request);

	/**
	 * Retrieve all attributes in a Map of <Identifier,List<Attribute>> where the
	 * Identifier denotes the Attribute's ID.
	 */
	public abstractMap<Identifier,abstractSet<Attribute>> getCategoriesAttributes();

	/**
	 * Use this function to retrieve all attributes grouped by category. The function
	 * returns a Map of type <Identifier,AttributeEntity> where Identifier denotes the
	 * category.
	 */
	public abstractMap<Identifier,AttributeEntity> getCategoriesEntities();

	/**
	 * Removes an attribute.
	 * 
	 * @param category    The ID of the category.
	 * @param id    The ID of the attribute.
	 * @param issuer    The issuer of the Attribute.
	 */
	public void removeAttribute(Identifier category, Identifier id, String issuer);

	/**
	 * Set the entire categories of a request. The categories are supplied as a
	 * Map<Identifier, AttributeEntity>.
	 * 
	 * @param categories    A Map containing all categories to set for this request.
	 */
	public void setCategoriesAttributeEntity(abstractMap<Identifier, AttributeEntity> categories);

	/**
	 * Set the entire categories of a request. The categories are supplied as a
	 * Map<Identifier, List<Attributes>>.
	 * 
	 * @param categories    A Map containing all categories to set for this request.
	 */
	public void setCategoriesList(abstractMap<Identifier,abstractSet<Attribute>> categories);

	/**
	 * This method sets the request to be immutable. This is not reversible, however
	 * it is of course always possible to clone() the request, in which case a mutable
	 * copy will be created.
	 */
	public void setImmutable();

}