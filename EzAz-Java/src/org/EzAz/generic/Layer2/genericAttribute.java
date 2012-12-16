package org.EzAz.generic.Layer2;

import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.Collection;

import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.Identifier;

public class genericAttribute implements Attribute {

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
