package org.EzAz.Layer3Driver.DummyDriver;
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
import java.util.Properties;

import org.EzAz.AdviceObligationHandlers.AdviceObligationHandler;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.Result;
import org.EzAz.Layer2.abstractSet;
import org.EzAz.Layer3Driver.AsyncEmulator.AsyncEmulator;
import org.EzAz.generic.Layer2.genericLayer2RuntimeException;
import org.EzAz.generic.Layer2.genericResponse;
import org.EzAz.generic.Layer2.genericResult;
import org.EzAz.generic.Layer2.genericStatus;

public class DummyDriver extends AsyncEmulator {

	public DummyDriver() {
		
	}
	
	@Override
	public Response evaluate(Request request) {
		request.setImmutable();
		genericResponse resp=new genericResponse();
		genericResult result=new genericResult();
		result.setDecision(Result.RESULT_PERMIT);
		// Create status
		genericStatus status=new genericStatus();
		status.setStatusCode("urn:oasis:names:tc:xacml:1.0:status:ok");
		status.setStatusMessage("OK");
		result.setStatus(status);
		resp.addResult(result);
		return resp;
	}

	@Override
	public Response evaluate(Request request, Object appContext) {
		return evaluate(request);
	}

	@Override
	public Response extendedOperation(String operation, Request request,
			Object extendedContext) {
		throw new genericLayer2RuntimeException("AxiomaticsDriver does not implement extendedOperation!");
	}

	@Override
	public Response extendedOperation(String operation, Request request,
			Object extendedContext, Object appContext) {
		throw new genericLayer2RuntimeException("AxiomaticsDriver does not implement extendedOperation!");
	}

	@Override
	public Response evaluate(Request request, Object appContext,
			abstractSet<AdviceObligationHandler> obligationHandlers,
			abstractSet<AdviceObligationHandler> adviceListeners) {
		return evaluate(request);
	}

	@Override
	public Response extendedOperation(String operation, Request request,
			Object extendedContext, Object appContext,
			abstractSet<AdviceObligationHandler> adviceHandlers,
			abstractSet<AdviceObligationHandler> obligationHandlers) {
		throw new genericLayer2RuntimeException("AxiomaticsDriver does not implement extendedOperation!");
	}

	@Override
	public void setupConnection(Properties properties) {
	}

}
