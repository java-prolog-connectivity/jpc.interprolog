package org.jpc.term.interprolog;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jpc.engine.prolog.PrologConstants;
import org.minitoolbox.reflection.BeansUtil;

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

	public static int getVariableCode(VariableNode variableNode) {
		return (Integer)BeansUtil.getProperty(variableNode, "number"); //ugly, to change after the bug of VariableNode not implementing hashCode is corrected so the map can be indexed by VariableNodes, not their int codes
	}
	
	
	public static List<InterPrologTermWrapper> asInterPrologTermWrappers(List<TermModel> ipTerms, Map<Integer, String> variablesNames) {
		List<InterPrologTermWrapper> termWrappers = new ArrayList<>();
		for(TermModel ipTerm : ipTerms) {
			termWrappers.add(new InterPrologTermWrapper(ipTerm, variablesNames));
		}
		return termWrappers;
	}
	
	private Map<Integer, String> variablesNames;
	private TermModel termModel;

//	public InterPrologTermWrapper(TermModel termModel) {
//		this(termModel, new HashMap<Integer, String>());
//	}
	
	public InterPrologTermWrapper(TermModel termModel, Map<Integer, String> variablesNames) {
		this.termModel = termModel;
		this.variablesNames = variablesNames;
	}
	
	public Map<Integer, String> getVariablesNames() {
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
	
	public boolean isList() {
		return termModel.isList();
	}
	
	/**
	 * @throws an IPException if the TermModel is not a proper list
	 * @return
	 */
	public List<InterPrologTermWrapper> listMembers() {
		return asInterPrologTermWrappers(asList(termModel.flatList()), variablesNames);
	}
	
	public List<InterPrologTermWrapper> getChildren() {
		return asInterPrologTermWrappers(asList(termModel.getChildren()), variablesNames);
	}
	
	public String getVariableName() {
		if(!isVariable())
			throw new RuntimeException("Term is not a variable");
		VariableNode variableNode = (VariableNode)termModel.node;
		Integer varCode = getVariableCode(variableNode);
		String variableName = variablesNames.get(varCode);
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
		for(Entry<Integer, String> entry : getVariablesNames().entrySet()) {
			variablesUnification.add(new TermModel("=", new TermModel[]{new TermModel(entry.getValue()), new TermModel(new VariableNode(entry.getKey()))}));
		}
		return variablesUnification;
	}

	//TODO reimplement this taking into consideration that TermModel does not implement hashCode
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((termModel == null) ? 0 : termModel.hashCode()); //TermModel does not implement hashCode so this may not work correctly
		result = prime * result
				+ ((variablesNames == null) ? 0 : variablesNames.hashCode());
		return result;
	}
*/
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterPrologTermWrapper other = (InterPrologTermWrapper) obj;
		if (termModel == null) {
			if (other.termModel != null)
				return false;
		} else if (!termModel.equals(other.termModel))
			return false;
		if (variablesNames == null) {
			if (other.variablesNames != null)
				return false;
		} else if (!variablesNames.equals(other.variablesNames))
			return false;
		return true;
	}


	
	
}
