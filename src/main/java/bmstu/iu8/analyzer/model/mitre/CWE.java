package main.java.bmstu.iu8.analyzer.model.mitre;

public class CWE extends MitreSource {

	public CWE(String name, int number, String descriptionUrl) {
		super(name, number, descriptionUrl);
	}
	
	public CWE() {
		super();
	}
	
	@Override
	public String toString() {
		return 
				"-----------CWE----------" + "\r\n" +
				super.toString();
	}

}
