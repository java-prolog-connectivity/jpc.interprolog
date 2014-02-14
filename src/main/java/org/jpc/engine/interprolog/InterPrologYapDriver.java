package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.PrologEngine;
import org.jpc.util.JpcPreferences;
import org.jpc.util.engine.supported.EngineDescription;
import org.jpc.util.engine.supported.Yap;

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
		return new InterPrologSwiYapEngine(new YAPSubprocessEngine(getExecutableFullPath()));
	}
	
	@Override
	public EngineDescription getEngineDescription() {
		return new Yap();
	}

	@Override
	protected String getExecutableFileName() {
		return EXECUTABLE_FILE_NAME;
	}
	
	@Override
	public boolean isDisabled() {
		return true;
	}
	
}
