package org.EzAz.Layer2;
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
import org.EzAz.Layer2.abstractSet;

/**
 * @author felix
 * @version 1.0
 * @created 12-Dec-2012 22:54:48
 */
public interface AdviceObligation {

	/**
	 * Returns the AttributeAssignement of the Advice or Obligation.
	 */
	public abstractSet<AttributeAssignment> getAttributeAssignments();

	/**
	 * Returns the ID of the Advice or Obligation.
	 */
	public Identifier getId();

	/**
	 * Returns an integer value indicating whether this Advice or Obligation has been
	 * handled already. The following values are defined:
	 * 0 : Advice/Obligation has NOT been handled yet.
	 * 1: Advice/Obligation HAS been handled. If a handler wants to handle the
	 * Advice/Obligation as well, it is free to do so.
	 * 2: Advice/Obligation HAS been handled, and MUST NOT be handled again
	 * (obligation handlers must refrain from handling it again).
	 */
	public int hasBeenHandled();

	/**
	 * Set the state to indicate that this Advice/Obligation has been handled already.
	 * 
	 * @param handled    Indication of whether this Advice/Obligation has been handled.
	 */
	public void setHandled(boolean handled);

	/**
	 * Set the state to indicate that this Advice/Obligation has been handled, and no
	 * other handler may handle it again.
	 * 
	 * @param stophandling    Indication on whether any subsequent handler must ignore
	 * this Advice/Obligation.
	 */
	public void setStopHandling(boolean stophandling);

	/**
	 * Returns a flag indicating that this Advice/Obligation has been handled, and no
	 * other handler may handle it again.
	 */
	public boolean mayNotHandle();

}