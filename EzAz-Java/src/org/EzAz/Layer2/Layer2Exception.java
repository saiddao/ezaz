package org.EzAz.Layer2;
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

/**
 * @author felix
 * @version 1.0
 * @created 10-Jan-2013 17:55:56
 */
public abstract class Layer2Exception extends Exception {

	private static final long serialVersionUID = -7499051948739345476L;

	/**
	 * 
	 * @param arg0    arg0
	 */
	public Layer2Exception(String arg0){
		super(arg0);
	}

}