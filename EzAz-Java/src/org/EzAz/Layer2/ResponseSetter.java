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
 * construct a response that corresponds to a request issued by Layer 2 users.
 * @author felix
 * @version 1.0
 * @created 05-Dec-2012 00:00:58
 */
public interface ResponseSetter {

	/**
	 * Add a single result object to a response.
	 * 
	 * @param result    The result object to add to the response.
	 */
	public void addResult(Result result);

	/**
	 * Adds an abstract set of result objects to the response. These will be added to
	 * any results already on the response - i.e. any preexisting result objects in
	 * the response will not be removed.
	 * 
	 * @param results    An abstract set containing result objects.
	 */
	public void addResults(abstractSet<Result> results);

	/**
	 * Indicates whether the response object is mutable (i.e. can be changed). If this
	 * function returns false, then the response object must not be modified - else an
	 * exception is thrown.
	 */
	public boolean isMutable();

	/**
	 * This method sets the response object to be immutable. This is not reversible.
	 */
	public void setImmutable();

	/**
	 * Replaces all result objects attached to this reponse with those given in the
	 * list that is passed in as a parameter.
	 * 
	 * @param results    Ab abstract set of results to attach to the response. This
	 * will remove all results that were attached to the response object previously.
	 */
	public void setResults(abstractSet<Result> results);

}