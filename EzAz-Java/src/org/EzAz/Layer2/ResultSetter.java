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
 * This interface is meant to be used for Layer 3 implementers that need to
 * construct a result that corresponds to a request issued by Layer 2 users.
 * @author felix
 * @version 1.0
 * @created 12-Dec-2012 22:54:49
 */
public interface ResultSetter {

	/**
	 * Adds an associated advice to a result.
	 * 
	 * @param advice    The advice to add to the result.
	 */
	public void addAdvice(AdviceObligation advice);

	/**
	 * Adds an obligation to a result.
	 * 
	 * @param obligation    The obligation to add to the result.
	 */
	public void addObligation(AdviceObligation obligation);

	/**
	 * Indicates whether the result object is mutable (i.e. can be changed). If this
	 * function returns false, then the result object must not be modified - else an
	 * exception is thrown.
	 */
	public boolean isMutable();

	/**
	 * Replaces the associated advice for the result with a list of advice in the
	 * parameter. Any previously attached advice will no longer be attached to the
	 * result.
	 * 
	 * @param advice    An abstract set of advice objects to attach to the result.
	 */
	public void setAssociatedAdvice(abstractSet<AdviceObligation> advice);

	/**
	 * Set the decision of the result.
	 * 
	 * @param decision    The decision for the result. This must be one of Result.
	 * RESULT_PERMIT, Result.RESULT_DENY, Result.RESULT_NOT_APPLICABLE or
	 * RESULT_INDETERMINATE.
	 */
	public void setDecision(int decision);

	/**
	 * This method sets the result object to be immutable. This is not reversible.
	 */
	public void setImmutable();

	/**
	 * Replaces the obligations for the result with a list of advice in the parameter.
	 * Any previously attached obligations will no longer be attached to the result.
	 * 
	 * @param obligationSet    An abstract set of obligations to attach to the result.
	 */
	public void setObligations(abstractSet<AdviceObligation> obligationSet);

	/**
	 * Adds policy identifier list to the current response. This should be done
	 * whenever the corresponding request's getReturnPolicyIdList() method returns
	 * true.
	 * 
	 * @param policyIdentifiers    An abstract set containing policy id references.
	 */
	public void setPolicyIdentifierList(abstractSet<IdReference> policyIdentifiers);

	/**
	 * Sets the status of the result.
	 * 
	 * @param status    The status for the result.
	 */
	public void setStatus(Status status);

}