package org.jpc.engine.interprolog;

import static java.util.Arrays.asList;

import org.jpc.Jpc;
import org.jpc.term.Atom;
import org.jpc.term.Compound;
import org.jpc.term.Term;
import org.jpc.term.Variable;

public class InterPrologSwiYapQuery extends InterPrologQuery {

	public InterPrologSwiYapQuery(InterPrologEngine prologEngine, Term goal, Jpc context) {
		super(prologEngine, goal, context);
	}
	
	@Override
	protected Term instrumentGoal(Term goal) {
		goal = super.instrumentGoal(goal);
		Term atomToTerm = new Compound("atom_to_term", asList(new Atom(goal.toString()), new Variable(INSTRUMENTED_GOAL), new Variable(ALL_VARIABLES)));
		Term buildTermModel = new Compound("buildTermModel", asList(new Variable(ALL_VARIABLES), new Variable(ALL_VARIABLES_TERM_MODEL)));
		Term instrumentedGoal = new Compound(",", asList(atomToTerm, new Variable(INSTRUMENTED_GOAL)));
		return new Compound(",", asList(instrumentedGoal, buildTermModel)); //buildTermModel should be the last predicate
	}
	
}
