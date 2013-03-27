package org.jpc.salt.interprolog;

import org.jpc.salt.TermContentHandler;
import org.jpc.salt.TermReader;
import org.jpc.term.interprolog.InterPrologTermWrapper;

public class InterPrologTermReader extends TermReader {

	private InterPrologTermWrapper interPrologTerm;
	
	public InterPrologTermReader(InterPrologTermWrapper interPrologTerm, TermContentHandler contentHandler) {
		super(contentHandler);
		this.interPrologTerm = interPrologTerm;
	}
	
	@Override
	public void read() {
		read(interPrologTerm);
	}
	
	public void read(InterPrologTermWrapper interPrologTerm) {
		if(interPrologTerm.isInteger()) {
			Number number = (Number) interPrologTerm.getTermModel().node;
			getContentHandler().startIntegerTerm(number.longValue());
		} else if(interPrologTerm.isFloat()) {
			Number number = (Number) interPrologTerm.getTermModel().node;
			getContentHandler().startFloatTerm(number.doubleValue());
		} else if (interPrologTerm.isVariable()) {
			getContentHandler().startVariable(interPrologTerm.getVariableName());
		} else if (interPrologTerm.isAtom()) {
			String name = (String) interPrologTerm.getTermModel().node;
			getContentHandler().startAtom(name);
		} else if(interPrologTerm.isCompound()) {
			getContentHandler().startCompound();
			String name = (String) interPrologTerm.getTermModel().node;
			getContentHandler().startAtom(name);
			for(InterPrologTermWrapper child : interPrologTerm.getArgs()) {
				read(child);
			}
			getContentHandler().endCompound();
		} else
			throw new RuntimeException("Unrecognized InterProlog term: " + interPrologTerm);
	}

}
