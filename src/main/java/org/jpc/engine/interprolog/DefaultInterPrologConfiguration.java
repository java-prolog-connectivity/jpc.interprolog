package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.PrologEngine;
import org.jpc.engine.prolog.configuration.PrologEngineConfiguration;

public abstract class DefaultInterPrologConfiguration extends PrologEngineConfiguration {

	public static final String INTERPROLOG_LIBRARY_NAME = "InterProlog";

	private String engineBinDirectory; //should be set by the configure method of the non-abstract descendant classes
	private boolean isNative;

	public String getEngineBinDirectory() {
		return engineBinDirectory;
	}

	public void setEngineBinDirectory(String engineBinDirectory) {
		this.engineBinDirectory = engineBinDirectory;
	}

	public boolean isNative() {
		return isNative;
	}

	public void setNative(boolean isNative) {
		this.isNative = isNative;
	}
	
	@Override
	public String getLibraryName() {
		return INTERPROLOG_LIBRARY_NAME;
	}
	
	@Override
	public void onCreate(PrologEngine logicEngine) {
		logicEngine.query("import read_atom_to_term/3 from string").oneSolution();
	}
	
}
