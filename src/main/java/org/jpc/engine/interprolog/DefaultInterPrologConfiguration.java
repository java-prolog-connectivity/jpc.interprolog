package org.jpc.engine.interprolog;

import java.io.File;

import org.jpc.engine.prolog.PrologEngine;
import org.jpc.engine.prolog.configuration.EngineConfigurationException;
import org.jpc.engine.prolog.configuration.PrologEngineConfiguration;
import org.minitoolbox.Preferences.MissingPropertyException;

public abstract class DefaultInterPrologConfiguration extends PrologEngineConfiguration {

	public static final String INTERPROLOG_LIBRARY_NAME = "InterProlog";

	private String engineBinDirectory; //should be set by the configure method of the non-abstract descendant classes
	private String executableFileName;

	public DefaultInterPrologConfiguration() {
		executableFileName = getExecutableFileName();
	}
	
	public DefaultInterPrologConfiguration(String engineBinDirectory) {
		setEngineBinDirectory(engineBinDirectory);
	}
	
	@Override
	public boolean isConfigured() {
		return engineBinDirectory != null;
	}
	
	public String getEngineBinDirectory() {
		return engineBinDirectory;
	}

	public void setEngineBinDirectory(String engineBinDirectory) {
		this.engineBinDirectory = engineBinDirectory;
	}
	
	@Override
	public String getLibraryName() {
		return INTERPROLOG_LIBRARY_NAME;
	}
	
	@Override
	public void onCreate(PrologEngine logicEngine) {
		//logicEngine.query("import read_atom_to_term/3 from string").oneSolution(); //this would not work since read_atom_to_term/3 is needed for bootstrapping.
		//it has to be done with the native API.
		com.declarativa.interprolog.PrologEngine interPrologEngine = ((InterPrologEngine)logicEngine).getWrappedEngine();
		interPrologEngine.command("import read_atom_to_term/3 from string");
	}
	
	@Override
	public void configure() {
		String engineBinDirectory;
		try {
			engineBinDirectory = getBinDirectoryPropertyOrThrow();
		} catch(MissingPropertyException e) {
			throw new EngineConfigurationException(e);
		}
		setEngineBinDirectory(engineBinDirectory);
	}
	
	protected String getExecutableFullPath() {
		return engineBinDirectory + File.separator + executableFileName;
	}
	
	protected abstract String getExecutableFileName();
	protected abstract String getBinDirectoryPropertyOrThrow();

}
