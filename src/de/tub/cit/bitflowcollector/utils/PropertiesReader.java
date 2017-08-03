package de.tub.cit.bitflowcollector.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	public static String readProperty(String propertyName) throws IOException {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try (InputStream input = classLoader.getResourceAsStream("config.properties")) {
			properties.load(input);
			return properties.getProperty(propertyName);
		}
	}
}
