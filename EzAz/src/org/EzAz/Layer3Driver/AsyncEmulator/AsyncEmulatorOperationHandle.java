package org.EzAz.Layer3Driver.AsyncEmulator;

import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer3.operationHandle;

public class AsyncEmulatorOperationHandle implements operationHandle {
	static private long uniqueId = 0;
	public Request req;
	public Response resp;
	long id;

	public AsyncEmulatorOperationHandle() {
		id=nextValue();
	}

	public synchronized static long nextValue() {
		uniqueId++;
		return uniqueId;
	}

}
