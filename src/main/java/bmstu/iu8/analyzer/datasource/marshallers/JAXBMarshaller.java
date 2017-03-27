package main.java.bmstu.iu8.analyzer.datasource.marshallers;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JAXBMarshaller implements DatasourceMarshaller {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(JAXBMarshaller.class);
	
	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public JAXBMarshaller(String packageName) {
		super();
		if (packageName!=null && !packageName.isEmpty()) {
			context = initializeContext(packageName);
			marshaller = initializeMarshaller();
			unmarshaller = initializeUnmarshaller();
		}
	}
	
	@Override
	public String marshal(Object object) {
		String result = "";
		if (marshaller != null) {
			try (StringWriter writer = new StringWriter()) {
				try {
					marshaller.marshal(object, writer);
					result = writer.toString();
				} catch (JAXBException e) {
					LOGGER.error("Cannot marshall object: " + object.toString());
					LOGGER.error("Error message: " + e.getMessage());
				}
			}
			catch (IOException e) {
				LOGGER.error("Cannot close stringWriter!");
				LOGGER.error("Error message: " + e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Object marshal(String source) {
		Object result = null;
		if (source !=null && !source.isEmpty()) {
			try {
				result = unmarshaller.unmarshal(new StreamSource(new StringReader(source)));
			} catch (JAXBException e) {
				LOGGER.error("Cannot unmarshall from text: " + source);
				LOGGER.error("Error message: " + e.getMessage());
			}
		}
		return result;
	}

	private JAXBContext initializeContext(String packageName) {
		JAXBContext result = null;
		try {
			result =  JAXBContext.newInstance(packageName);
		} catch (JAXBException e) {
			LOGGER.error("Cannot initialize JAXB context for package with name: " + packageName);
			LOGGER.error("Error massage: " + e.getMessage());
		}
		return result;
	}
	
	private Unmarshaller initializeUnmarshaller() {
		Unmarshaller result = null;
		try {
			result = context.createUnmarshaller();
		} catch (JAXBException e) {
			LOGGER.error("Cannot initialize JAXB unmarshaller!");
			LOGGER.error("Error massage: " + e.getMessage());
		}
		return result;
	}

	private Marshaller initializeMarshaller() {
		Marshaller result = null;
		try {
			result = context.createMarshaller();
		} catch (JAXBException e) {
			LOGGER.error("Cannot initialize JAXB marshaller!");
			LOGGER.error("Error massage: " + e.getMessage());
		}
		return result;
	}

}
