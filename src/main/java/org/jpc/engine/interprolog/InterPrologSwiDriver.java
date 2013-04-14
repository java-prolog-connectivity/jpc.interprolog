package org.jpc.engine.interprolog;

import org.jpc.JpcPreferences;
import org.jpc.engine.prolog.PrologEngine;
import org.minitoolbox.OsUtil;

import com.declarativa.interprolog.SWISubprocessEngine;

public class InterPrologSwiDriver extends InterPrologDriver {

	public static final String SWI = "SWI";
	public static final String SWI_BIN_DIRECTORY_PROPERTY_NAME = "SWI_BIN_DIRECTORY";
	
	private static final String EXECUTABLE_FILE_NAME_WINDOWS_OSX = "swipl"; //executable for windows or osx
	private static final String EXECUTABLE_FILE_NAME_LINUX = "pl"; //executable for linux
	
	public InterPrologSwiDriver() {
	}
	
	public InterPrologSwiDriver(String engineBinDirectory) {
		super(engineBinDirectory);
	}

	public InterPrologSwiDriver(JpcPreferences preferences) {
		super(preferences);
	}
	
	public InterPrologSwiDriver(JpcPreferences preferences, String engineBinDirectory) {
		super(preferences, engineBinDirectory);
	}
	
	@Override
	public String getDefaultBinDirectory() {
		return getPreferences().getVar(SWI_BIN_DIRECTORY_PROPERTY_NAME);
	}
	
	@Override
	protected PrologEngine basicCreatePrologEngine() {
		return new InterPrologEngine(new SWISubprocessEngine(getExecutableFullPath()));
	}
	
	@Override
	public String getEngineName() {
		return SWI;
	}

	@Override
	protected String getExecutableFileName() {
		if(OsUtil.osIsOsX() || OsUtil.osIsWindows())
			return EXECUTABLE_FILE_NAME_WINDOWS_OSX;
		else
			return EXECUTABLE_FILE_NAME_LINUX;
	}
	
}
