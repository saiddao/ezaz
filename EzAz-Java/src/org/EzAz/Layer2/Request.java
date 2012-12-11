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
 * @created 05-Dec-2012 00:00:58
 */
public interface Request {

	/**
	 * Retrieves all categories of the request, as an abstractMap of all
	 * AttributeEntities.
	 */
	public abstractMap<Identifier, AttributeEntity> getCategoriesEntities();

	/**
	 * Indicates whether the combined decision flag has been set. This attribute is
	 * used to request that the PDP combines multiple decisions into a single decision.
	 * The use of this attribute is specified in the XACML 3.0 multiple decision
	 * profile. If the PDP does not implement the relevant functionality in the XACML
	 * 3.0 multiple decision profile, then the PDP must return an Indeterminate with a
	 * status code of urn:oasis:names:tc:xacml:1.0:status:processing-error if it
	 * receives a request with this attribute set to “true”.
	 */
	public boolean getCombinedDecision();

	/**
	 * In case that this request makes use of the repeated attribute categories as
	 * specified in the XACML 3.0 Multiple Decision Profile, this method will return
	 * an abstractMap of repeated categories.
	 */
	public abstractSet<AttributeEntity> getRepeatedCategories();

	/**
	 * Indicates whether a flag has been set that indicates that the response shall
	 * return a policy ID list (a list of XACML policies that were evaluated in order
	 * to generate the result).
	 */
	public boolean getReturnPolicyIdList();

	/**
	 * Returns the XACML Version used.
	 */
	public int getXacmlVersion();

	/**
	 * Indicates whether the Request has repeated categories (used by the XACML 3.0
	 * Multiple Decision Profile).
	 */
	public boolean hasRepeatedCategories();

	/**
	 * Indicates whether this request is a multiple decision type request, as
	 * specified in the XACML 3.0 Multiple Decision Profile.
	 */
	public boolean isMultipleRequest();

	/**
	 * Indicates whether the request object is mutable (i.e. can be changed). If this
	 * function returns false, then the request object must not be modified - else an
	 * exception is thrown.
	 */
	public boolean isMutable();

	/**
	 * Sets a flag to request that the PDP combines multiple decisions into a single
	 * decision. The use of this attribute is specified in the XACML 3.0 multiple
	 * decision profile . If the PDP does not implement the relevant functionality in
	 * the XACML 3.0 multiple decision profile, then the PDP must return an
	 * Indeterminate with a status code of urn:oasis:names:tc:xacml:1.0:status:
	 * processing-error if it receives a request with this attribute set to “true”.
	 * 
	 * @param combinedDecision    combinedDecision
	 * @exception genericLayer2Exception genericLayer2Exception
	 */
	public void setCombinedDecision(boolean combinedDecision)
	  throws Exception;

	/**
	 * This method sets the request object to be immutable. This is not reversible,
	 * however it is of course always possible to clone() the request, in which case a
	 * mutable copy will be created.
	 */
	public void setImmutable();

	/**
	 * Sets a flag indicating that the response shall return a policy ID list (a list
	 * of XACML policies that were evaluated in order to generate the result).
	 * 
	 * @param returnPolicyIdList    returnPolicyIdList
	 * @exception genericLayer2Exception genericLayer2Exception
	 */
	public void setReturnPolicyIdList(boolean returnPolicyIdList)
	  throws Exception;

	/**
	 * Returns the category ID that is used for the repeated categories as defined in
	 * the XACML v3.0 Multiple Decision Profile Version 1.0.
	 */
	public Identifier getRepeatedCategoryId();

	/**
	 * Sets the Id of the category that will be repeated. This effectively turns the
	 * request into a multiple decision request according to the XACML v3.0 Multiple
	 * Decision Profile Version 1.0.
	 * In regular (i.e. non-multiple decision XACML requests) every AttributeEntity
	 * has to have a different Identifier. In a multi-request, there can be multiple
	 * instances of an AttributeEntity with the same Identifier. This method
	 * identifies the Identifier of the category that will be repeated. Therefore,
	 * this request should be treated by the PDP as if it were multiple requests where
	 * each request has the same set of non-repeated categories, and one instance of
	 * the repeated category.
	 * Calling this function will do the following:
	 * If there is already an AttributeEntity in the request with the same Identifier
	 * as given to this method, the AttributeEntity will be moved into a set of
	 * repeated attribute categories and will be the "current" AttributeEntity. Every
	 * attribute added to the repeated category will then be added to the current
	 * AttributeEntity. To create another instance of a repeated AttributeEntity, call
	 * the createNewRepeatedEntity() method. After doing so, every attribute added to
	 * the repeated category will then be added to the newly created AttributeEntity.
	 * 
	 * <b>This Id can only be set once per request!</b> Attempting to call this
	 * function for a second time will render an Exception.
	 * 
	 * @param id
	 */
	public void setRepeatedCategoryId(Identifier id);

	/**
	 * Creates a new AttributeEntity with the repeated category and adds it to the end
	 * of the repeated category set.
	 * 
	 * Use this function as follows:
	 * 
	 * 1. Call setRepeatedCategoryId(Identifier) once on the request to turn the
	 * request into a multi-request. If there is already an AttributeEntity with the
	 * given Identifier, it will be the first in the set of repeated category
	 * AttributeEntities. Then call this method (createNewRepeatedEntity) every time
	 * you need to create a new repeated AttributeEntity (which will automatically be
	 * assigned the same Identifier that you have specified with the
	 * setRepeatedCategoryId() method). Then, every subsequent call to the
	 * AttributeHelper interface in order to add attributes of that category will add
	 * the attribute to the new AttributeEntity.
	 */
	public void createNewRepeatedEntity();

}