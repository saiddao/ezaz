package org.EzAz.Layer3;
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
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.Request;

/**
 * @author felix
 * @version 1.0
 * @created 05-Dec-2012 00:03:53
 */
public interface AsyncResultListener {

	/**
	 * Notifies that a result to an outstanding asynchronous operation has been
	 * received. All handlers get invoked before this function gets called.
	 * 
	 * @param requestID    The async request ID of the request for which the response
	 * was received.
	 * @param request
	 * @param result    The result that has been received.
	 * @param appContext
	 * @param extendedContext    extendedContext
	 */
	public void onComplete(operationHandle requestID, Request request, Response result, Object appContext, Object extendedContext);

	/**
	 * Notifies that an error occurred in the processing of the request. This must NOT
	 * happen if the PDP returns an error, because in that case we must get back a
	 * result with a status that would indicate an error. Instead, this must only be
	 * invoked if, for example, there is a network or system error and the PDPservice
	 * cannot communicate with the PDP.
	 * 
	 * @param requestID    The async request ID of the request for which the response
	 * was received
	 * @param e    The Exception that has been generated.
	 */
	public void onError(operationHandle requestID, Exception e);

}