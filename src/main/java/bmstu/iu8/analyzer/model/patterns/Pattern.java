package main.java.bmstu.iu8.analyzer.model.patterns;

import java.io.File;
import java.util.Collection;

public abstract class Pattern {
	
	protected Collection<File> sources;
	//TODO replace String with something more suitable
	// may need - file extensions
	protected String rule;

}
