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
import org.EzAz.Layer2.IdReference;
import org.EzAz.Layer2.IdReferenceSetter;
import org.EzAz.Layer2.Identifier;

public class genericIdReference implements IdReference, IdReferenceSetter {
	
	String earliestVersion;
	String latestVersion;
	String version;
	Identifier id;
	int type;
	

	@Override
	public String getEarliestVersion() {
		return earliestVersion;
	}

	@Override
	public String getLatestVersion() {
		return latestVersion;
	}

	@Override
	public Identifier getReference() {
		return id;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setEarliestVersion(String earliestVersion) {
		this.earliestVersion=earliestVersion;
	}

	@Override
	public void setLatestVersion(String latestVersion) {
		this.latestVersion=latestVersion;		
	}

	@Override
	public void setReference(Identifier reference) {
		id=reference;
	}

	@Override
	public void setVersion(String version) {
		this.version=version;
	}

}
