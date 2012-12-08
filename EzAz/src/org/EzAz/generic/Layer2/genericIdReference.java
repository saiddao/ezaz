package org.EzAz.generic.Layer2;

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
