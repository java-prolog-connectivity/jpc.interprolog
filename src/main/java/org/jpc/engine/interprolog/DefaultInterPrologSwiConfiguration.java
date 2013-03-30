package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.PrologEngine;
import org.minitoolbox.OsUtil;

import com.declarativa.interprolog.SWISubprocessEngine;

public class DefaultInterPrologSwiConfiguration extends DefaultInterPrologConfiguration {

	public static final String SWI = "swi";
	public static final String SWI_BIN_DIRECTORY_PROPERTY_NAME = "SWI_BIN_DIRECTORY";
	
	public DefaultInterPrologSwiConfiguration() {
	}
	
	public DefaultInterPrologSwiConfiguration(String engineBinDirectory) {
		super(engineBinDirectory);
	}

	@Override
	public String getBinDirectoryPropertyOrThrow() {
		return preferences.getVarOrThrow(SWI_BIN_DIRECTORY_PROPERTY_NAME);
	}
	
	@Override
	protected PrologEngine basicCreatePrologEngine() {
		return new InterPrologEngine(new SWISubprocessEngine(getExecutableFullPath()));
	}
	
	@Override
	public String getEngineName() {
		return SWI;
	}

	@Override
	protected String getExecutableFileName() {
		if(OsUtil.osIsOsX() || OsUtil.osIsWindows())
			return "swipl";
		else
			return "pl";
	}
	
}
