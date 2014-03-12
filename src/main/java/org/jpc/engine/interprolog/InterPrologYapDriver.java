package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.driver.PrologEngineFactory;
import org.jpc.util.JpcPreferences;
import org.jpc.util.engine.supported.Yap;

import com.declarativa.interprolog.YAPSubprocessEngine;

public class InterPrologYapDriver extends InterPrologDriver {

	public InterPrologYapDriver() {
		this(null, new JpcPreferences());
	}
	
	public InterPrologYapDriver(String engineBinDirectory) {
		this(engineBinDirectory, new JpcPreferences());
	}
	
	public InterPrologYapDriver(String engineBinDirectory, JpcPreferences preferences) {
		super(new Yap(), engineBinDirectory, preferences);
	}
	
	@Override
	public String getDefaultBinDirectory() {
		return getPreferences().getVar(Yap.YAP_BIN_DIRECTORY_PROPERTY_NAME);
	}

	@Override
	protected PrologEngineFactory<InterPrologEngine> defaultBasicFactory() {
		return new PrologEngineFactory<InterPrologEngine>() {
			@Override
			public InterPrologEngine createPrologEngine() {
				return new InterPrologSwiYapEngine(new YAPSubprocessEngine(getExecutableFullPath()));
			}
		};
	}

	@Override
	protected String getExecutableFileName() {
		return ((Yap)getEngineDescription()).getExecutableFileName();
	}
	
	@Override
	public boolean isDisabled() {
		return true;
	}
	
}
