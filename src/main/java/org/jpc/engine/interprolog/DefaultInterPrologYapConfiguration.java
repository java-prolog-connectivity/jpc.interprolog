package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.PrologEngine;

import com.declarativa.interprolog.YAPSubprocessEngine;

public class DefaultInterPrologYapConfiguration extends DefaultInterPrologConfiguration {

	public static final String YAP = "yap";
	public String YAP_BIN_DIRECTORY_PROPERTY_NAME = "YAP_BIN_DIRECTORY";
	
	@Override
	public boolean configure() {
		setEngineBinDirectory(preferences.getVarOrThrow(YAP_BIN_DIRECTORY_PROPERTY_NAME));
		return true;
	}
	
	@Override
	protected PrologEngine basicCreatePrologEngine() {
		if(isNative())
			throw new UnsupportedOperationException("Impossible to create a native engine for " + getEngineName() + " using " + getLibraryName());
		else
			return new InterPrologEngine(new YAPSubprocessEngine(getEngineBinDirectory()));
	}
	
	@Override
	public String getEngineName() {
		return YAP;
	}
	
}
