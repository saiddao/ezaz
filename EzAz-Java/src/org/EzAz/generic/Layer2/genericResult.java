package org.EzAz.generic.Layer2;

import org.EzAz.Layer2.AdviceObligation;
import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.IdReference;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Result;
import org.EzAz.Layer2.ResultSetter;
import org.EzAz.Layer2.Status;
import org.EzAz.Layer2.abstractMap;
import org.EzAz.Layer2.abstractSet;

public class genericResult implements Result, ResultSetter {

	private abstractSet<AdviceObligation> associatedAdvice;
	private abstractMap<Identifier,AttributeEntity> attributeEntities;
	private int decision;
	private abstractSet<AdviceObligation> obligations;
	private abstractSet<IdReference> policyIdentifierList;
	private Status status;
	private boolean mutable=true;

	@Override
	public abstractSet<AdviceObligation> getAssociatedAdvice() {
		return associatedAdvice;
	}

	@Override
	public int getDecision() {
		return decision;
	}

	@Override
	public abstractSet<AdviceObligation> getObligations() {
		return obligations;
	}

	@Override
	public abstractSet<IdReference> getPolicyIdentifierList() {
		return policyIdentifierList;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public boolean isAllowed(boolean bias) {
		// TODO Auto-generated method stub
		if (obligations != null && !obligations.isEmpty())
			return bias;
		if (decision==Result.RESULT_PERMIT)
			return true;
		else if (decision==Result.RESULT_DENY)
			return false;
		else
			return bias;
	}

	@Override
	public void addAdvice(AdviceObligation advice) throws genericLayer2RuntimeException {
		checkMutable();
		this.associatedAdvice.add(advice);
	}

	@Override
	public void addObligation(AdviceObligation obligation) throws genericLayer2RuntimeException {
		checkMutable();
		this.obligations.add(obligation);
	}

	@Override
	public boolean isMutable() {
		return mutable;
	}
	
	public void checkMutable() throws genericLayer2RuntimeException {
		if (!mutable)
			throw (new genericLayer2RuntimeException("Cannot modify an immutable Result object!"));
	}

	@Override
	public void setAssociatedAdvice(abstractSet<AdviceObligation> advice) throws genericLayer2RuntimeException {
		checkMutable();
		this.associatedAdvice=advice;
		
	}

	@Override
	public void setDecision(int decision) throws genericLayer2RuntimeException {
		checkMutable();
		this.decision=decision;
	}

	@Override
	public void setImmutable() {
		mutable=false;
	}

	@Override
	public void setObligations(abstractSet<AdviceObligation> obligationSet) throws genericLayer2RuntimeException {
		checkMutable();
		this.obligations=obligationSet;
	}

	@Override
	public void setPolicyIdentifierList(
			abstractSet<IdReference> policyIdentifiers) throws genericLayer2RuntimeException {
		checkMutable();
		this.policyIdentifierList=policyIdentifiers;
	}

	@Override
	public void setStatus(Status status) throws genericLayer2RuntimeException {
		checkMutable();
		this.status=status;
	}

	@Override
	public abstractMap<Identifier, AttributeEntity> getCategoriesEntities() {
		return attributeEntities;
	}

	@Override
	public void setCategoriesEntities(
			abstractMap<Identifier, AttributeEntity> attributeEntities) {
		this.attributeEntities=attributeEntities;
	}

}
