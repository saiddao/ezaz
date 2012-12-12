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
@SuppressWarnings("rawtypes")
public abstract class layer2Bootstrapper {

	public layer2Bootstrapper(){

	}

	public void init(){

	}

	/**
	 * Retrieves the Request class.
	 */
	abstract public Class classRequest();

	/**
	 * Retrieves the Attribute class.
	 */
	abstract public Class classAttribute();

	/**
	 * Retrieves the Response class.
	 */
	abstract public Class classResponse();

	/**
	 * Retrieves the Response class.
	 */
	abstract public Class classResult();

	public Object create(Object o) throws Exception {
		try {
			if (o instanceof AdviceObligation)
				return classAdviceObligation().newInstance();
			else if (o instanceof Attribute)
				return classAttribute().newInstance();
			else if (o instanceof AttributeAssignment)
				return classAttributeAssignment().newInstance();
			else if (o instanceof AttributeEntity)
				return classAttributeEntity().newInstance();
			else if (o instanceof IdReference)
				return classIdReference().newInstance();
			else if (o instanceof Request)
				return classRequest().newInstance();
			else if (o instanceof Response)
				return classResponse().newInstance();
			else if (o instanceof Result)
				return classResult().newInstance();
			else if (o instanceof Status)
				return classStatus().newInstance();
			else
				throw new RuntimeException ("Cannot instantiate unknown class: "+o.getClass().getName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	abstract public Class classAttributeAssignment();

	abstract public Class classAttributeEntity();

	abstract public Class classIdReference();

	abstract public Class classStatus();

	abstract public Class classAdviceObligation();

}