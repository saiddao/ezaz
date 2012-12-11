package org.EzAz.generic.Layer2;

import org.EzAz.Layer2.Status;
import org.EzAz.Layer2.StatusSetter;

public class genericStatus implements Status, StatusSetter {

	private String statusCode;
	private String statusDetail;
	private String statusMessage;

	@Override
	public void setStatusCode(String statusCode) {
		this.statusCode=statusCode;
	}

	@Override
	public void setStatusDetail(String statusDetail) {
		this.statusDetail=statusDetail;
	}

	@Override
	public void setStatusMessage(String message) {
		this.statusMessage=message;
	}

	@Override
	public String getStatusCode() {
		return statusCode;
	}

	@Override
	public String getStatusDetail() {
		return statusDetail;
	}

	@Override
	public String getStatusMessage() {
		return statusMessage;
	}

}
