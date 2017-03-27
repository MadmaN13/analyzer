package main.java.bmstu.iu8.analyzer.datasource.marshallers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
	public String marshall(Object object) {
		String result = "";
		if (marshaller != null) {
			result = marshaller.ma
		}
		return result;
	}

	@Override
	public Object marshall(String source) {
		// TODO Auto-generated method stub
		return null;
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
