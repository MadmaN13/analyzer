package test.java.bmstu.iu8.analyzer.datasource.marshallers;

import static org.junit.Assert.*;

import java.math.BigInteger;

import javax.xml.bind.JAXBElement;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.bmstu.iu8.analyzer.datasource.marshallers.JAXBMarshaller;
import main.java.bmstu.iu8.analyzer.datasource.xml.ObjectFactory;
import main.java.bmstu.iu8.analyzer.datasource.xml.XmlMitreSourceType;

public class JAXBMarshallerTest {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(JAXBMarshallerTest.class);
	
	private static JAXBMarshaller marshaller;
	private static ObjectFactory xmlFactory;
	private String xmlSource;

	@BeforeClass
	public static void init() {
		marshaller = new JAXBMarshaller("main.java.bmstu.iu8.analyzer.datasource.xml");
		xmlFactory = new ObjectFactory();
	}
	
	@Test
	public void testMarshalObject() {
		XmlMitreSourceType xmlMitreSourceType = new XmlMitreSourceType();
		xmlMitreSourceType.setName("test mitre source");
		xmlMitreSourceType.setNumber(new BigInteger("1"));
		xmlMitreSourceType.setUrl("url/test/org/mitre/source");
		JAXBElement<XmlMitreSourceType> mitreSource = xmlFactory.createXmlMitreSource(xmlMitreSourceType);
		xmlSource = marshaller.marshal(mitreSource);
		assertNotNull(xmlSource);
		LOGGER.info(xmlSource);
	}

//	@Test
//	public void testMarshalString() {
//		fail("Not yet implemented");
//	}

}
