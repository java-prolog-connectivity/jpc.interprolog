package org.jpc.salt.interprolog;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jpc.salt.TermBuilder;
import org.jpc.salt.TermContentHandler;
import org.jpc.salt.TermWriter;
import org.jpc.term.interprolog.InterPrologTermWrapper;

import com.declarativa.interprolog.TermModel;
import com.declarativa.interprolog.util.VariableNode;

public class InterPrologTermWriter extends TermWriter<InterPrologTermWrapper> {

	private Map<VariableNode, String> variablesNames;
	private int varIdCounter;
	
	public InterPrologTermWriter() {
		resetVariablesNames();
	}
	
	private VariableNode getVariable(String variableName) {
		for(Entry<VariableNode,String> entry : variablesNames.entrySet()) {
			if(entry.getValue().equals(variableName))
				return entry.getKey();
		}
		return null;
	}
	
	private void resetVariablesNames() {
		variablesNames = new HashMap<>();
		varIdCounter = 0;
	}
	
	private VariableNode createVariable(String name) {
		VariableNode variable = new VariableNode(varIdCounter++);
		variablesNames.put(variable, name);
		return variable;
	}
	@Override
	protected void write(InterPrologTermWrapper term) {
		super.write(term);
		resetVariablesNames();
	}
	
	@Override
	public TermContentHandler startIntegerTerm(long value) {
		process(new InterPrologTermWrapper(new TermModel(value), variablesNames));
		return this;
	}

	@Override
	public TermContentHandler startFloatTerm(double value) {
		process(new InterPrologTermWrapper(new TermModel(value), variablesNames));
		return this;
	}

	@Override
	public TermContentHandler startVariable(String name) {
		VariableNode variable = getVariable(name);
		if(variable == null) {
			variable = createVariable(name);
		}
		process(new InterPrologTermWrapper(new TermModel(variable), variablesNames));
		return this;
	}
	
	@Override
	public TermContentHandler startAtom(String name) {
		process(new InterPrologTermWrapper(new TermModel(name), variablesNames));
		return this;
	}
	
	@Override
	protected TermBuilder<InterPrologTermWrapper> createCompoundBuilder() {
		return new InterPrologTermBuilder();
	}

}
