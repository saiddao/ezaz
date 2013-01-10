package org.EzAz.Layer2;
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

/**
 * @author felix
 * @version 1.0
 * @created 10-Jan-2013 17:55:56
 */
public interface Result {

	public static final int RESULT_PERMIT = 1;
	public static final int RESULT_DENY = 2;
	public static final int RESULT_NOT_APPLICABLE = 3;
	public static final int RESULT_INDETERMINATE = 4;

	/**
	 * Returns the associated Advice from the Response as an abstractSet of type
	 * AdviceObligation.
	 */
	public abstractSet<AdviceObligation> getAssociatedAdvice();

	/**
	 * Returns the decision of the request. This will either be RESULT_PERMIT,
	 * RESULT_DENY, RESULT_NOT_APPLICABLE or RESULT_INDETERMINATE.
	 */
	public int getDecision();

	/**
	 * Returns the associated Obligations from the Response as an abstractSet of type
	 * AdviceObligation. NOTE: The XACML 3 specifications states that the Result may
	 * contain a set of obligation expressions that MUST be evaluated into obligations
	 * by the PDP and the resulting obligations MUST be fulfilled by the PEP in
	 * conjunction with the authorization decision. If the PEP does not understand or
	 * cannot fulfill any of the obligations, then it MUST act according to the PEP
	 * bias. See also the isAllowed() convenience function that will automatically
	 * invoke obligation handlers.
	 */
	public abstractSet<AdviceObligation> getObligations();

	/**
	 * Returns the policy ID list, i.e. the IDs of the policies that were evaluated in
	 * order to achieve the result.
	 */
	public abstractSet<IdReference> getPolicyIdentifierList();

	/**
	 * Retrieves the status of the Result.
	 */
	public Status getStatus();

	/**
	 * If the isAllowed() convenience function is called, the framework will
	 * automatically invoke any advice and obligation handlers, and will check that
	 * all obligations are handled. If not all obligations are handled, then the
	 * isAllowed() function will return a boolean value according to the bias. The
	 * bias parameter indicates whether RESULT_INDETERMINATE , RESULT_NOT_APPLICABLE
	 * or a failure to handle all obligations should lead to a permit or a deny. The
	 * function will return a boolean value indicating true in case of a permit, and
	 * false in case of a deny.
	 * 
	 * @param bias    bias
	 */
	public boolean isAllowed(boolean bias);

	/**
	 * Retrieves the AttributeEntities (i.e. Attribute Categories with Attributes)
	 * that may be attached to the Result. This will happen when the IncludeInResults
	 * flag has been set on an attribute.
	 */
	public abstractMap<Identifier,AttributeEntity> getCategoriesEntities();

}