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
import java.util.HashSet;
import java.util.Properties;

import org.EzAz.AdviceObligationHandlers.AdviceObligationHandler;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.abstractSet;
import org.EzAz.Layer3.AsyncResultListener;
import org.EzAz.Layer3.PDPService;
import org.EzAz.Layer3.operationHandle;

public class AsyncEmulator extends PDPService {

    HashSet<AsyncEmulatorOperationHandle> ops = new HashSet<AsyncEmulatorOperationHandle>();

    // Synchronously calls evaluate, stores the result, and then gives back a
    // handle.
    @Override
    public operationHandle asyncEvaluate(Request request) throws Exception {
	request.setImmutable();
	Response resp = evaluate(request);
	AsyncEmulatorOperationHandle handle = new AsyncEmulatorOperationHandle();
	handle.resp = resp;
	handle.req = request;
	ops.add(handle);
	return handle;
    }

    @Override
    public operationHandle asyncEvaluate(Request request, AsyncResultListener listener) throws Exception {
	AsyncEmulatorOperationHandle handle = (AsyncEmulatorOperationHandle) asyncEvaluate(request);
	listener.onComplete(handle, request, handle.resp, null, null);
	return handle;
    }

    @Override
    public operationHandle asyncExtendedOperation(String operation, Request request, Object extendedContext,
	    AsyncResultListener listener) throws Exception {
	request.setImmutable();
	Response resp = extendedOperation(operation, request, extendedContext);
	AsyncEmulatorOperationHandle handle = new AsyncEmulatorOperationHandle();
	handle.req = request;
	handle.resp = resp;
	listener.onComplete(handle, request, resp, null, extendedContext);
	return handle;
    }

    @Override
    public synchronized void cancel(operationHandle opHandle) {
	if (ops.contains(opHandle)) {
	    ops.remove(opHandle);
	} else {
	    throw new RuntimeException("Operation Handle doesn't exist!");
	}
    }

    @Override
    public Response evaluate(Request request) throws Exception {
	return evaluate(request, null, null, null);
    }

    @Override
    public Response extendedOperation(String operation, Request request, Object extendedContext) throws Exception {
	return extendedOperation(operation, request, null, null, null, null);
    }

    @Override
    public Response extendedOperation(String operation, Request request, Object extendedContext, Object appContext)
	    throws Exception {
	return extendedOperation(operation, request, null, null, null, null);
    }

    @Override
    public Response extendedOperation(String operation, Request request, Object extendedContext, Object appContext,
	    abstractSet<AdviceObligationHandler> adviceHandlers, abstractSet<AdviceObligationHandler> obligationHandlers)
	    throws Exception {
	throw new RuntimeException("AsyncEmulator doesn't provide an extendedOperation() function!");
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
	return ((AsyncEmulatorOperationHandle) opHandle).resp;
    }

    @Override
    public void addHandler(AdviceObligationHandler registryEntry) {
	// TODO Auto-generated method stub

    }

    @Override
    public abstractSet<AdviceObligationHandler> getHandlers() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Response evaluateImpl(Request request, Object appContext) {
	throw new RuntimeException("AsyncEmulator doesn't provide an evaluateImpl() function!");
    }

}
