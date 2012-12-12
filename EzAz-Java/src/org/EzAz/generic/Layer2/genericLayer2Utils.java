package org.EzAz.generic.Layer2;

import org.EzAz.Layer2.Attribute;
import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Request;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.Result;
import org.EzAz.Layer2.Status;
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

	public static void prettyPrint(Response resp) {
		System.out.println ("RESPONSE");
		System.out.println ("  RESULT: ");
		for (Result result: resp.getResults()) {
			String decision;
			int dec = result.getDecision();
			if (dec == Result.RESULT_PERMIT)
				decision="PERMIT";
			else if (dec == Result.RESULT_DENY)
				decision="DENY";
			else if (dec == Result.RESULT_NOT_APPLICABLE)
				decision="NOT APPLICABLE";
			else if (dec == Result.RESULT_INDETERMINATE)
				decision="INDETERMINATE";
			else
				decision="UNKNOWN_VALUE";
			System.out.println ("    DECISION: "+decision);
			Status status = result.getStatus();
			String tmp=status.getStatusCode();
			System.out.println("    STATUS CODE: "+(tmp != null? tmp : ""));
			tmp=status.getStatusDetail();
			System.out.println("    STATUS DETAIL: "+(tmp != null? tmp : ""));
			tmp=status.getStatusMessage();
			System.out.println("    STATUS MESSAGE: "+(tmp != null? tmp : ""));
			abstractMap<Identifier, AttributeEntity> categoriesEntities = result.getCategoriesEntities();
			if (categoriesEntities != null) {
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
	}
}
