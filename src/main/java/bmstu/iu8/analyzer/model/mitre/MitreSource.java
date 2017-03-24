package main.java.bmstu.iu8.analyzer.model.mitre;

public abstract class MitreSource {
	
	private String name;
	private int number;
	private String descriptionUrl;
	
	public MitreSource(String name, int number, String descriptionUrl) {
		super();
		setName(name);
		setNumber(number);
		setDescriptionUrl(descriptionUrl);
	}
	
	@Override
	public String toString() {
		return 
				"Name: " + getName() + "\r\n" +
				"Number: " + getNumber() + "\r\n" +
				"URL: " + getDescriptionUrl() + "\r\n" ;
	}
	
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	private void setNumber(int number) {
		this.number = number;
	}
	public String getDescriptionUrl() {
		return descriptionUrl;
	}
	private void setDescriptionUrl(String descriptionUrl) {
		this.descriptionUrl = descriptionUrl;
	}
}
