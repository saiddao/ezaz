package org.EzAz.generic.Layer2;

import java.util.Map.Entry;

import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.AttributeHelper;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.abstractMap;
import org.EzAz.Layer2.abstractSet;

public class genericRequest implements Request, AttributeHelper {
	
	int version=3;
	boolean returnPolicyIdList=false;
	boolean mutable=true;
	abstractSet<AttributeEntity> repeatedCategories;
	AttributeEntity currentRepeatedAttributeEntity;
	boolean combinedDecision;
	private abstractMap<Identifier, AttributeEntity> myCategoriesEntities;
	private Identifier repeatedCategoryId;

	@Override
	public boolean getCombinedDecision() {
		return combinedDecision;
	}

	@Override
	public abstractSet<AttributeEntity> getRepeatedCategories() {
		return repeatedCategories;
	}

	@Override
	public boolean getReturnPolicyIdList() {
		return returnPolicyIdList;
	}

	@Override
	public int getXacmlVersion() {
		return version;
	}

	@Override
	public boolean hasRepeatedCategories() {
		if (repeatedCategories == null)
			return false;
		else {
			if (repeatedCategories.size() > 0)
				return true;
			else
				return false;
		}
	}

	@Override
	public boolean isMultipleRequest() {
		if (repeatedCategories == null || repeatedCategoryId == null)
			return false;
		return true;
	}

	@Override
	public boolean isMutable() {
		return mutable;
	}

	@Override
	public void setCombinedDecision(boolean combinedDecision) throws genericLayer2Exception {
		checkMutable();
		this.combinedDecision=combinedDecision;
	}
	
	private void checkMutable() throws genericLayer2RuntimeException {
		if (!mutable)
			throw (new genericLayer2RuntimeException("Cannot modify an immutable Request object!"));
	}

	@Override
	public void setReturnPolicyIdList(boolean returnPolicyIdList) throws genericLayer2Exception {
		checkMutable();
		this.returnPolicyIdList=returnPolicyIdList;
	}
	
	@Override
	public void addAttribute(Identifier category, Identifier id, String issuer,
			Identifier type, Object value) {
		genericAttribute attr = new genericAttribute();
		attr.setId(id);
		attr.setIssuer(issuer);
		attr.setType(type);
		attr.setValue(value);
		addAttribute(category, attr);
	}

	@Override
	public void addAttribute(Identifier category, Identifier id, String issuer,
			boolean val) {
		genericAttribute attr = new genericAttribute();
		attr.setId(id);
		attr.setIssuer(issuer);
		attr.setType (Attribute.TYPE_BOOLEAN);
		attr.setValue(new Boolean(val));
		addAttribute(category, attr);
	}

	@Override
	public void addAttribute(Identifier category, Identifier id, String issuer,
			int val) {
		genericAttribute attr = new genericAttribute();
		attr.setId(id);
		attr.setIssuer(issuer);
		attr.setType (Attribute.TYPE_INTEGER);
		attr.setValue(new Integer(val));
		addAttribute(category, attr);
	}

	@Override
	public void addAttribute(Identifier category, Identifier id, String issuer,
			String val) {
		genericAttribute attr = new genericAttribute();
		attr.setId(id);
		attr.setIssuer(issuer);
		attr.setType(Attribute.TYPE_STRING);
		attr.setValue(new String(val));
		addAttribute(category, attr);
	}

	@Override
	public void addAttribute(Identifier category, Attribute attr) throws genericLayer2RuntimeException {
		checkMutable();
		abstractMap<Identifier,AttributeEntity> entities = getCategoriesEntities();
		AttributeEntity entity = entities.get(category);
		if (entity == null) {
			entity=new genericAttributeEntity();
			entity.setCategory(category);
			abstractSet<Attribute> attrs=new abstractSet<Attribute>();
			attrs.add(attr);
			entity.setAttributes(attrs);
			addCategory(entity);
		} else {
			abstractSet<Attribute> attrs = entity.getAttributes();
			attrs.add(attr);
		}
	}

	@Override
	public void addCategory(Identifier cat, abstractSet<Attribute> attributes) throws genericLayer2RuntimeException {
		abstractMap<Identifier, AttributeEntity> categoryMap =
			getCategoriesEntities();
		if (categoryMap.containsKey(cat)) {
			throw new genericLayer2RuntimeException ("Trying to add a category to a Request but that category already exists!");
		}
		AttributeEntity ent=new genericAttributeEntity();
		ent.setCategory(cat);
		ent.setAttributes(attributes);
		categoryMap.put(cat, ent);
	}

	@Override
	public void addCategory(AttributeEntity entity) throws genericLayer2RuntimeException {
		abstractMap<Identifier, AttributeEntity> categoryEntities =
			getCategoriesEntities();
		Identifier cat=entity.getCategory();
		if (cat == null) {
			throw new genericLayer2RuntimeException ("AttributeEntity has null category!");
		}
		if (categoryEntities.containsKey(cat)) {
			throw new genericLayer2RuntimeException ("Trying to add a category that already exists!");
		}
		categoryEntities.put(cat, entity);
	}

	@Override
	public Request clone(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public abstractMap<Identifier, abstractSet<Attribute>> getCategoriesAttributes() {
		abstractMap<Identifier, AttributeEntity> categoryEntities =
			getCategoriesEntities();
		abstractMap<Identifier, abstractSet<Attribute>> categoriesAttributes =
			new abstractMap<Identifier, abstractSet<Attribute>>();
		for (Entry<Identifier, AttributeEntity> entry : categoryEntities.entrySet()) {
			AttributeEntity attrEntity = entry.getValue();
			categoriesAttributes.put(entry.getKey(), attrEntity.getAttributes());
		}
		return categoriesAttributes ;
	}

	@Override
	public abstractMap<Identifier, AttributeEntity> getCategoriesEntities() {
		if (myCategoriesEntities == null) {
			myCategoriesEntities=new abstractMap<Identifier, AttributeEntity>();
		}
		return myCategoriesEntities;
	}

	@Override
	public void removeAttribute(Identifier category, Identifier id,
			String issuer) {
		abstractMap<Identifier, AttributeEntity> categoryEntities =
			getCategoriesEntities();
		AttributeEntity attributeEntity = categoryEntities.get(category);
		// TODO QUESTION: If the category doesn't exist, should we throw an exception or just ignore it?
		if (attributeEntity == null)
			return;
		
		abstractSet<Attribute> attributes = attributeEntity.getAttributes();
		abstractSet<Attribute> toRemove=new abstractSet<Attribute>();
		for (Attribute a : attributes) {
			String otherIssuer=a.getIssuer();
			if (issuer == null && otherIssuer == null ||
					(issuer != null && otherIssuer != null && issuer.equals(otherIssuer))) {
				if (a.getId().equals(id)) {
					// Remove attribute
					toRemove.add(a);
				}
			}
		}
		attributes.removeAll(toRemove);
		attributeEntity.setAttributes(attributes);	
	}

	@Override
	public void setCategoriesAttributeEntity(
			abstractMap<Identifier, AttributeEntity> categories) {
		myCategoriesEntities=categories;
	}

	@Override
	public void setCategoriesList(
			abstractMap<Identifier, abstractSet<Attribute>> categories) {
		myCategoriesEntities=new abstractMap<Identifier, AttributeEntity>();
		for (Identifier c: categories.keySet()) {
			AttributeEntity ae=new genericAttributeEntity();
			ae.setCategory(c);
			ae.setAttributes(categories.get(c));
			myCategoriesEntities.put (c, ae);
		}
	}

	@Override
	public void setImmutable() {
		this.mutable=false;
	}

	@Override
	public Identifier getRepeatedCategoryId() {
		return repeatedCategoryId;
	}

	@Override
	public void setRepeatedCategoryId(Identifier id) {
		// This method must not be called twice!
		if (repeatedCategoryId != null) {
			throw new genericLayer2RuntimeException("Trying to call setRepeatedCategoryId more than once on the same Request!");
		}
		// Move the repeated category away from the myCategoriesEntities
		// set to the repeatedCategories set.
		repeatedCategories=new abstractSet<AttributeEntity>();
		if (myCategoriesEntities.containsKey(id)) {
			currentRepeatedAttributeEntity=myCategoriesEntities.remove(id);
			repeatedCategories.add(currentRepeatedAttributeEntity);
		}
	}

	@Override
	public void createNewRepeatedEntity() {
		if (repeatedCategoryId == null) {
			throw new genericLayer2RuntimeException("Called createNewRepeatedEntity(), but no repeated Category has been set for this Request!");
		}
		// Check whether the last AttributeEntity is empty and throw an Exception in that
		// case - we don't want any empty AttributeEntities except for the last one.
		if (currentRepeatedAttributeEntity.getAttributes().isEmpty()) {
			throw  new genericLayer2RuntimeException("Called createNewRepeatedEntity(), but the current repeated AttributeEntity is empty. This is not allowed - it must have at least one attribute.");
		}
		currentRepeatedAttributeEntity=new genericAttributeEntity();
		currentRepeatedAttributeEntity.setCategory(repeatedCategoryId);
		repeatedCategories.add(currentRepeatedAttributeEntity);
	}

}
