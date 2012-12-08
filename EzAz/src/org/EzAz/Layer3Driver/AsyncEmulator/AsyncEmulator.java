package org.EzAz.Layer3Driver.AsyncEmulator;

import java.util.HashSet;
import java.util.Properties;

import org.EzAz.AdviceObligationHandlers.AdviceObligationHandler;
import org.EzAz.AdviceObligationHandlers.AdviceObligationHandlerRegistryEntry;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.abstractSet;
import org.EzAz.Layer3.AsyncResultListener;
import org.EzAz.Layer3.PDPService;
import org.EzAz.Layer3.operationHandle;

public class AsyncEmulator extends PDPService {
	
	HashSet<AsyncEmulatorOperationHandle> ops=new HashSet<AsyncEmulatorOperationHandle>();

	// Synchronously calls evaluate, stores the result, and then gives back a handle.
	@Override
	public operationHandle asyncEvaluate(Request request) {
		request.setImmutable();
		Response resp=evaluate(request);
		AsyncEmulatorOperationHandle handle=new AsyncEmulatorOperationHandle();
		handle.resp=resp;
		handle.req=request;
		ops.add(handle);
		return handle;
	}

	@Override
	public operationHandle asyncEvaluate(Request request,
			AsyncResultListener listener) {
		AsyncEmulatorOperationHandle handle=
			(AsyncEmulatorOperationHandle) asyncEvaluate(request);
		listener.onComplete(handle, request, handle.resp, null, null);
		return handle;
	}

	@Override
	public operationHandle asyncExtendedOperation(String operation,
			Request request, Object extendedContext,
			AsyncResultListener listener) {
		request.setImmutable();
		Response resp=extendedOperation(operation, request, extendedContext);
		AsyncEmulatorOperationHandle handle=new AsyncEmulatorOperationHandle();
		handle.req=request;
		handle.resp=resp;
		listener.onComplete(handle, request, resp, null, extendedContext);
		return handle;
	}

	@Override
	public synchronized void cancel(operationHandle opHandle) {
		if (ops.contains(opHandle)) {
			ops.remove(opHandle);
		} else {
			throw new RuntimeException ("Operation Handle doesn't exist!");
		}
	}

	@Override
	public Response evaluate(Request request) {
		return evaluate(request, null, null, null);
	}

	@Override
	public Response evaluate(Request request, Object appContext) {
		return evaluate(request, null, null, null);
	}

	@Override
	public Response evaluate(Request request, Object appContext,
			abstractSet<AdviceObligationHandler> obligationHandlers,
			abstractSet<AdviceObligationHandler> adviceListeners) {
		throw new RuntimeException ("AsyncEmulator doesn't provide an evaluate() function!");
	}

	@Override
	public Response extendedOperation(String operation, Request request,
			Object extendedContext) {
		return extendedOperation(operation, request, null, null, null, null);
	}

	@Override
	public Response extendedOperation(String operation, Request request,
			Object extendedContext, Object appContext) {
		return extendedOperation(operation, request, null, null, null, null);
	}

	@Override
	public Response extendedOperation(String operation, Request request,
			Object extendedContext, Object appContext,
			abstractSet<AdviceObligationHandler> adviceHandlers,
			abstractSet<AdviceObligationHandler> obligationHandlers) {
		throw new RuntimeException ("AsyncEmulator doesn't provide an extendedOperation() function!");
	}

	@Override
	public boolean isDone(operationHandle opHandle) {
		if (ops.contains(opHandle)) {
			return false;
		} else {
			throw new RuntimeException("Unknown operation handle");
		}
	}

	@Override
	public void setupConnection(Properties properties) {
	}

	@Override
	public Response waitOn(operationHandle opHandle) {
		ops.remove(opHandle);
		return ((AsyncEmulatorOperationHandle)opHandle).resp;
	}

	@Override
	public void addHandler(Identifier id, AdviceObligationHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addHandler(AdviceObligationHandlerRegistryEntry registryEntry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public abstractSet<AdviceObligationHandlerRegistryEntry> getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

}
