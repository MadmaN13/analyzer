package main.java.bmstu.iu8.analyzer.model;

import main.java.bmstu.iu8.analyzer.model.mitre.CAPEC;
import main.java.bmstu.iu8.analyzer.model.mitre.CWE;
import main.java.bmstu.iu8.analyzer.model.patterns.Pattern;

public class Weakness {
	
	private String name;
	private String category;
	private CWE cwe;
	private CAPEC capec;
	private Pattern pattern;
	
	public Weakness() {
		super();
		setName("");
		setCategory("");
		setCwe(new CWE());
		setCapec(new CAPEC());
		setPattern(null);
	}
	
	public Weakness(String name, String category, CWE cwe, CAPEC capec, Pattern pattern) {
		super();
		setName(name);
		setCategory(category);
		setCwe(cwe);
		setCapec(capec);
		setPattern(pattern);
	}
	

	@Override
	public String toString() {
		return "Description: " + getName() + "\r\n" +
				"Category: " + getCategory() + "\r\n" +
				"CWE: " + cwe.getName() + "\r\n" +
				"CAPEC: " + capec.getName() + "\r\n" ;
//				"Pattern: " + pattern.toString() + "\r\n" ;
	}
	
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	private void setCategory(String category) {
		this.category = category;
	}
	public CWE getCwe() {
		return cwe;
	}
	private void setCwe(CWE cwe) {
		this.cwe = cwe;
	}
	public CAPEC getCapec() {
		return capec;
	}
	private void setCapec(CAPEC capec) {
		this.capec = capec;
	}
	public Pattern getPattern() {
		return pattern;
	}
	private void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	
}
