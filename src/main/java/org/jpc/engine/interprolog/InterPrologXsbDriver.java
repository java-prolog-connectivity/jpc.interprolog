package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.PrologEngine;
import org.jpc.util.JpcPreferences;
import org.jpc.util.engine.supported.EngineDescription;
import org.jpc.util.engine.supported.Xsb;

import com.declarativa.interprolog.XSBSubprocessEngine;

public class InterPrologXsbDriver extends InterPrologDriver  {

	public static final String XSB_BIN_DIRECTORY_PROPERTY_NAME = "XSB_BIN_DIRECTORY"; //environment variable with the path to the XSB executable.
	public static final String XSB = "XSB";
	private static final String EXECUTABLE_FILE_NAME = "xsb";

	public InterPrologXsbDriver() {
	}
	
	public InterPrologXsbDriver(String engineBinDirectory) {
		super(engineBinDirectory);
	}
	
	public InterPrologXsbDriver(JpcPreferences preferences) {
		super(preferences);
	}
	
	public InterPrologXsbDriver(JpcPreferences preferences, String engineBinDirectory) {
		super(preferences, engineBinDirectory);
	}
	
	@Override
	public String getDefaultBinDirectory() {
		return getPreferences().getVar(XSB_BIN_DIRECTORY_PROPERTY_NAME);
	}
	
	@Override
	protected PrologEngine basicCreatePrologEngine() {
		return new InterPrologXsbEngine(new XSBSubprocessEngine(getExecutableFullPath()));
	}
	
	@Override
	public EngineDescription getEngineDescription() {
		return new Xsb();
	}

	@Override
	protected String getExecutableFileName() {
		return EXECUTABLE_FILE_NAME;
	}

}
