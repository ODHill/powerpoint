package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import logic.interfaces.PPTFinder;

public class Configuration {

	private static Configuration instance;
	
	private Properties properties = null;
	
	private Configuration() {
		
	}
	
	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}
	
	private Properties getProperties(){
		properties = new Properties();
		try {
			
			properties.load(new FileInputStream(Utils.PROPERTIES_NAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public String getValue(String value) {
		return getProperties().getProperty(value);
	}
	
	public void  setDirectory(String directory){
		properties.setProperty(PPTFinder.POWERPOINT_PROPERTIES_KEY , directory);
		try {
			properties.store(new FileOutputStream(Utils.PROPERTIES_NAME), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
