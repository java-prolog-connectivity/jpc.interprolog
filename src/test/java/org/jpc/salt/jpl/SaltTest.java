package org.jpc.salt.jpl;


import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import org.jpc.salt.JpcTermWriter;
import org.jpc.salt.jpl.JplTermReader;
import org.jpc.salt.jpl.JplTermWriter;
import org.jpc.term.Atom;
import org.jpc.term.Compound;
import org.jpc.term.FloatTerm;
import org.jpc.term.IntegerTerm;
import org.jpc.term.Term;
import org.jpc.term.Variable;
import org.junit.Test;

public class SaltTest {

	jpl.Term t1Jpl = new jpl.Compound("name", new jpl.Term[] {new jpl.Compound("name2", new jpl.Term[] {new jpl.Atom("atom1"), new jpl.Integer(-10), new jpl.Float(10.5), new jpl.Variable("A"), new jpl.Variable("_A")})});
	Term t1Jpc = new Compound("name", asList(new Compound("name2", asList(new Atom("atom1"), new IntegerTerm(-10), new FloatTerm(10.5), new Variable("A"), new Variable("_A")))));
	
	@Test
	public void testJplToJpl() {
		JplTermWriter termWriter = new JplTermWriter();
		new JplTermReader(termWriter).stream(t1Jpl);
		assertEquals(t1Jpl, termWriter.getTerms().get(0));
	}
	
	@Test
	public void testJplToJpc() {
		JpcTermWriter jpcTermWriter = new JpcTermWriter();
		new JplTermReader(jpcTermWriter).stream(t1Jpl);
		assertEquals(t1Jpc, jpcTermWriter.getTerms().get(0));
	}
	
	@Test
	public void testJpcToJpl() {
		JplTermWriter jplTermWriter = new JplTermWriter();
		t1Jpc.streamTo(jplTermWriter);
		assertEquals(t1Jpl, jplTermWriter.getTerms().get(0));
	}
	
}
