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

import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.abstractSet;

public class genericAttributeEntity implements AttributeEntity {

	abstractSet<Attribute> attributes=new abstractSet<Attribute>();
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

	public static void prettyPrint(PrintStream ps, String header, AttributeEntity ent) {
		abstractSet<Attribute> attributes = ent.getAttributes();
		System.out.println (header+"CATEGORY: "+ent.getCategory().toString());
		for (Attribute a: attributes) {
			genericAttribute.prettyPrint(ps, header+"  ", a);
		}

	}

}
