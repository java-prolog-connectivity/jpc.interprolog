package org.jpc.interprolog;

import static org.jpc.engine.provider.PrologEngineProviderManager.setPrologEngineProvider;

import org.jpc.engine.provider.SimpleEngineProvider;
import org.jpc.util.config.EngineConfigurationManager;
import org.junit.BeforeClass;

public class InterPrologXsbPrologEngineTestSuite extends InterPrologPrologEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		setPrologEngineProvider(new SimpleEngineProvider(EngineConfigurationManager.getDefault().forAlias("xsb")));
	}
}
