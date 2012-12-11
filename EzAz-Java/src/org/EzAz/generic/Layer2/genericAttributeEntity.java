package org.EzAz.generic.Layer2;

import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.abstractSet;

public class genericAttributeEntity implements AttributeEntity {

	abstractSet<Attribute> attributes;
	Identifier category;
	Object content;
	
	@Override
	public abstractSet<Attribute> getAttributes() {
		return attributes;
	}

	@Override
	public Identifier getCategory() {
		return category;
	}

	@Override
	public Object getContent() {
		return content;
	}

	@Override
	public void setAttributes(abstractSet<Attribute> attributes) {
		this.attributes=attributes;

	}

	@Override
	public void setCategory(Identifier id) {
		this.category=id;
	}

	@Override
	public void setContent(Object content) {
		this.content=content;
	}

}