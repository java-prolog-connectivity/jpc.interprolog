package org.jpc.salt.interprolog;

import static org.jpc.engine.prolog.PrologConstants.CONS_FUNCTOR;
import static org.jpc.engine.prolog.PrologConstants.EMPTY_LIST_SYMBOL;

import java.util.List;

import org.jpc.JpcException;
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
	
	private void read(InterPrologTermWrapper interPrologTerm) {
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
			if(interPrologTerm.isList()) {
				List<InterPrologTermWrapper> listChildren = interPrologTerm.listMembers();
				if(listChildren.isEmpty())
					throw new JpcException("Unexpected list size"); //if the list is empty the term should be an atom, not a compound.
				readList(listChildren);
			} else {
				getContentHandler().startCompound();
				String name = (String) interPrologTerm.getTermModel().node; //assuming the functors in TermModel objects representing compound terms are always Strings (no support for HiLog terms)
				getContentHandler().startAtom(name);
				for(InterPrologTermWrapper child : interPrologTerm.getArgs()) {
					read(child);
				}
				getContentHandler().endCompound();
			}
		} else
			throw new RuntimeException("Unrecognized InterProlog term: " + interPrologTerm);
	}

	private void readList(List<InterPrologTermWrapper> list) {
		readList(list, 0);
	}
	
	private void readList(List<InterPrologTermWrapper> list, int index) {
		if(list.size() == index)
			getContentHandler().startAtom(EMPTY_LIST_SYMBOL);
		else {
			getContentHandler().startCompound();
			getContentHandler().startAtom(CONS_FUNCTOR);
			read(list.get(index));
			readList(list, index+1);
			getContentHandler().endCompound();
		}
	}
	
}
