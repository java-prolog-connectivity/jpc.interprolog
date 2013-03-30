package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.PrologEngine;

import com.declarativa.interprolog.XSBSubprocessEngine;
import com.xsb.interprolog.NativeEngine;

public class DefaultInterPrologXsbConfiguration extends DefaultInterPrologConfiguration  {

	public static final String XSB_BIN_DIRECTORY_PROPERTY_NAME = "XSB_BIN_DIRECTORY"; //environment variable with the path to the XSB executable.
	public static final String XSB = "xsb";
	private boolean isNative;
	
	public DefaultInterPrologXsbConfiguration() {
	}
	
	public DefaultInterPrologXsbConfiguration(String engineBinDirectory) {
		super(engineBinDirectory);
	}
	
	@Override
	public String getBinDirectoryPropertyOrThrow() {
		return preferences.getVarOrThrow(XSB_BIN_DIRECTORY_PROPERTY_NAME);
	}
	
	public boolean isNative() {
		return isNative;
	}

	public void setNative(boolean isNative) {
		this.isNative = isNative;
	}
	
	@Override
	protected PrologEngine basicCreatePrologEngine() {
		if(isNative())
			return new InterPrologEngine(new NativeEngine(getEngineBinDirectory()));
		else
			return new InterPrologEngine(new XSBSubprocessEngine(getExecutableFullPath()));
	}
	
	@Override
	public String getEngineName() {
		return XSB;
	}

	@Override
	protected String getExecutableFileName() {
		return "xsb";
	}

}
