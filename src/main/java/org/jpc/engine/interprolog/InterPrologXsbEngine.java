package org.jpc.engine.interprolog;

import org.jpc.Jpc;
import org.jpc.query.Query;
import org.jpc.term.Term;

import com.declarativa.interprolog.PrologEngine;

public class InterPrologXsbEngine extends InterPrologEngine {

	public InterPrologXsbEngine(PrologEngine wrappedEngine) {
		super(wrappedEngine);
	}
	
	@Override
	public Query basicQuery(Term goal, boolean errorHandledQuery, Jpc context) {
		return new InterPrologXsbQuery(this, goal, errorHandledQuery, context);
	}
	
}
