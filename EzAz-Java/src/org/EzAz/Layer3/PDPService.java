package org.EzAz.Layer3;
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

import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.abstractSet;
import org.EzAz.AdviceObligationHandlers.AdviceObligationHandler;
import org.EzAz.Layer2.Identifier;
import org.EzAz.AdviceObligationHandlers.AdviceObligationHandlerRegistryEntry;
import org.EzAz.AdviceObligationHandlers.AdviceObligationHandlerRegistry;

/**
 * An interface for the connection to the PDP.
 * @author felix
 * @version 1.0
 * @created 12-Dec-2012 22:54:49
 */
public abstract class PDPService implements AdviceObligationHandlerRegistry {

	public PDPService(){

	}

	/**
	 * An asynchronous way to send an evaluate request to a PDP. Returns an
	 * operationHandle.
	 * @return An operationHandle that can be used to track the execution
	 * 
	 * @param request    The actual XACML request
	 */
	public abstract operationHandle asyncEvaluate(Request request) throws Exception;

	/**
	 * An asynchronous way to send an evaluate request to a PDP. Returns an
	 * operationHandle. This method also takes a AsyncResultListener as an argument,
	 * which will then get called when the operation completes.
	 * @return An operationHandle that can be used to track the execution
	 * 
	 * @param request    The actual XACML request
	 * @param listener    that will get notified when the operation completes
	 */
	public abstract operationHandle asyncEvaluate(Request request, AsyncResultListener listener) throws Exception;

	/**
	 * The asynchronous way to send an extended operation to a PDP. Returns an
	 * operationHandle.
	 * @return An operationHandle that can be used to track the execution of the
	 * operation.
	 * 
	 * @param operation    The extended operation, represented as a String.
	 * @param request    The Request Object.
	 * @param extendedContext    Any additional context that may be relevant for the
	 * extended operation.
	 * @param listener    A listener that will get notified when the operation
	 * completes.
	 */
	public abstract operationHandle asyncExtendedOperation(String operation, Request request, Object extendedContext, AsyncResultListener listener) throws Exception;

	/**
	 * Cancels a request that is already in process. <b>This may or may not be
	 * supported by the PDP, meaning that the PDP might still evaluate the request.
	 * </b>
	 * 
	 * @param opHandle    opHandle
	 */
	public abstract void cancel(operationHandle opHandle);

	/**
	 * Sends the request to the PDP for evaluation. Simplest synchronous operation
	 * mode.
	 * 
	 * @param request    The Request Object.
	 */
	public abstract Response evaluate(Request request) throws Exception;

	/**
	 * Sends the request to the PDP for evaluation in synchronous mode. Allows to
	 * specify an Object that will then be passed to every AdviceObligationHandler
	 * that may be called due to Advice/Obligations in the Result(s).
	 * 
	 * @param request    The Request Object.
	 * @param appContext    An application specific Object that is passed to every
	 * Advice/Obligation Handler that gets called when the response is received. May
	 * be null.
	 */
	public abstract Response evaluate(Request request, Object appContext) throws Exception;

	/**
	 * Sends the request to the PDP for evaluation in synchronous mode. Allows to
	 * specify an Object that will then be passed to every AdviceObligationHandler
	 * that may be called due to Advice/Obligations in the Result(s). Also allows a
	 * list of specific Advice and Obligation Handlers to be set (this will then - for
	 * this operation only - override the Advice/Obligation Handlers that have
	 * previously been installed.
	 * 
	 * @param request    The object representing the request.
	 * @param appContext    An application specific Object that is passed to every
	 * Advice/Obligation Handler that gets called when the response is received. May
	 * be null.
	 * @param obligationHandlers    A list of Obligation Handlers.
	 * @param adviceListeners    A list of Advice Handlers.
	 */
	public abstract Response evaluate(Request request, Object appContext, abstractSet<AdviceObligationHandler> obligationHandlers, abstractSet<AdviceObligationHandler> adviceListeners) throws Exception;

	/**
	 * Sends an extended operation to a PDP. Extended operations can be used to
	 * implement additional features by PDPs. For example, a PDP might implement an
	 * extended operation called "EvalueWithTrace" that will return a decision, and
	 * additional tracing information that might be useful in order to see which XACML
	 * policies applied and how they have been evaluated. <strong>Since extended
	 * operations may or may not be supported by a particular PDP, use this feature
	 * with care.</strong>
	 * @return The decision of the evaluation.
	 * 
	 * @param operation    A String denoting the extended operation type. This is
	 * relevant for the PDP that may support this particular extended operation type.
	 * @param request    The Request object.
	 * @param extendedContext    An Object that contains additional information about
	 * the extended request. May be null.
	 */
	public abstract Response extendedOperation(String operation, Request request, Object extendedContext) throws Exception;

	/**
	 * Sends an extended operation to a PDP. Extended operations can be used to
	 * implement additional features by PDPs. For example, a PDP might implement an
	 * extended operation called "EvalueWithTrace" that will return a decision, and
	 * additional tracing information that might be useful in order to see which XACML
	 * policies applied and how they have been evaluated. <strong>Since extended
	 * operations may or may not be supported by a particular PDP, use this feature
	 * with care.</strong>. Also allows to specify an Object that will then be passed
	 * to every AdviceObligationHandler that may be called due to Advice/Obligations
	 * in the Result(s).
	 * 
	 * @param operation    A String denoting the extended operation type. This is
	 * relevant for the PDP that may support this particular extended operation type.
	 * @param request    The Object representing the Request.
	 * @param extendedContext    An Object that contains additional information about
	 * the extended request. May be null.
	 * @param appContext    An application specific Object that is passed to every
	 * Advice/Obligation Handler that gets called when the response is received. May
	 * be null.
	 */
	public abstract Response extendedOperation(String operation, Request request, Object extendedContext, Object appContext) throws Exception;

	/**
	 * Sends an extended operation to a PDP. Extended operations can be used to
	 * implement additional features by PDPs. For example, a PDP might implement an
	 * extended operation called "EvalueWithTrace" that will return a decision, and
	 * additional tracing information that might be useful in order to see which XACML
	 * policies applied and how they have been evaluated. <strong>Since extended
	 * operations may or may not be supported by a particular PDP, use this feature
	 * with care.</strong>. Also allows to specify an Object that will then be passed
	 * to every AdviceObligationHandler that may be called due to Advice/Obligations
	 * in the Result(s).
	 * 
	 * @param operation    A String denoting the extended operation type. This is
	 * relevant for the PDP that may support this particular extended operation type.
	 * @param request    The Request object.
	 * @param extendedContext    An Object that contains additional information about
	 * the extended request. May be null.
	 * @param appContext    An application specific Object that is passed to every
	 * Advice/Obligation Handler that gets called when the response is received. May
	 * be null.
	 * @param adviceHandlers    A List of Handlers that will get called for every
	 * Advice on the Results of this operation only. May be null.
	 * @param obligationHandlers    A List of Handlers that will get called for every
	 * Obligation on the Results of this operation only. May be null.
	 */
	public abstract Response extendedOperation(String operation, Request request, Object extendedContext, Object appContext, abstractSet<AdviceObligationHandler> adviceHandlers, abstractSet<AdviceObligationHandler> obligationHandlers) throws Exception;

	/**
	 * Returns a boolean indicating whether a particular operation has completed. If
	 * this is indicated, then calling waitOn() on the same operationHandle will
	 * deliver an immediate result.
	 * 
	 * @param opHandle    opHandle
	 */
	public abstract boolean isDone(operationHandle opHandle);

	/**
	 * Sets up the connection to a policy decision point (PDP). This method will be
	 * called during the client initialization. It will create / recreate the
	 * singleton client stub.
	 * 
	 * @param properties    Properties containing the connection parameters
	 */
	public abstract void setupConnection(Properties properties);

	/**
	 * Waits on the current operation handle and returns the Response received.
	 * 
	 * @param opHandle    opHandle
	 */
	public abstract Response waitOn(operationHandle opHandle) throws Exception;

	/**
	 * Adds a handler to the Registry.
	 * 
	 * @param id    The identifier that the Handler is responsible for.
	 * @param handler    The Advice/Obligation Handler.
	 */
	public void addHandler(Identifier id, AdviceObligationHandler handler){

	}

	/**
	 * Adds a handler to the Registry.
	 * 
	 * @param registryEntry    Both the Advice/Obligation Handler and the Identifier
	 * that the Handler is responsible for in a HandlerRegistryEntry object.
	 */
	public void addHandler(AdviceObligationHandlerRegistryEntry registryEntry){

	}

	/**
	 * Return the current handlers, as a list of HandlerRegistryEntry objects.
	 */
	public abstractSet<AdviceObligationHandlerRegistryEntry> getHandlers(){
		return null;
	}

}