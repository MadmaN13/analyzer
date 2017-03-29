package main.java.bmstu.iu8.analyzer.model.mitre;

public class CAPEC extends MitreSource {

	public CAPEC(String name, int number, String descriptionUrl) {
		super(name, number, descriptionUrl);
	}
	
	public CAPEC() {
		super();
	}
	
	@Override
	public String toString() {
		return 
				"----------CAPEC---------" + "\r\n" +
				super.toString();
	}
}
