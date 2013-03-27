package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.PrologEngine;

import com.declarativa.interprolog.SWISubprocessEngine;

public class DefaultInterPrologSwiConfiguration extends DefaultInterPrologConfiguration {

	public static final String SWI = "swi";
	public String SWI_BIN_DIRECTORY_PROPERTY_NAME = "SWI_BIN_DIRECTORY";
	
	@Override
	public boolean configure() {
		setEngineBinDirectory(preferences.getVarOrThrow(SWI_BIN_DIRECTORY_PROPERTY_NAME));
		return true;
	}

	@Override
	protected PrologEngine basicCreatePrologEngine() {
		if(isNative())
			throw new UnsupportedOperationException("Impossible to create a native engine for " + getEngineName() + " using " + getLibraryName());
		else
			return new InterPrologEngine(new SWISubprocessEngine(getEngineBinDirectory()));
	}
	
	@Override
	public String getEngineName() {
		return SWI;
	}
	
}
