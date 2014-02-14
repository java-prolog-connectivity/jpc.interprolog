package org.jpc.interprolog;

import org.jpc.engine.logtalk.LogtalkEngineTestSuite;
import org.jpc.examples.LogtalkExamplesTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({LogtalkEngineTestSuite.class, LogtalkExamplesTestSuite.class})
public class InterPrologLogtalkEngineTestSuite {
}
