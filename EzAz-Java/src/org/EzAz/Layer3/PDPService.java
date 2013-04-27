package org.EzAz.Layer3;

import org.EzAz.AdviceObligationHandlers.AdviceObligationHandler;
import org.EzAz.Layer2.AdviceObligation;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.Result;
import org.EzAz.AdviceObligationHandlers.AdviceObligationHandlerRegistry;
import org.EzAz.Layer2.abstractSet;
import java.util.Properties;

/**
 * An interface for the connection to the PDP.
 * 
 * @author felix
 * @version 1.0
 * @created 02-Apr-2013 14:28:24
 */
public abstract class PDPService implements AdviceObligationHandlerRegistry {

    public abstractSet<AdviceObligationHandler> preChain = new abstractSet<AdviceObligationHandler>();
    public abstractSet<AdviceObligationHandler> internalChain = new abstractSet<AdviceObligationHandler>();
    public abstractSet<AdviceObligationHandler> postChain = new abstractSet<AdviceObligationHandler>();

    public void finalize() throws Throwable {

    }

    public PDPService() {

    }

    /**
     * Adds a handler to the Registry.
     * 
     * @param registryEntry
     *            Both the Advice/Obligation Handler and the Identifier that the
     *            Handler is responsible for in a HandlerRegistryEntry object.
     */
    public void addHandler(AdviceObligationHandler handler) {
	internalChain.add(handler);
    }

    /**
     * An asynchronous way to send an evaluate request to a PDP. Returns an
     * operationHandle.
     * 
     * @return An operationHandle that can be used to track the execution
     * 
     * @param request
     *            The actual XACML request
     * @exception Exception
     */
    public abstract operationHandle asyncEvaluate(Request request) throws Exception;

    /**
     * An asynchronous way to send an evaluate request to a PDP. Returns an
     * operationHandle. This method also takes a AsyncResultListener as an
     * argument, which will then get called when the operation completes.
     * 
     * @return An operationHandle that can be used to track the execution
     * 
     * @param request
     *            The actual XACML request
     * @param listener
     *            that will get notified when the operation completes
     * @exception Exception
     */
    public abstract operationHandle asyncEvaluate(Request request, AsyncResultListener listener) throws Exception;

    /**
     * The asynchronous way to send an extended operation to a PDP. Returns an
     * operationHandle.
     * 
     * @return An operationHandle that can be used to track the execution of the
     *         operation.
     * 
     * @param operation
     *            The extended operation, represented as a String.
     * @param request
     *            The Request Object.
     * @param extendedContext
     *            Any additional context that may be relevant for the extended
     *            operation.
     * @param listener
     *            A listener that will get notified when the operation
     *            completes.
     * @exception Exception
     */
    public abstract operationHandle asyncExtendedOperation(String operation, Request request, Object extendedContext,
	    AsyncResultListener listener) throws Exception;

    /**
     * Cancels a request that is already in process. <b>This may or may not be
     * supported by the PDP, meaning that the PDP might still evaluate the
     * request. </b>
     * 
     * @param opHandle
     *            opHandle
     */
    public abstract void cancel(operationHandle opHandle);

    /**
     * Sends the request to the PDP for evaluation. Simplest synchronous
     * operation mode.
     * 
     * @param request
     *            The Request Object.
     * @exception Exception
     */
    public Response evaluate(Request request) throws Exception {
	return evaluate(request, null);
    }

    /**
     * Sends the request to the PDP for evaluation in synchronous mode. Allows
     * to specify an Object that will then be passed to every
     * AdviceObligationHandler that may be called due to Advice/Obligations in
     * the Result(s).
     * 
     * @param request
     *            The Request Object.
     * @param appContext
     *            An application specific Object that is passed to every
     *            Advice/Obligation Handler that gets called when the response
     *            is received. May be null.
     * @exception Exception
     */
    public Response evaluate(Request request, Object appContext) throws Exception {
	Response resp = evaluateImpl(request, appContext);
	// Handle advice and obligations
	handleAdviceAndObligations(request, resp, appContext);
	return resp;
    }

    public boolean handleAdviceAndObligations(Request request, Response response, Object context) {
	abstractSet<Result> results = response.getResults();
	boolean allObligationsHandled = true;
	for (Result result : results) {
	    //
	    // Handle Advice
	    if (result.getAssociatedAdvice() != null)
		for (AdviceObligation adv : result.getAssociatedAdvice()) {
		    // Call all handlers on the pre Chain
		    if (!adv.mayNotHandle())
			processAdviceChain(preChain, adv, request, result, context);
		    // Call all handlers on the internal Chain
		    if (!adv.mayNotHandle())
			processAdviceChain(internalChain, adv, request, result, context);
		    // Call all handlers on the post Chain
		    if (!adv.mayNotHandle())
			processAdviceChain(postChain, adv, request, result, context);
		}
	    //
	    // Handle Obligations
	    if (result.getObligations() != null)
	    for (AdviceObligation obl : result.getObligations()) {
		// Call all handlers on the pre Chain
		if (!obl.mayNotHandle())
		    processObligationChain(preChain, obl, request, result, context);
		// Call all handlers on the internal Chain
		if (!obl.mayNotHandle())
		    processObligationChain(internalChain, obl, request, result, context);
		// Call all handlers on the post Chain
		if (!obl.mayNotHandle())
		    processObligationChain(postChain, obl, request, result, context);
		// Remember whether any obligation has NOT been handled yet
		if (obl.hasBeenHandled() == 0)
		    allObligationsHandled = false;
	    }
	}
	return allObligationsHandled;
    }

    public void processAdviceChain(abstractSet<AdviceObligationHandler> chain, AdviceObligation adv, Request request,
	    Result result, Object context) {
	for (AdviceObligationHandler h : chain) {
	    if (adv.mayNotHandle())
		return;
	    if (h.canHandleAdvice(adv.getId()))
		h.handleAdvice(adv, request, result, context);
	}
    }

    public void processObligationChain(abstractSet<AdviceObligationHandler> chain, AdviceObligation obl,
	    Request request, Result result, Object context) {
	for (AdviceObligationHandler h : chain) {
	    if (obl.mayNotHandle())
		return;
	    if (h.canHandleObligation(obl.getId()))
		h.handleObligation(obl, request, result, context);
	}
    }

    /**
     * Sends the request to the PDP for evaluation in synchronous mode. Allows
     * to specify an Object that will then be passed to every
     * AdviceObligationHandler that may be called due to Advice/Obligations in
     * the Result(s). Also allows a list of specific Advice and Obligation
     * Handlers to be set (this will then - for this operation only - override
     * the Advice/Obligation Handlers that have previously been installed.
     * 
     * @param request
     *            The object representing the request.
     * @param appContext
     *            An application specific Object that is passed to every
     *            Advice/Obligation Handler that gets called when the response
     *            is received. May be null.
     * @param obligationHandlers
     *            A list of Obligation Handlers.
     * @param adviceListeners
     *            A list of Advice Handlers.
     * @exception Exception
     */
    public Response evaluate(Request request, Object appContext,
	    abstractSet<AdviceObligationHandler> obligationHandlers,
	    abstractSet<AdviceObligationHandler> adviceListeners) throws Exception {
	return evaluate(request, appContext);
    }

    /**
     * The native implementation of the evaluate() method. This function must
     * handle the actual communication with the PDP in synchronous mode. Allows
     * to specify an Object (may be null) that will then be passed to every
     * AdviceObligationHandler that may be called due to Advice/Obligations in
     * the Result(s).
     * 
     * @param request
     *            The Request Object.
     * @param appContext
     *            An application specific Object that is passed to every
     *            Advice/Obligation Handler that gets called when the response
     *            is received. May be null.
     */
    public abstract Response evaluateImpl(Request request, Object appContext);

    /**
     * Sends an extended operation to a PDP. Extended operations can be used to
     * implement additional features by PDPs. For example, a PDP might implement
     * an extended operation called "EvalueWithTrace" that will return a
     * decision, and additional tracing information that might be useful in
     * order to see which XACML policies applied and how they have been
     * evaluated. <strong>Since extended operations may or may not be supported
     * by a particular PDP, use this feature with care.</strong>
     * 
     * @return The decision of the evaluation.
     * 
     * @param operation
     *            A String denoting the extended operation type. This is
     *            relevant for the PDP that may support this particular extended
     *            operation type.
     * @param request
     *            The Request object.
     * @param extendedContext
     *            An Object that contains additional information about the
     *            extended request. May be null.
     * @exception Exception
     */
    public abstract Response extendedOperation(String operation, Request request, Object extendedContext)
	    throws Exception;

    /**
     * Sends an extended operation to a PDP. Extended operations can be used to
     * implement additional features by PDPs. For example, a PDP might implement
     * an extended operation called "EvalueWithTrace" that will return a
     * decision, and additional tracing information that might be useful in
     * order to see which XACML policies applied and how they have been
     * evaluated. <strong>Since extended operations may or may not be supported
     * by a particular PDP, use this feature with care.</strong>. Also allows to
     * specify an Object that will then be passed to every
     * AdviceObligationHandler that may be called due to Advice/Obligations in
     * the Result(s).
     * 
     * @param operation
     *            A String denoting the extended operation type. This is
     *            relevant for the PDP that may support this particular extended
     *            operation type.
     * @param request
     *            The Object representing the Request.
     * @param extendedContext
     *            An Object that contains additional information about the
     *            extended request. May be null.
     * @param appContext
     *            An application specific Object that is passed to every
     *            Advice/Obligation Handler that gets called when the response
     *            is received. May be null.
     * @exception Exception
     */
    public abstract Response extendedOperation(String operation, Request request, Object extendedContext,
	    Object appContext) throws Exception;

    /**
     * Sends an extended operation to a PDP. Extended operations can be used to
     * implement additional features by PDPs. For example, a PDP might implement
     * an extended operation called "EvalueWithTrace" that will return a
     * decision, and additional tracing information that might be useful in
     * order to see which XACML policies applied and how they have been
     * evaluated. <strong>Since extended operations may or may not be supported
     * by a particular PDP, use this feature with care.</strong>. Also allows to
     * specify an Object that will then be passed to every
     * AdviceObligationHandler that may be called due to Advice/Obligations in
     * the Result(s).
     * 
     * @param operation
     *            A String denoting the extended operation type. This is
     *            relevant for the PDP that may support this particular extended
     *            operation type.
     * @param request
     *            The Request object.
     * @param extendedContext
     *            An Object that contains additional information about the
     *            extended request. May be null.
     * @param appContext
     *            An application specific Object that is passed to every
     *            Advice/Obligation Handler that gets called when the response
     *            is received. May be null.
     * @param adviceHandlers
     *            A List of Handlers that will get called for every Advice on
     *            the Results of this operation only. May be null.
     * @param obligationHandlers
     *            A List of Handlers that will get called for every Obligation
     *            on the Results of this operation only. May be null.
     * @exception Exception
     */
    public abstract Response extendedOperation(String operation, Request request, Object extendedContext,
	    Object appContext, abstractSet<AdviceObligationHandler> adviceHandlers,
	    abstractSet<AdviceObligationHandler> obligationHandlers) throws Exception;

    /**
     * Return the current handlers, as a list of HandlerRegistryEntry objects.
     */
    public abstractSet<AdviceObligationHandler> getHandlers() {
	return internalChain;
    }

    /**
     * Returns a boolean indicating whether a particular operation has
     * completed. If this is indicated, then calling waitOn() on the same
     * operationHandle will deliver an immediate result.
     * 
     * @param opHandle
     *            opHandle
     */
    public abstract boolean isDone(operationHandle opHandle);

    /**
     * Sets up the connection to a policy decision point (PDP). This method will
     * be called during the client initialization. It will create / recreate the
     * singleton client stub.
     * 
     * @param properties
     *            Properties containing the connection parameters
     */
    public abstract void setupConnection(Properties properties);

    /**
     * Waits on the current operation handle and returns the Response received.
     * 
     * @param opHandle
     *            opHandle
     * @exception Exception
     */
    public abstract Response waitOn(operationHandle opHandle) throws Exception;

}