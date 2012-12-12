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
public interface Response {

	/**
	 * Returns the number of results in this response. In general, single (i.e. not
	 * multiple request profile type requests) will return a single response.
	 */
	public int getResultLength();

	/**
	 * Returns an abstractSet of results in this response.
	 */
	public abstractSet<Result> getResults();

	/**
	 * Convenience function that checks all results, invokes all handlers for advice
	 * and obligation, and returns true in case of a permit and false in case of a
	 * deny, according to the bias.
	 * 
	 * @param bias    Specifies the bias. If the overall result is
	 * RESULT_INDETERMINATE, RESULT_NOT_APPLICAPLE, or if not all obligations have
	 * been handled, then the outcome of the decision will be as indicated in the bias.
	 */
	public boolean isAllowed(boolean bias);

}