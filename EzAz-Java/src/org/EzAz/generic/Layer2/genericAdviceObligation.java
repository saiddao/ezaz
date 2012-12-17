package org.EzAz.generic.Layer2;

import java.io.PrintStream;

import org.EzAz.Layer2.AdviceObligation;
import org.EzAz.Layer2.AdviceObligationSetter;
import org.EzAz.Layer2.AttributeAssignment;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.abstractSet;

public class genericAdviceObligation implements AdviceObligation, AdviceObligationSetter {

	private abstractSet<AttributeAssignment> attributeAssignments;
	Identifier id;
	int hasBeenHandled=0;
	
	@Override
	public abstractSet<AttributeAssignment> getAttributeAssignments() {
		return attributeAssignments;
	}

	@Override
	public Identifier getId() {
		return id;
	}

	@Override
	public int hasBeenHandled() {
		return hasBeenHandled;
	}

	@Override
	public void setHandled(boolean handled) {
		if (hasBeenHandled != 2)
			hasBeenHandled=1;

	}

	@Override
	public void setStopHandling(boolean stophandling) {
		hasBeenHandled=2;

	}
	
	@Override
	public boolean mayNotHandle() {
		if (hasBeenHandled == 2)
			return true;
		else
			return false;
	}

	@Override
	public void setId(Identifier id) {
		this.id=id;
	}

	@Override
	public void setAttributeAssignments(
			abstractSet<AttributeAssignment> attributeAssignments) {
		this.attributeAssignments=attributeAssignments;
	}

	public static void prettyPrint(PrintStream ps, String header, AdviceObligation obl) {
		ps.println(header+"OBLIGATION: "+obl.getId().toString());
		abstractSet<AttributeAssignment> a = obl.getAttributeAssignments();
		for (AttributeAssignment assign: a) {
			genericAttributeAssignment.prettyPrint(ps, header+"  ", assign);
		}
	}
}
