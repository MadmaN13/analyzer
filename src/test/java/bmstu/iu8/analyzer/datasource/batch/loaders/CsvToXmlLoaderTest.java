package test.java.bmstu.iu8.analyzer.datasource.batch.loaders;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import main.java.bmstu.iu8.analyzer.datasource.batch.loaders.CsvToXmlLoader;

public class CsvToXmlLoaderTest {
	
	private static String from;
	private static String to;
	
	@BeforeClass
	public static void init() {
		to = "src/main/resources/io/loaderResult.txt";
	}
	
	@Test
	public void testNotEmptyValidFormatFileLoad() {
		from = "src/main/resources/text/csv/notEmptyValidFormatFile.csv";
		CsvToXmlLoader.load(from, to);
	}
	
	@Test
	public void testNotEmptyWrongDataFormatFileLoad() {
		from = "src/main/resources/text/csv/notEmptyWrongDataFormatFile.csv";
	}	
	
	@Test
	public void testNotEmptyWrongHeaderFormatFileLoad() {
		from = "src/main/resources/text/csv/notEmptyWrongHeaderFormatFile.csv";
	}
	
	@Test
	public void testEmptyFileLoad() {
		from = "src/main/resources/text/csv/emptyFile.csv";
	}

}
