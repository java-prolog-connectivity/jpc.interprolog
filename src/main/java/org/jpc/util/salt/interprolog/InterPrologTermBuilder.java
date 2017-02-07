package org.jpc.util.salt.interprolog;

import java.util.ArrayList;
import java.util.List;

import org.jpc.util.salt.TermBuilder;
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
				List<TermModel> termModelArgs = new ArrayList<>();
				for(InterPrologTermWrapper arg : getArgs()) {
					termModelArgs.add(arg.getTermModel());
				}
				TermModel tm = new TermModel((getFunctor().getTermModel().node), termModelArgs.toArray(new TermModel[]{}));
				builtTerm = new InterPrologTermWrapper(tm, getFunctor().getVariablesNames());//the variables names of the functor and the arguments should be the same
			}
			else
				throw new RuntimeException("Invalid functor type");
		}
		return builtTerm;
	}
	
}
