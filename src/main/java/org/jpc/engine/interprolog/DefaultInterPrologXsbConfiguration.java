package org.jpc.engine.interprolog;

import org.jpc.engine.prolog.PrologEngine;

import com.declarativa.interprolog.XSBSubprocessEngine;
import com.xsb.interprolog.NativeEngine;

public class DefaultInterPrologXsbConfiguration extends DefaultInterPrologConfiguration  {

	public String XSB_BIN_DIRECTORY_PROPERTY_NAME = "XSB_BIN_DIRECTORY"; //environment variable with the path to the XSB executable.
	public static final String XSB = "xsb";
	
	@Override
	public boolean configure() {
		setEngineBinDirectory(preferences.getVarOrThrow(XSB_BIN_DIRECTORY_PROPERTY_NAME));
		return true;
	}
	
	@Override
	protected PrologEngine basicCreatePrologEngine() {
		if(isNative())
			return new InterPrologEngine(new NativeEngine(getEngineBinDirectory()));
		else
			return new InterPrologEngine(new XSBSubprocessEngine(getEngineBinDirectory()));
	}
	
	@Override
	public String getEngineName() {
		return XSB;
	}

}
