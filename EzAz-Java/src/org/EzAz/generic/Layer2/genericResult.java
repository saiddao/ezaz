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
	private abstractMap<Identifier, AttributeEntity> attributeEntities;
	private int decision;
	private abstractSet<AdviceObligation> obligations;
	private abstractSet<IdReference> policyIdentifierList;
	private Status status;
	private boolean mutable = true;

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
		if (policyIdentifierList == null && isMutable()) {
			policyIdentifierList=new abstractSet<IdReference>();
		}
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
		if (decision == Result.RESULT_PERMIT)
			return true;
		else if (decision == Result.RESULT_DENY)
			return false;
		else
			return bias;
	}

	@Override
	public void addAdvice(AdviceObligation advice)
			throws genericLayer2RuntimeException {
		checkMutable();
		this.associatedAdvice.add(advice);
	}

	@Override
	public void addObligation(AdviceObligation obligation)
			throws genericLayer2RuntimeException {
		checkMutable();
		this.obligations.add(obligation);
	}

	@Override
	public boolean isMutable() {
		return mutable;
	}

	public void checkMutable() throws genericLayer2RuntimeException {
		if (!mutable)
			throw (new genericLayer2RuntimeException(
					"Cannot modify an immutable Result object!"));
	}

	@Override
	public void setAssociatedAdvice(abstractSet<AdviceObligation> advice)
			throws genericLayer2RuntimeException {
		checkMutable();
		this.associatedAdvice = advice;

	}

	@Override
	public void setDecision(int decision) throws genericLayer2RuntimeException {
		checkMutable();
		this.decision = decision;
	}

	@Override
	public void setImmutable() {
		mutable = false;
	}

	@Override
	public void setObligations(abstractSet<AdviceObligation> obligationSet)
			throws genericLayer2RuntimeException {
		checkMutable();
		this.obligations = obligationSet;
	}

	@Override
	public void setPolicyIdentifierList(
			abstractSet<IdReference> policyIdentifiers)
			throws genericLayer2RuntimeException {
		checkMutable();
		this.policyIdentifierList = policyIdentifiers;
	}

	@Override
	public void setStatus(Status status) throws genericLayer2RuntimeException {
		checkMutable();
		this.status = status;
	}

	@Override
	public abstractMap<Identifier, AttributeEntity> getCategoriesEntities() {
		if (attributeEntities == null && isMutable()) {
			attributeEntities = new abstractMap<Identifier, AttributeEntity>();
		}
		return attributeEntities;
	}

	@Override
	public void setCategoriesEntities(
			abstractMap<Identifier, AttributeEntity> attributeEntities) {
		this.attributeEntities = attributeEntities;
	}

	public static void prettyPrint(PrintStream ps, String header, Result result) {
		String decision;
		int dec = result.getDecision();
		if (dec == Result.RESULT_PERMIT)
			decision = "PERMIT";
		else if (dec == Result.RESULT_DENY)
			decision = "DENY";
		else if (dec == Result.RESULT_NOT_APPLICABLE)
			decision = "NOT APPLICABLE";
		else if (dec == Result.RESULT_INDETERMINATE)
			decision = "INDETERMINATE";
		else
			decision = "UNKNOWN_VALUE";
		ps.println(header + "DECISION: " + decision);
		Status status = result.getStatus();
		genericStatus.prettyPrint(ps, header + "  ", status);
		// Advice
		abstractSet<AdviceObligation> advice = result.getAssociatedAdvice();
		if (advice != null)
			for (AdviceObligation adv : advice) {
				genericAdviceObligation.prettyPrint(ps, header + "  ", "ADVICE", adv);
			}
		// Obligations
		abstractSet<AdviceObligation> obligations = result.getObligations();
		if (obligations != null)
			for (AdviceObligation obligation : obligations) {
				genericAdviceObligation.prettyPrint(ps, header + "  ", "OBLIGATION", obligation);
			}
		// Attributes
		abstractMap<Identifier, AttributeEntity> categoriesEntities = result
				.getCategoriesEntities();
		if (categoriesEntities != null) {
			for (Identifier i : categoriesEntities.keySet()) {
				AttributeEntity ent = categoriesEntities.get(i);
				genericAttributeEntity.prettyPrint(ps, header + "  ", ent);
			}
		}
	}

	public static void handleAdvice(Result r) {
	    for (AdviceObligation adv: r.getAssociatedAdvice()) {
		// handle advice
	    }
	}

	public static boolean handleObligations(Result r) {
	    for (AdviceObligation adv: r.getObligations()) {
		// handle obligations
	    }
	    return false;
	}

}
