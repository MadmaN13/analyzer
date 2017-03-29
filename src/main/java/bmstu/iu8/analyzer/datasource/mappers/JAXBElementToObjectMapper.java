package main.java.bmstu.iu8.analyzer.datasource.mappers;

import javax.xml.bind.JAXBElement;

public interface JAXBElementToObjectMapper<I,O> {
	public O map(JAXBElement<I> element);
}
