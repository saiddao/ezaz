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
 * @created 05-Dec-2012 00:00:57
 */
public interface IdReferenceSetter {

	/**
	 * Sets the earliest version attribute.
	 * 
	 * @param earliestVersion    earliestVersion
	 */
	public void setEarliestVersion(String earliestVersion);

	/**
	 * Set the latest version attribute.
	 * 
	 * @param latestVersion    latestVersion
	 */
	public void setLatestVersion(String latestVersion);

	/**
	 * Sets the reference.
	 * 
	 * @param reference    reference
	 */
	public void setReference(Identifier reference);

	/**
	 * Sets the version.
	 * 
	 * @param version    version
	 */
	public void setVersion(String version);

}