package test.java.bmstu.iu8.analyzer.datasource.marshallers;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.bmstu.iu8.analyzer.datasource.marshallers.JAXBMarshaller;
import main.java.bmstu.iu8.analyzer.datasource.xml.ObjectFactory;
import main.java.bmstu.iu8.analyzer.datasource.xml.XmlAlertType;
import main.java.bmstu.iu8.analyzer.datasource.xml.XmlMitreSourceType;
import main.java.bmstu.iu8.analyzer.datasource.xml.XmlWeaknessType;

	
public class JAXBMarshallerTest {
	private final static Logger LOGGER = LoggerFactory.getLogger(JAXBMarshallerTest.class);
	
	private static JAXBMarshaller marshaller;
	private static ObjectFactory xmlFactory;
	private String xmlSource;
	private Object objectSource;

	@BeforeClass
	public static void init() {
		marshaller = new JAXBMarshaller("main.java.bmstu.iu8.analyzer.datasource.xml");
		xmlFactory = new ObjectFactory();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMitreSourceMarshalling() {
		createXmlMitreSource();
		LOGGER.info("Created xml: " + xmlSource);
		assertNotNull(xmlSource);
		
		createObject();
		assertNotNull(objectSource);
		assertTrue(objectSource instanceof JAXBElement<?>);
		assertEquals("test mitre source", ((JAXBElement<XmlMitreSourceType>) objectSource).getValue().getName());
		assertEquals(new BigInteger("1"), ((JAXBElement<XmlMitreSourceType>) objectSource).getValue().getNumber());
		assertEquals("url/test/org/mitre/source", ((JAXBElement<XmlMitreSourceType>) objectSource).getValue().getUrl());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testWeaknessMarshalling() {
		createXmlWeakness();
		LOGGER.info("Created xml: " + xmlSource);
		assertNotNull(xmlSource);
		
		createObject();
		assertNotNull(objectSource);
		assertTrue(objectSource instanceof JAXBElement<?>);
		assertEquals("test weakness", ((JAXBElement<XmlWeaknessType>) objectSource).getValue().getDescription());
		assertEquals("test category", ((JAXBElement<XmlWeaknessType>) objectSource).getValue().getCategory());
		assertEquals("test mitre source", ((JAXBElement<XmlWeaknessType>) objectSource).getValue().getCwe().getName());
		assertEquals("test mitre source", ((JAXBElement<XmlWeaknessType>) objectSource).getValue().getCapec().getName());
	}

	
	@SuppressWarnings("unchecked")
	@Test
	public void testAlertMarshalling() {
		createXmlAlert();
		LOGGER.info("Created xml: " + xmlSource);
		assertNotNull(xmlSource);
		
		createObject();
		assertNotNull(objectSource);
		assertTrue(objectSource instanceof JAXBElement<?>);
		assertEquals(new BigInteger("1"), ((JAXBElement<XmlAlertType>) objectSource).getValue().getId());
		assertEquals("test alert", ((JAXBElement<XmlAlertType>) objectSource).getValue().getDescription());
		assertEquals(new BigInteger("2"), ((JAXBElement<XmlAlertType>) objectSource).getValue().getWeaknessId());
		assertNotNull(((JAXBElement<XmlAlertType>) objectSource).getValue().getTimestamp());
	}

	
	private void createXmlAlert() {
		JAXBElement<XmlAlertType> xmlAlert = xmlFactory.createXmlAlert(createXmlAlertType());
		xmlSource = marshaller.marshal(xmlAlert);
	}
	
	private XmlAlertType createXmlAlertType() {
		BigInteger id = new BigInteger("1");
		XMLGregorianCalendar timestamp = createXmlGregorianCalendar(new Date());
		String description = "test alert";
		BigInteger weaknessId = new BigInteger("2");
		return xmlFactory.createXmlAlertType(id, timestamp, description, weaknessId);
	}

	private void createXmlWeakness() {
		JAXBElement<XmlWeaknessType> xmlWeakness = xmlFactory.createXmlWeakness(createXmlWeaknessType());
		xmlSource = marshaller.marshal(xmlWeakness);
	}
	
	private XmlWeaknessType createXmlWeaknessType() {
		String description = "test weakness";
		String category = "test category";
		return xmlFactory.createXmlWeaknessType(description, category, 
				createXmlMitreSourceType(), createXmlMitreSourceType());
	}

	private void createXmlMitreSource() {
		JAXBElement<XmlMitreSourceType> mitreSource = xmlFactory.createXmlMitreSource(createXmlMitreSourceType());
		xmlSource = marshaller.marshal(mitreSource);
	}
	
	private XmlMitreSourceType createXmlMitreSourceType() {
		String name = "test mitre source";
		BigInteger number = new BigInteger("1");
		String url = "url/test/org/mitre/source";
		return xmlFactory.createXmlMitreSourceType(name, number, url);
	}
	
	private void createObject() {
		objectSource =  marshaller.marshal(xmlSource);
	}
	
	private XMLGregorianCalendar createXmlGregorianCalendar(Date date) {
		XMLGregorianCalendar result = null;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		try {
			result = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		}
		catch (DatatypeConfigurationException e) {
			LOGGER.error("Cannot instiniate XmlGregorianCalendar!");
			LOGGER.error("Error message: " + e.getMessage());
		}
		return result;
	}
}
