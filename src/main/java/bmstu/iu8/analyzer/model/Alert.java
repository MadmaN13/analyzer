package main.java.bmstu.iu8.analyzer.model;

import java.util.Date;

public class Alert {
	private Date timestamp;
	private String occurenceDescription;
	private Weakness weakness;
	
	public Alert(Date timestamp, String occurenceDescription, 
			Weakness weakness) {
		setTimestamp(timestamp);
		setOccurenceDescription(occurenceDescription);
		setWeakness(weakness);
	}

	@Override
	public String toString() {
		return "Timestamp: " + getTimestamp().toString() + "\r\n" +
				"Description: " + getOccurenceDescription() + "\r\n" +
				"Weakness: " + weakness.getDescription() + "\r\n";
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	private void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getOccurenceDescription() {
		return occurenceDescription;
	}

	private void setOccurenceDescription(String occurenceDescription) {
		this.occurenceDescription = occurenceDescription;
	}

	public Weakness getWeakness() {
		return weakness;
	}

	private void setWeakness(Weakness weakness) {
		this.weakness = weakness;
	}

}
