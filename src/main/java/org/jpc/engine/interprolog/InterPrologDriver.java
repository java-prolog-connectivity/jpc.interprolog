package org.jpc.engine.interprolog;

import java.io.File;

import org.jpc.engine.prolog.PrologEngine;
import org.jpc.engine.prolog.PrologEngineInitializationException;
import org.jpc.engine.prolog.driver.AbstractPrologEngineDriver;
import org.jpc.util.JpcPreferences;

public abstract class InterPrologDriver extends AbstractPrologEngineDriver {

	public static final String INTERPROLOG_LIBRARY_NAME = "InterProlog";

	private String engineBinDirectory; //should be set by the configure method of the non-abstract descendant classes
	
	public InterPrologDriver() {
		setEngineBinDirectory(getDefaultBinDirectory());
	}
	
	public InterPrologDriver(JpcPreferences preferences) {
		super(preferences);
		setEngineBinDirectory(getDefaultBinDirectory());
	}
	
	public InterPrologDriver(String engineBinDirectory) {
		setEngineBinDirectory(engineBinDirectory);
	}
	
	public InterPrologDriver(JpcPreferences preferences, String engineBinDirectory) {
		this(preferences);
		setEngineBinDirectory(engineBinDirectory);
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
	public void readyOrThrow() {
		if(engineBinDirectory == null)
			throw new PrologEngineInitializationException("Missing the Prolog engine bin directory.");
		if(!new File(engineBinDirectory).exists())
			throw new PrologEngineInitializationException("Prolog bin directory does not exist: " + engineBinDirectory + ".");
	}
	
	@Override
	protected void onCreate(PrologEngine logicEngine) {
		//logicEngine.query("import read_atom_to_term/3 from string").oneSolution(); //this would not work since read_atom_to_term/3 is needed for bootstrapping.
		//it has to be done with the native API.
		com.declarativa.interprolog.PrologEngine interPrologEngine = ((InterPrologEngine)logicEngine).getWrappedEngine();
		interPrologEngine.command("(import read_atom_to_term/3 from string), (import closetail/1 from listutil)");
	}
	
	protected String getExecutableFullPath() {
		return engineBinDirectory + File.separator + getExecutableFileName();
	}
	
	protected abstract String getExecutableFileName();
	protected abstract String getDefaultBinDirectory();

	@Override
	public String getLicenseUrl() {
		return "http://www.gnu.org/licenses/gpl-2.0.html";
	}
	
	@Override
	public String getSiteUrl() {
		return "http://www.declarativa.pt/InterProlog/";
	}
	
}
