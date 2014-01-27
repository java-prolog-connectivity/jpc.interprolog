package org.jpc.interprolog;

import org.jpc.PrologEngineTestSuite;
import org.jpc.examples.PrologExamplesTestSuite;
import org.jpc.salt.interprolog.InterPrologTransformationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({PrologEngineTestSuite.class, PrologExamplesTestSuite.class, InterPrologTransformationTest.class})
public class InterPrologPrologEngineTestSuite {
}
