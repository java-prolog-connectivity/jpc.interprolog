package org.jpc.engine.interprolog;

import org.jpc.JpcPreferences;
import org.jpc.engine.prolog.PrologEngine;
import org.jpc.util.supportedengines.EngineDescription;
import org.jpc.util.supportedengines.Swi;

import com.declarativa.interprolog.SWISubprocessEngine;

public class InterPrologSwiDriver extends InterPrologDriver {
	
	private Swi engineDescription = new Swi();
	
	public InterPrologSwiDriver() {
	}
	
	public InterPrologSwiDriver(String engineBinDirectory) {
		super(engineBinDirectory);
	}

	public InterPrologSwiDriver(JpcPreferences preferences) {
		super(preferences);
	}
	
	public InterPrologSwiDriver(JpcPreferences preferences, String engineBinDirectory) {
		super(preferences, engineBinDirectory);
	}
	
	@Override
	public String getDefaultBinDirectory() {
		return getPreferences().getVar(Swi.SWI_BIN_DIRECTORY_PROPERTY_NAME);
	}
	
	@Override
	protected PrologEngine basicCreatePrologEngine() {
		return new InterPrologEngine(new SWISubprocessEngine(getExecutableFullPath()));
	}
	
	@Override
	public EngineDescription getEngineDescription() {
		return engineDescription;
	}

	@Override
	protected String getExecutableFileName() {
		return engineDescription.getExecutableFileName();
	}
	
}
