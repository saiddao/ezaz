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

import org.EzAz.Layer2.Response;
import org.EzAz.Layer2.ResponseSetter;
import org.EzAz.Layer2.Result;
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
