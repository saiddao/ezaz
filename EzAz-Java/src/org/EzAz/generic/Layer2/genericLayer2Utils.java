package org.EzAz.generic.Layer2;

import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.abstractMap;
import org.EzAz.Layer2.abstractSet;

public class genericLayer2Utils {

	public static void prettyPrint(Request r) {
		System.out.println ("REQUEST");
		abstractMap<Identifier, AttributeEntity> categoriesEntities = r.getCategoriesEntities();
		for (Identifier i: categoriesEntities.keySet()) {
			System.out.println ("  CATEGORY: "+i.toASCIIString());
			AttributeEntity ent=categoriesEntities.get(i);
			abstractSet<Attribute> attributes = ent.getAttributes();
			for (Attribute a: attributes) {
				System.out.println ("    ATTR: "+a.getId().toASCIIString()+"="+a.getValue().toString()+" ("+a.getType().toASCIIString()+")");
			}
		}
	}

}
