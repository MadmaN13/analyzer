package main.java.bmstu.iu8.analyzer.datasource.mappers;

import javax.xml.bind.JAXBElement;

import main.java.bmstu.iu8.analyzer.datasource.xml.XmlMitreSourceType;
import main.java.bmstu.iu8.analyzer.datasource.xml.XmlWeaknessType;
import main.java.bmstu.iu8.analyzer.model.Weakness;
import main.java.bmstu.iu8.analyzer.model.mitre.CAPEC;
import main.java.bmstu.iu8.analyzer.model.mitre.CWE;

public class WeaknessMapper implements JAXBElementToObjectMapper<XmlWeaknessType, Weakness> {

	@Override
	public Weakness map(JAXBElement<XmlWeaknessType> element) {
		Weakness weakness = new Weakness();
		if (element != null) {
			XmlWeaknessType xmlWeaknessType = element.getValue();
			String description = xmlWeaknessType.getDescription();
			String category = xmlWeaknessType.getCategory();
			XmlMitreSourceType cweType = xmlWeaknessType.getCwe();
			CWE cwe = createCWE(cweType);
			XmlMitreSourceType capecType = xmlWeaknessType.getCapec();
			CAPEC capec = createCAPEC(capecType);
			weakness = new Weakness(description, category, cwe, capec, null);
		}
		return weakness;
	}

	private CWE createCWE(XmlMitreSourceType cweType) {
		return new CWE(	cweType.getName(),
						cweType.getNumber().intValue(), 
						cweType.getUrl());
	}

	private CAPEC createCAPEC(XmlMitreSourceType capecType) {
		return new CAPEC(	capecType.getName(),
							capecType.getNumber().intValue(), 
							capecType.getUrl());
	}
}
