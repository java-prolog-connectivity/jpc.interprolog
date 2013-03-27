package org.jpc.engine.interprolog;

import static org.jpc.JpcPreferences.JPC_VAR_PREFIX;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jpc.Jpc;
import org.jpc.query.DeterministicPrologQuery;
import org.jpc.term.Term;
import org.jpc.term.interprolog.InterPrologTermWrapper;

import com.declarativa.interprolog.TermModel;
import com.declarativa.interprolog.util.VariableNode;

public class InterPrologQuery extends DeterministicPrologQuery {

	private com.declarativa.interprolog.PrologEngine wrappedInterPrologEngine;
	private TermModel interPrologQuery;
	
	public static final String ALL_VARIABLES = JPC_VAR_PREFIX + "ALL_VARIABLES";
	
	public InterPrologQuery(InterPrologEngine prologEngine, Term goal, Jpc context) {
		super(prologEngine, goal, context);
		wrappedInterPrologEngine = prologEngine.getWrappedEngine();
		InterPrologTermWrapper termWrapper = InterPrologBridge.fromJpcToInterProlog(goal);
		interPrologQuery = asInterPrologQuery(termWrapper);
	}

	private TermModel asInterPrologQuery(InterPrologTermWrapper interPrologTermWrapper) {
		TermModel listUnification = TermModel.makeList(interPrologTermWrapper.getVariablesUnification().toArray(new TermModel[]{}));
		TermModel unificationAllVariables = new TermModel("=", new TermModel[]{new TermModel(ALL_VARIABLES), listUnification});
		TermModel bindingTerm = new TermModel(",", new TermModel[]{unificationAllVariables, interPrologTermWrapper.getTermModel()});
		return bindingTerm;
	}
	
	@Override
	public synchronized Map<String, Term> oneSolution() {
		TermModel boundTerm = wrappedInterPrologEngine.deterministicGoal(interPrologQuery);
		TermModel listUnification = (TermModel) ((TermModel)boundTerm.getChild(0)).getChild(1);
		TermModel[] unifications = listUnification.flatList();
		Map<VariableNode, String> variablesNames = new HashMap<>();
		Map<String, InterPrologTermWrapper> interPrologBindings = new HashMap<>();
		for(TermModel unification : unifications) {
			String name = (String)((TermModel)unification.getChild(0)).node;
			Object value = (TermModel)((TermModel)unification.getChild(1)).node;
			if(value instanceof VariableNode) {
				VariableNode variable = (VariableNode)value;
				variablesNames.put(variable, name);
				interPrologBindings.put(name, new InterPrologTermWrapper(new TermModel(variable), variablesNames));
			} else
				interPrologBindings.put(name, new InterPrologTermWrapper((TermModel)value, variablesNames));
		}
		Map<String, Term> oneSolution = new HashMap<>();
		for(Entry<String, InterPrologTermWrapper> interPrologBinding : interPrologBindings.entrySet()) {
			Term term = InterPrologBridge.fromInterPrologToJpc(interPrologBinding.getValue());
			oneSolution.put(interPrologBinding.getKey(), term);
		}
		return oneSolution;
	}

}
