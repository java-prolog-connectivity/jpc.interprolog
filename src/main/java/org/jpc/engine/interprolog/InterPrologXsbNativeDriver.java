package org.jpc.engine.interprolog;

import org.jpc.JpcPreferences;
import org.jpc.engine.prolog.PrologEngine;

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
		return new InterPrologEngine(new NativeEngine(getEngineBinDirectory()));
	}
	
	@Override
	public String getLibraryName() {
		return super.getLibraryName() + "(Native)";
	}

}
