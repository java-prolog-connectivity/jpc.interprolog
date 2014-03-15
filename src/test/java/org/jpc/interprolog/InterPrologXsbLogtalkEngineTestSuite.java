package org.jpc.interprolog;

import org.jpc.util.config.EngineConfigurationManager;
import org.junit.BeforeClass;

public class InterPrologXsbLogtalkEngineTestSuite extends InterPrologLogtalkEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		EngineConfigurationManager engineConfigurationManager = EngineConfigurationManager.createFromFile("jpc_xsb_logtalk.settings");
		EngineConfigurationManager.setDefault(engineConfigurationManager);
	}
}
