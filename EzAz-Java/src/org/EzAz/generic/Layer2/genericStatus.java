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
import java.io.PrintStream;

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

	public static void prettyPrint(PrintStream ps, String header, Status status) {
		if (status == null)
			return;
		String tmp=status.getStatusCode();
		ps.println(header+"STATUS CODE: "+(tmp != null? tmp : ""));
		tmp=status.getStatusDetail();
		ps.println(header+"STATUS DETAIL: "+(tmp != null? tmp : ""));
		tmp=status.getStatusMessage();
		ps.println(header+"STATUS MESSAGE: "+(tmp != null? tmp : ""));
	}

}
