package org.jpc.salt.interprolog;

import org.jpc.salt.TermBuilder;
import org.jpc.term.interprolog.InterPrologTermWrapper;

import com.declarativa.interprolog.TermModel;

public class InterPrologTermBuilder extends TermBuilder<InterPrologTermWrapper> {

	@Override
	public InterPrologTermWrapper build() {
		InterPrologTermWrapper builtTerm;
		if(!isCompound())
			builtTerm = getFunctor();
		else {
			if(getFunctor().isAtom()) {
				TermModel tm = new TermModel((getFunctor().getTermModel().node), getArgs().toArray(new TermModel[]{}));
				builtTerm = new InterPrologTermWrapper(tm, getFunctor().getVariablesNames());//the variables names of the functor and the arguments should be the same
			}
			else
				throw new RuntimeException("Invalid functor type");
		}
		return builtTerm;
	}
	
}
