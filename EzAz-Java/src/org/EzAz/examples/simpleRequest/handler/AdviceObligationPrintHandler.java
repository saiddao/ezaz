package org.EzAz.examples.simpleRequest.handler;

import java.util.Properties;

import org.EzAz.AdviceObligationHandlers.AdviceObligationHandler;
import org.EzAz.Layer2.AdviceObligation;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Result;
import org.EzAz.generic.Layer2.genericAdviceObligation;

public class AdviceObligationPrintHandler implements AdviceObligationHandler {

    @Override
    public void handleAdvice(AdviceObligation obligation, Request request, Result result, Object context) {
	genericAdviceObligation.prettyPrint(System.out, "AdviceObligationPrintHander::handleAdvice: ", "(advice)", obligation);
	obligation.setHandled(true);
    }

    @Override
    public void handleObligation(AdviceObligation obligation, Request request, Result result, Object context) {
	genericAdviceObligation.prettyPrint(System.out, "AdviceObligationPrintHander::handleObligation: ", "(obligation)", obligation);
	obligation.setHandled(true);
    }

    @Override
    public void init(Properties properties) {
	// TODO Auto-generated method stub
	
    }

}
