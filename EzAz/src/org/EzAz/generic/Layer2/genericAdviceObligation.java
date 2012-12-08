package org.EzAz.generic.Layer2;

import org.EzAz.Layer2.AdviceObligation;
import org.EzAz.Layer2.AttributeAssignment;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.abstractSet;

public class genericAdviceObligation implements AdviceObligation {

	private abstractSet<AttributeAssignment> attributeAssignments;
	Identifier id;
	int hasBeenHandled=0;
	
	@Override
	public abstractSet<AttributeAssignment> getAttributeAssignments() {
		// TODO Auto-generated method stub
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

}
