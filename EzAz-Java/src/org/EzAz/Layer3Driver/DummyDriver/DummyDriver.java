package org.EzAz.Layer3Driver.DummyDriver;

import java.util.Properties;

import org.EzAz.AdviceObligationHandlers.AdviceObligationHandler;
import org.EzAz.Layer2.Identifier;
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
