package main.java.bmstu.iu8.analyzer.datasource.mappers;

import javax.xml.bind.JAXBElement;

import main.java.bmstu.iu8.analyzer.datasource.xml.XmlMitreSourceType;
import main.java.bmstu.iu8.analyzer.model.mitre.CAPEC;

public class CAPECMapper implements JAXBElementToObjectMapper<XmlMitreSourceType, CAPEC> {

	@Override
	public CAPEC map(JAXBElement<XmlMitreSourceType> element) {
		CAPEC capec = new CAPEC();
		if (element != null) {
			XmlMitreSourceType mitreSourceType = element.getValue();
			String name = mitreSourceType.getName();
			int number = mitreSourceType.getNumber().intValue();
			String descriptionUrl = mitreSourceType.getUrl();
			capec = new CAPEC(name, number, descriptionUrl);
		}
		return capec;
	}

}
