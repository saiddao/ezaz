package org.EzAz.generic.Layer2;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.EzAz.Layer2.AttributeEntity;
import org.EzAz.Layer2.Identifier;
import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.ResponseSetter;
import org.EzAz.Layer2.Result;
import org.EzAz.Layer2.Status;
import org.EzAz.Layer2.abstractMap;
import org.EzAz.Layer2.abstractSet;

public class genericResponse implements Response, ResponseSetter {

	private abstractSet<Result> results=new abstractSet<Result>();
	private boolean mutable;

	@Override
	public int getResultLength() {
		return results.size();
	}

	@Override
	public abstractSet<Result> getResults() {
		return results;
	}

	@Override
	public boolean isAllowed(boolean bias) {
		if (results == null || results.isEmpty())
			return bias;
		for (Result r: results) {
			if (r.isAllowed(bias) == bias)
				return bias;
		}
		return !bias;
	}

	@Override
	public void addResult(Result result) {
		results.add(result);
	}

	@Override
	public void addResults(abstractSet<Result> results) {
		results.addAll(results);
	}

	@Override
	public boolean isMutable() {
		return mutable;
	}

	@Override
	public void setImmutable() {
			this.mutable=true;
	}

	@Override
	public void setResults(abstractSet<Result> results) {
		// TODO Auto-generated method stub
		
	}
	
	public static void prettyPrint(PrintStream ps, String header, Response resp) {
		ps.println ("RESPONSE");
		for (Result result: resp.getResults()) {
			ps.println ("  RESULT: ");
			genericResult.prettyPrint(ps, header+"  ", result);
		}
	}
	

}
