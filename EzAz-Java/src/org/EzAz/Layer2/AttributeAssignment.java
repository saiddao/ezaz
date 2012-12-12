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
public interface AttributeAssignment {

	/**
	 * Returns the Attribute of the assignment.
	 */
	public Attribute getAttribute();

	/**
	 * Returns the ID of the category of the Attribute in the Assignment.
	 */
	public Identifier getCategory();

	/**
	 * Sets the Attribute in the Assignment.
	 * 
	 * @param attribute    The Attribute for the Assignment.
	 */
	public void setAttribute(Attribute attribute);

	/**
	 * Sets the ID of the category of the Attribute in the Assignment.
	 * 
	 * @param category    The ID of the category.
	 */
	public void setCategory(Identifier category);

}