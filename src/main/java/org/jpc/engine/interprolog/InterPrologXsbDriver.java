package org.jpc.engine.interprolog;

import org.jpc.util.JpcPreferences;
import org.jpc.util.engine.supported.Xsb;

import com.declarativa.interprolog.XSBSubprocessEngine;

public class InterPrologXsbDriver extends InterPrologDriver  {

	public InterPrologXsbDriver() {
		this(null, new JpcPreferences());
	}
	
	public InterPrologXsbDriver(String engineBinDirectory) {
		this(engineBinDirectory, new JpcPreferences());
	}
	
	public InterPrologXsbDriver(String engineBinDirectory, JpcPreferences preferences) {
		super(new Xsb(), engineBinDirectory, preferences);
	}
	
	@Override
	public String getDefaultBinDirectory() {
		return getPreferences().getVar(Xsb.XSB_BIN_DIRECTORY_PROPERTY_NAME);
	}
	
	@Override
	protected InterPrologEngine basicCreatePrologEngine() {
		return new InterPrologXsbEngine(new XSBSubprocessEngine(getExecutableFullPath()));
	}

	@Override
	protected String getExecutableFileName() {
		return ((Xsb)getEngineDescription()).getExecutableFileName();
	}

}
