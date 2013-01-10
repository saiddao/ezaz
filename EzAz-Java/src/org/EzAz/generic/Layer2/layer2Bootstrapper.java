package org.EzAz.generic.Layer2;
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
@SuppressWarnings("rawtypes")
public class layer2Bootstrapper extends org.EzAz.Layer2.layer2Bootstrapper {
	public void init() {
		
	}
	
	public Class classRequest() {
		return genericRequest.class;
	}

	public Class classResponse() {
		return genericResponse.class;
	}
	public Class classAttribute() {
		return genericAttribute.class;
	}

	public Class classResult() {
		return genericResult.class;
	}

	@Override
	public Class classAttributeAssignment() {
		return genericAttributeAssignment.class;
	}

	@Override
	public Class classAttributeEntity() {
		return genericAttributeEntity.class;
	}

	@Override
	public Class classIdReference() {
		return genericIdReference.class;
	}

	@Override
	public Class classStatus() {
		return genericStatus.class;
	}

	@Override
	public Class classAdviceObligation() {
		return genericAdviceObligation.class;
	}
}
