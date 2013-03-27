package org.jpc.term.interprolog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jpc.engine.prolog.PrologConstants;

import com.declarativa.interprolog.TermModel;
import com.declarativa.interprolog.util.VariableNode;

/**
 * InterProlog terms represent variables as (objects wrapping) integers. 
 * Transforming directly from JPC to InterProlog terms will loose the variables names.
 * Therefore this class wraps the original InterProlog term representation and adds a map keeping the name of term variables.
 * @author sergioc
 *
 */
public class InterPrologTermWrapper {

	private Map<VariableNode, String> variablesNames;
	private TermModel termModel;

	public InterPrologTermWrapper(TermModel termModel) {
		this(termModel, new HashMap<VariableNode, String>());
	}
	
	public InterPrologTermWrapper(TermModel termModel, Map<VariableNode, String> variablesNames) {
		this.termModel = termModel;
		this.variablesNames = variablesNames;
	}
	
	public Map<VariableNode, String> getVariablesNames() {
		return variablesNames;
	}

	public TermModel getTermModel() {
		return termModel;
	}
	
	public boolean isCompound() {
		return !termModel.isLeaf();
	}
	
	public boolean isAtom() {
		return termModel.isAtom();
	}
	
	public boolean isInteger() {
		return termModel.isInteger();
	}
	
	public boolean isFloat() {
		return termModel.isNumber() && !termModel.isInteger();
	}
	
	public boolean isVariable() {
		return termModel.isVar();
	}
	
	public String getVariableName() {
		if(!isVariable())
			throw new RuntimeException("Term is not a variable");
		String variableName = variablesNames.get(termModel.node);
		return variableName!=null?variableName:PrologConstants.ANONYMOUS_VAR_NAME;
	}
	
	public List<InterPrologTermWrapper> getArgs() {
		List<InterPrologTermWrapper> args = new ArrayList<>();
		for(TermModel argTermModel : termModel.getChildren()) {
			args.add(new InterPrologTermWrapper(argTermModel, variablesNames));
		}
		return args;
	}

	@Override
	public String toString() {
		return termModel.toString();
	}
	
	public List<TermModel> getVariablesUnification() {
		List<TermModel> variablesUnification = new ArrayList<>();
		for(Entry<VariableNode, String> entry : getVariablesNames().entrySet()) {
			variablesUnification.add(new TermModel("=", new TermModel[]{new TermModel(entry.getValue()), new TermModel(entry.getKey())}));
		}
		return variablesUnification;
	}


}
