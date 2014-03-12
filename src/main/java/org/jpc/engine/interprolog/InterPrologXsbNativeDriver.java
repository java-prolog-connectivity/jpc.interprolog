package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.driver.PrologEngineFactory;
import org.jpc.util.JpcPreferences;

import com.xsb.interprolog.NativeEngine;

public class InterPrologXsbNativeDriver extends InterPrologXsbDriver {

	public InterPrologXsbNativeDriver() {
		super();
	}
	
	public InterPrologXsbNativeDriver(String engineBinDirectory) {
		super(engineBinDirectory);
	}
	
	public InterPrologXsbNativeDriver(String engineBinDirectory, JpcPreferences preferences) {
		super(engineBinDirectory, preferences);
	}
	
	@Override
	protected PrologEngineFactory<InterPrologEngine> defaultBasicFactory() {
		return new PrologEngineFactory<InterPrologEngine>() {
			@Override
			public InterPrologEngine createPrologEngine() {
				return new InterPrologXsbEngine(new NativeEngine(getEngineBinDirectory()));
			}
		};
	}
	
	@Override
	public String getLibraryName() {
		return super.getLibraryName() + "(Native)";
	}

	@Override
	public boolean isDisabled() {
		return true;
	}
	
}
