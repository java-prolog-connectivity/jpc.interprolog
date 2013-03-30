package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.PrologEngine;

import com.declarativa.interprolog.YAPSubprocessEngine;

public class DefaultInterPrologYapConfiguration extends DefaultInterPrologConfiguration {

	public static final String YAP = "yap";
	public static final String YAP_BIN_DIRECTORY_PROPERTY_NAME = "YAP_BIN_DIRECTORY";
	
	public DefaultInterPrologYapConfiguration() {
	}
	
	public DefaultInterPrologYapConfiguration(String engineBinDirectory) {
		super(engineBinDirectory);
	}
	
	@Override
	public String getBinDirectoryPropertyOrThrow() {
		return preferences.getVarOrThrow(YAP_BIN_DIRECTORY_PROPERTY_NAME);
	}

	@Override
	protected PrologEngine basicCreatePrologEngine() {
		return new InterPrologEngine(new YAPSubprocessEngine(getExecutableFullPath()));
	}
	
	@Override
	public String getEngineName() {
		return YAP;
	}

	@Override
	protected String getExecutableFileName() {
		return "yap";
	}
	
}
