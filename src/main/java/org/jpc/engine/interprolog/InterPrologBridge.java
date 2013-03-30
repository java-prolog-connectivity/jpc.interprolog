package org.jpc.engine.interprolog;

import org.jpc.salt.JpcTermWriter;
import org.jpc.salt.interprolog.InterPrologTermReader;
import org.jpc.salt.interprolog.InterPrologTermWriter;
import org.jpc.term.Term;
import org.jpc.term.interprolog.InterPrologTermWrapper;

public class InterPrologBridge {

	public static InterPrologTermWrapper fromJpcToInterProlog(Term term) {
		InterPrologTermWriter interPrologTermWriter = new InterPrologTermWriter();
		term.read(interPrologTermWriter);
		return interPrologTermWriter.getFirst();
	}
	
	public static Term fromInterPrologToJpc(InterPrologTermWrapper term) {
		JpcTermWriter jpcTermWriter = new JpcTermWriter();
		new InterPrologTermReader(term, jpcTermWriter).read();
		return jpcTermWriter.getFirst();
	}
	
}
