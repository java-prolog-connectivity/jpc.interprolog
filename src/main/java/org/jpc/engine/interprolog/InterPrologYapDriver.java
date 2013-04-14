package org.jpc.engine.interprolog;

import org.jpc.JpcPreferences;
import org.jpc.engine.prolog.PrologEngine;

import com.declarativa.interprolog.YAPSubprocessEngine;

public class InterPrologYapDriver extends InterPrologDriver {

	public static final String YAP = "YAP";
	public static final String YAP_BIN_DIRECTORY_PROPERTY_NAME = "YAP_BIN_DIRECTORY";
	private static final String EXECUTABLE_FILE_NAME = "yap";
	
	public InterPrologYapDriver() {
	}
	
	public InterPrologYapDriver(String engineBinDirectory) {
		super(engineBinDirectory);
	}
	
	public InterPrologYapDriver(JpcPreferences preferences) {
		super(preferences);
	}
	
	public InterPrologYapDriver(JpcPreferences preferences, String engineBinDirectory) {
		super(preferences, engineBinDirectory);
	}
	
	@Override
	public String getDefaultBinDirectory() {
		return getPreferences().getVar(YAP_BIN_DIRECTORY_PROPERTY_NAME);
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
		return EXECUTABLE_FILE_NAME;
	}
	
}
