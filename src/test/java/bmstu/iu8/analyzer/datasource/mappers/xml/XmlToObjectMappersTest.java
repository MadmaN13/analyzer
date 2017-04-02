package test.java.bmstu.iu8.analyzer.datasource.mappers.xml;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.xml.bind.JAXBElement;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.bmstu.iu8.analyzer.datasource.mappers.AlertMapper;
import main.java.bmstu.iu8.analyzer.datasource.mappers.CAPECMapper;
import main.java.bmstu.iu8.analyzer.datasource.mappers.CWEMapper;
import main.java.bmstu.iu8.analyzer.datasource.mappers.WeaknessMapper;
import main.java.bmstu.iu8.analyzer.datasource.marshallers.JAXBMarshaller;
import main.java.bmstu.iu8.analyzer.datasource.xml.XmlAlertType;
import main.java.bmstu.iu8.analyzer.datasource.xml.XmlMitreSourceType;
import main.java.bmstu.iu8.analyzer.datasource.xml.XmlWeaknessType;
import main.java.bmstu.iu8.analyzer.model.Alert;
import main.java.bmstu.iu8.analyzer.model.Weakness;
import main.java.bmstu.iu8.analyzer.model.mitre.CAPEC;
import main.java.bmstu.iu8.analyzer.model.mitre.CWE;
import main.java.bmstu.iu8.analyzer.model.mitre.MitreSource;

public class XmlToObjectMappersTest {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(XmlToObjectMappersTest.class);
	
	private static AlertMapper alertMapper;
	private static CAPECMapper capecMapper;
	private static CWEMapper cweMapper;
	private static WeaknessMapper weaknessMapper;
	
	private static JAXBMarshaller marshaller;
	private static String source;
	private static Object objectSource;
	
	
	@BeforeClass
	public static void init() {
		alertMapper = new AlertMapper();
		capecMapper = new CAPECMapper();
		cweMapper = new CWEMapper();
		weaknessMapper = new WeaknessMapper();
		
		marshaller = new JAXBMarshaller("main.java.bmstu.iu8.analyzer.datasource.xml");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCWEMapper() {
		source = readXml("src/main/resources/text/testMitreSource.txt");
		objectSource = marshaller.marshal(source);
		
		assertNotNull(objectSource);
		assertTrue(objectSource instanceof JAXBElement<?>);
		
		CWE cwe = cweMapper.map((JAXBElement<XmlMitreSourceType>) objectSource);
		LOGGER.info("CWE mapped: " + "\r\n" + cwe.toString());
		assertNotNull(cwe);
		assertEquals("test mitre source", cwe.getName());
		assertEquals(1, cwe.getNumber());
		assertEquals("url/test/org/mitre/source", cwe.getDescriptionUrl());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCAPECMapper() {
		source = readXml("src/main/resources/text/testMitreSource.txt");
		objectSource = marshaller.marshal(source);
		
		assertNotNull(objectSource);
		assertTrue(objectSource instanceof JAXBElement<?>);
		
		CAPEC capec = capecMapper.map((JAXBElement<XmlMitreSourceType>) objectSource);
		LOGGER.info("CAPEC mapped: " + "\r\n" + capec.toString());
		assertNotNull(capec);
		assertEquals("test mitre source", capec.getName());
		assertEquals(1, capec.getNumber());
		assertEquals("url/test/org/mitre/source", capec.getDescriptionUrl());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testWeaknessMapper() {
		source = readXml("src/main/resources/text/testWeakness.txt");
		objectSource = marshaller.marshal(source);
		
		assertNotNull(objectSource);
		assertTrue(objectSource instanceof JAXBElement<?>);
		
		Weakness weakness = weaknessMapper.map((JAXBElement<XmlWeaknessType>) objectSource);
		LOGGER.info("Weakness mapped: " + "\r\n" + weakness.toString());
		assertNotNull(weakness);
		assertEquals("test category", weakness.getCategory());
		assertEquals("test weakness", weakness.getName());
		assertNull(weakness.getPattern());
		
		CAPEC testCapec = new CAPEC("test mitre source", 1, "url/test/org/mitre/source");
		CWE testCwe = new CWE("test mitre source", 1, "url/test/org/mitre/source");
		
		assertMitreSourceEquals(testCapec, weakness.getCapec());
		assertMitreSourceEquals(testCwe, weakness.getCwe());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAlertMapper() {
		source = readXml("src/main/resources/text/testAlert.txt");
		objectSource = marshaller.marshal(source);
		
		assertNotNull(objectSource);
		assertTrue(objectSource instanceof JAXBElement<?>);
		
		Alert alert = alertMapper.map((JAXBElement<XmlAlertType>) objectSource);
		LOGGER.info("Alert mapped: " + "\r\n" + alert.toString());
		assertNotNull(alert);
		
		assertEquals(1, alert.getId());
		assertEquals("test alert", alert.getOccurenceDescription());
		assertNotNull(alert.getTimestamp());
		assertEquals(2, alert.getWeaknessId());
		
		assertWeaknessEquals(new Weakness(), alert.getWeakness());
	}

	private void assertWeaknessEquals(Weakness expected, Weakness actual) {
		assertMitreSourceEquals(expected.getCapec(), actual.getCapec());
		assertEquals(expected.getCategory(), actual.getCategory());
		assertMitreSourceEquals(expected.getCwe(), actual.getCwe());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getPattern(), actual.getPattern());
	}
	
	private void assertMitreSourceEquals(MitreSource expected, MitreSource actual) {
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDescriptionUrl(), actual.getDescriptionUrl());
		assertTrue(expected.getNumber() == actual.getNumber());
	}

	private String readXml(String path) {
		String result = "";
		try (Scanner scanner = new Scanner(new File(path))) {
			if (scanner.hasNextLine()) {
				result = scanner.nextLine();
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}
