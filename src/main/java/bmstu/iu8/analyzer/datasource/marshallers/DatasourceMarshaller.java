package main.java.bmstu.iu8.analyzer.datasource.marshallers;

public interface DatasourceMarshaller {
	public String marshal(Object object);
	public Object marshal(String source);

}
