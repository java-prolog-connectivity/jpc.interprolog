package org.jpc.engine.interprolog;

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
	protected InterPrologEngine basicCreatePrologEngine() {
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
