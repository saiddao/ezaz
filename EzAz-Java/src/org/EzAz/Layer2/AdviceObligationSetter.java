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
import org.EzAz.Layer2.abstractSet;

/**
 * @author felix
 * @version 1.0
 * @created 13-Dec-2012 19:35:24
 */
public interface AdviceObligationSetter {

	/**
	 * Set the Advice/Obligation Id.
	 * 
	 * @param id
	 */
	public void setId(Identifier id);

	/**
	 * Sets the list of attribute assignments.
	 * 
	 * @param attributeAssignments
	 */
	public void setAttributeAssignments(abstractSet<AttributeAssignment> attributeAssignments);

}