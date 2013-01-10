package org.EzAz.generic.Layer2;
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
import java.io.PrintStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collection;

import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.Identifier;

public class genericAttribute implements Attribute, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9214657975872643096L;
	private Identifier id, type;
	private String issuer;
	private Object value;
	private boolean includeInResult=false;
	
	@Override
	public Identifier getId() {
		return id;
	}

	@Override
	public String getIssuer() {
		return issuer;
	}

	@Override
	public Identifier getType() {
		return type;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public boolean isBoolean() {
		if (type.equals(TYPE_BOOLEAN) || value instanceof Boolean)
			return true;
		else
			return false;
	}

	@Override
	public boolean isMultiValue() {
		if (value instanceof Collection || value instanceof AbstractMap)
			return true;
		else
			return false;
	}

	@Override
	public void setId(Identifier id) {
		this.id = id;
	}

	@Override
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	@Override
	public void setType(Identifier type) {
		this.type = type;
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public void setIncludeInResult(boolean includeInResult) {
		this.includeInResult=includeInResult;
	}

	@Override
	public boolean getIncludeInResult() {
		return includeInResult;
	}
	
	public static void prettyPrint(PrintStream ps, String header, Attribute a) {
		ps.println (header+"ATTR: "+a.getId().toASCIIString()+"="+a.getValue().toString()+" ("+a.getType().toASCIIString()+")");
	}

}
