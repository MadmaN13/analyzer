package main.java.bmstu.iu8.analyzer.datasource.marshallers;

public interface DatasourceMarshaller {
	public String marshall(Object object);
	public Object marshall(String source);

}
