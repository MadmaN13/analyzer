package main.java.bmstu.iu8.analyzer.datasource.mappers;

import java.util.Date;

import javax.xml.bind.JAXBElement;

import main.java.bmstu.iu8.analyzer.datasource.xml.XmlAlertType;
import main.java.bmstu.iu8.analyzer.model.Alert;

public class AlertMapper implements JAXBElementToObjectMapper<XmlAlertType, Alert> {

	@Override
	public Alert map(JAXBElement<XmlAlertType> element) {
		Alert alert = new Alert();
		if (element != null) {
			XmlAlertType xmlAlertType = element.getValue();
			String description = xmlAlertType.getDescription();
			int id = xmlAlertType.getId().intValue();
			Date timestamp = xmlAlertType.getTimestamp().toGregorianCalendar().getTime();
			int weaknessId = xmlAlertType.getWeaknessId().intValue();
			alert = new Alert(id, timestamp, description, weaknessId);
		}
		return alert;
	}
}
