package org.jpc.interprolog;

import org.jpc.util.config.EngineConfigurationManager;
import org.junit.BeforeClass;

public class InterPrologXsbPrologEngineTestSuite extends InterPrologPrologEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		EngineConfigurationManager engineConfigurationManager = EngineConfigurationManager.createFromFile("jpc_xsb.settings");
		EngineConfigurationManager.setDefault(engineConfigurationManager);
	}
}
