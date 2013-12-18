package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.PrologEngine;
import org.jpc.util.JpcPreferences;

import com.xsb.interprolog.NativeEngine;

public class InterPrologXsbNativeDriver extends InterPrologXsbDriver {

	public InterPrologXsbNativeDriver() {
	}
	
	public InterPrologXsbNativeDriver(String engineBinDirectory) {
		super(engineBinDirectory);
	}
	
	public InterPrologXsbNativeDriver(JpcPreferences preferences) {
		super(preferences);
	}
	
	public InterPrologXsbNativeDriver(JpcPreferences preferences, String engineBinDirectory) {
		super(preferences, engineBinDirectory);
	}
	
	@Override
	protected PrologEngine basicCreatePrologEngine() {
		return new InterPrologXsbEngine(new NativeEngine(getEngineBinDirectory()));
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
