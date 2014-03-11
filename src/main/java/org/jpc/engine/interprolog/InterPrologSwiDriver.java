package org.jpc.engine.interprolog;

import org.jpc.util.JpcPreferences;
import org.jpc.util.engine.supported.Swi;

import com.declarativa.interprolog.SWISubprocessEngine;

public class InterPrologSwiDriver extends InterPrologDriver {
	
	public InterPrologSwiDriver() {
		this(null, new JpcPreferences());
	}

	public InterPrologSwiDriver(String engineBinDirectory) {
		this(engineBinDirectory, new JpcPreferences());
	}
	
	public InterPrologSwiDriver(String engineBinDirectory, JpcPreferences preferences) {
		super(new Swi(), engineBinDirectory, preferences);
	}
	
	@Override
	public String getDefaultBinDirectory() {
		return getPreferences().getVar(Swi.SWI_BIN_DIRECTORY_PROPERTY_NAME);
	}
	
	@Override
	protected InterPrologEngine basicCreatePrologEngine() {
		return new InterPrologSwiYapEngine(new SWISubprocessEngine(getExecutableFullPath()));
	}

	@Override
	protected String getExecutableFileName() {
		return ((Swi)getEngineDescription()).getExecutableFileName();
	}
	
	@Override
	public boolean isDisabled() {
		return true;
	}

}
