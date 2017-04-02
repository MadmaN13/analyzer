package main.java.bmstu.iu8.analyzer.model;

import java.util.Date;

public class Alert {
	private int id;
	private Date timestamp;
	private String occurenceDescription;
	private Weakness weakness;
	private int weaknessId;
	
	public Alert() {
		super();
		setId(-1);
		setTimestamp(new Date());
		setOccurenceDescription("");
		setWeakness(new Weakness());
		setWeaknessId(-1);
	}
	
	public Alert(int id, Date timestamp, String occurenceDescription, int weaknessId) {
		setId(id);
		setTimestamp(timestamp);
		setOccurenceDescription(occurenceDescription);
		setWeakness(new Weakness());
		setWeaknessId(weaknessId);
	}


	@Override
	public String toString() {
		return "Timestamp: " + getTimestamp().toString() + "\r\n" +
				"Description: " + getOccurenceDescription() + "\r\n" +
				"Weakness: " + weakness.getName() + "\r\n";
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

	public void setWeakness(Weakness weakness) {
		this.weakness = weakness;
	}

	public int getWeaknessId() {
		return weaknessId;
	}

	public void setWeaknessId(int weaknessId) {
		this.weaknessId = weaknessId;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

}
