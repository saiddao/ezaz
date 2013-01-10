package org.EzAz.Layer3Driver.AsyncEmulator;
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
