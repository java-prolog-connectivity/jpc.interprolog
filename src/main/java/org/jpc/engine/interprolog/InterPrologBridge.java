package org.jpc.engine.interprolog;

import org.jpc.util.salt.JpcTermStreamer;
import org.jpc.util.salt.interprolog.InterPrologTermReader;
import org.jpc.util.salt.interprolog.InterPrologTermStreamer;
import org.jpc.term.Term;
import org.jpc.term.interprolog.InterPrologTermWrapper;

public class InterPrologBridge {

	public static InterPrologTermWrapper fromJpcToInterProlog(Term term) {
		InterPrologTermStreamer interPrologTermWriter = new InterPrologTermStreamer();
		term.read(interPrologTermWriter);
		return interPrologTermWriter.getFirst();
	}
	
	public static Term fromInterPrologToJpc(InterPrologTermWrapper term) {
		JpcTermStreamer jpcTermWriter = new JpcTermStreamer();
		new InterPrologTermReader(term, jpcTermWriter).read();
		return jpcTermWriter.getFirst();
	}
	
}
