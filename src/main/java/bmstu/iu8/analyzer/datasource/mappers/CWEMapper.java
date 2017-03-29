package main.java.bmstu.iu8.analyzer.datasource.mappers;

import javax.xml.bind.JAXBElement;

import main.java.bmstu.iu8.analyzer.datasource.xml.XmlMitreSourceType;
import main.java.bmstu.iu8.analyzer.model.mitre.CWE;

public class CWEMapper implements JAXBElementToObjectMapper<XmlMitreSourceType,CWE> {

	@Override
	public CWE map(JAXBElement<XmlMitreSourceType> element) {
		CWE cwe = new CWE();
		if (element !=null) {
			XmlMitreSourceType mitreSourceType = element.getValue();
			String name = mitreSourceType.getName();
			int number = mitreSourceType.getNumber().intValue();
			String descriptionUrl = mitreSourceType.getUrl();
			cwe = new CWE(name, number, descriptionUrl);
		}
		return cwe;
	}
}
