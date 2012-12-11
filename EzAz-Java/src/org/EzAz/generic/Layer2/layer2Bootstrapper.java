package org.EzAz.generic.Layer2;

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
}
