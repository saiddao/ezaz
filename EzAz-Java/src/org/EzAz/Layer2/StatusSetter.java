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
 * construct a status that is needed for every result object.
 * @author felix
 * @version 1.0
 * @created 05-Dec-2012 00:00:58
 */
public interface StatusSetter {

	/**
	 * Sets the status code.
	 * 
	 * @param statusCode    The status code.
	 */
	public void setStatusCode(String statusCode);

	/**
	 * Sets the status detail.
	 * 
	 * @param statusDetail    The status detail.
	 */
	public void setStatusDetail(String statusDetail);

	/**
	 * Sets the status message.
	 * 
	 * @param message    The status message.
	 */
	public void setStatusMessage(String message);

}