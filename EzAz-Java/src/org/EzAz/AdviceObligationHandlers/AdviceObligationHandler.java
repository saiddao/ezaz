package org.EzAz.AdviceObligationHandlers;
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
import org.EzAz.Layer2.Result;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.AdviceObligation;

/**
 * @author felix
 * @version 1.0
 * @created 12-Dec-2012 22:54:48
 */
public interface AdviceObligationHandler {

	/**
	 * 
	 * @param obligation
	 * @param request
	 * @param result
	 * @param context    context
	 */
	public void handleAdvice(AdviceObligation obligation, Request request, Result result, Object context);

	/**
	 * 
	 * @param obligation
	 * @param request
	 * @param result
	 * @param context    context
	 */
	public void handleObligation(AdviceObligation obligation, Request request, Result result, Object context);

}