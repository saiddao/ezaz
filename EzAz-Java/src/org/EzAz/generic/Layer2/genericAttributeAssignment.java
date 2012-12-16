package org.EzAz.generic.Layer2;

import java.io.PrintStream;

import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.AttributeAssignment;
import org.EzAz.Layer2.Identifier;

public class genericAttributeAssignment implements AttributeAssignment {

	Attribute attribute;
	Identifier category;

	@Override
	public Attribute getAttribute() {
		return attribute;
	}

	@Override
	public Identifier getCategory() {
		return category;
	}

	@Override
	public void setAttribute(Attribute attribute) {
		this.attribute=attribute;

	}

	@Override
	public void setCategory(Identifier category) {
		this.category=category;
	}

	public static void prettyPrint(PrintStream ps, String header, AttributeAssignment assign) {
		ps.println(header+"ATTRIBUTEASSIGNMENT: Category="+assign.getCategory().toString());
		Attribute attr = assign.getAttribute();
		genericAttribute.prettyPrint(ps, header+"  ", attr);
	}

}
