package logic.interfaces; 

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import model.Powerpoint;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;

public interface PPTFinder {

	String POWERPOINT_PROPERTIES_KEY = "powerpoint.directory";
	
	public Powerpoint searchFileByName(String name) throws Exception;
	
	public List<Powerpoint> searchFileByWords(String Words) throws FileNotFoundException, InvalidFormatException, IOException, XmlException, OpenXML4JException, InterruptedException, ExecutionException;
	
	public List<Powerpoint> getAll() throws XmlException, OpenXML4JException, IOException;
	
	public List<String> getAllFileNames() throws XmlException, OpenXML4JException, IOException;
	
}
