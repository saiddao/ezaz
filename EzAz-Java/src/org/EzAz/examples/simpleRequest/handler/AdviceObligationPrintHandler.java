package org.EzAz.examples.simpleRequest.handler;
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
import org.EzAz.Layer2.AdviceObligation;
import org.EzAz.Layer2.Identifier;
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
	System.out.println ("AdviceObligationPrintHandler:init with properties: "+properties.toString());

    }

    @Override
    public boolean canHandleAdvice(Identifier id) {
	return true;
    }

    @Override
    public boolean canHandleObligation(Identifier id) {
	return true;
    }

}
