package main.java.bmstu.iu8.analyzer.datasource.batch.loaders;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.bmstu.iu8.analyzer.datasource.marshallers.JAXBMarshaller;
import main.java.bmstu.iu8.analyzer.datasource.xml.ObjectFactory;
import main.java.bmstu.iu8.analyzer.datasource.xml.XmlMitreSourceType;
import main.java.bmstu.iu8.analyzer.datasource.xml.XmlWeaknessType;

public class CsvToXmlLoader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CsvToXmlLoader.class);
	
	private static final char DELIMETER = ';';
	private static final int HEADER_INDEX = 0;
	private static final String XML_PACKAGE_NAME = "main.java.bmstu.iu8.analyzer.datasource.xml";
	
	public enum Headers {
		WeaknessName, Category,  
		CWENumber, CWEDescription, CWEUrl,
		CAPECNumber, CAPECDescription, CAPECUrl
	}
	
	public static void load(String from, String to) {
		try (Reader reader = new FileReader(from); Writer writer = new FileWriter(to)) {
			List<CSVRecord> records= CSVFormat.EXCEL.withDelimiter(DELIMETER)
					.withHeader(Headers.class).parse(reader).getRecords();
			loadCSVRecords(records,writer);
		} 
		catch (IOException e) {
			logLoaderMessage(e);
		} 
	}
	
	private static void loadCSVRecords(List<CSVRecord> records, Writer writer) {
		for (CSVRecord record : records) {
			if (records.indexOf(record) == HEADER_INDEX) continue;
			XmlMitreSourceType cweType = createCweTypeFromRecord(record);
			XmlMitreSourceType capecType = createCapecTypeFromRecord(record);
			XmlWeaknessType weaknessType = createWeaknessTypeFromRecord(record, cweType, capecType);
			JAXBElement<XmlWeaknessType> xmlWeakness = createXmlWeaknessElement(weaknessType);
			String result = createXmlWeaknessString(xmlWeakness);
			writeXml(writer, result);
		}
	}

	private static XmlWeaknessType createWeaknessTypeFromRecord(CSVRecord record, XmlMitreSourceType cweType, 
			XmlMitreSourceType capecType) {
		String weaknessName = record.get(Headers.WeaknessName);
		String category = record.get(Headers.Category);
		return new XmlWeaknessType(weaknessName, category, cweType, capecType);
	}

	private static XmlMitreSourceType createCapecTypeFromRecord(CSVRecord record) {
		String capecNumber = record.get(Headers.CAPECNumber);
		String capecDescription = record.get(Headers.CAPECDescription);
		String capecUrl = record.get(Headers.CAPECUrl);
		return new XmlMitreSourceType(capecDescription, getNumber(capecNumber), capecUrl);
	}

	private static XmlMitreSourceType createCweTypeFromRecord(CSVRecord record) {
		String cweNumber = record.get(Headers.CWENumber);
		String cweDescription = record.get(Headers.CWEDescription);
		String cweUrl = record.get(Headers.CWEUrl);
		return new XmlMitreSourceType(cweDescription, getNumber(cweNumber), cweUrl);
	}
	
	private static JAXBElement<XmlWeaknessType> createXmlWeaknessElement(XmlWeaknessType weaknessType) {
		ObjectFactory xmlFactory = new ObjectFactory();
		return xmlFactory.createXmlWeakness(weaknessType);
	}

	private static String createXmlWeaknessString(JAXBElement<XmlWeaknessType> xmlWeakness) {
		JAXBMarshaller marshaller = new JAXBMarshaller(XML_PACKAGE_NAME);
		return marshaller.marshal(xmlWeakness);
	}
	
	private static void writeXml(Writer writer, String result) {
		try {
			writer.write(result + "\r\n");
		} 
		catch (Exception e) {
			logWriterMessage(e);
		}
	}

	private static void logWriterMessage(Exception e) {
		LOGGER.error("Cannot write to file!");
		LOGGER.error("Error messsage: " + e.getMessage());
	}
	
	private static void logLoaderMessage(IOException e) {
		LOGGER.error("Cannot load data!");
		LOGGER.error("Error messsage: " + e.getMessage());
	}
	
	private static BigInteger getNumber(String number) {
		return new BigInteger(number);
	}

}
