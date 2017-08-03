package de.tub.cit.bitflowcollector.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import de.tub.cit.bitflowcollector.readers.BitflowCollectorCSVReader;

public class BitflowCollectorService {

	private static Logger logger = Logger.getLogger(BitflowCollectorService.class);
	
	public BitflowCollectorService() {
	}
	
	public long getSize(String path) {
		logger.debug("Inside BitflowCollectorService.getSize() ");			
		BitflowCollectorCSVReader reader = new BitflowCollectorCSVReader(path); //to get it using factory method
		try {
			logger.debug("Inside BitflowCollectorService.getSize(). Calling: BitflowCollectorCSVReader.getCSVFileSize().");
			return reader.getCSVFileSize();
		} catch (NullPointerException | FileNotFoundException e) {
			logger.error(e.getMessage());			
			return -1;
		}
	}
	
	public long getLinesNumber(String path) {
		logger.debug("Inside BitflowCollectorService.getLinesNumber() ");
		BitflowCollectorCSVReader reader = new BitflowCollectorCSVReader(path); //to get it using factory method
		try {
			logger.debug("Inside BitflowCollectorService.getSize(). Calling: BitflowCollectorCSVReader.getCSVRecordCount().");
			return reader.getCSVRecordCount();
		} catch (NullPointerException | IOException e) {
			logger.error(e.getMessage());			
			return -1;
		}
	}

	public String getFirstLines(String path, long numberOfLines) {
		logger.debug("Inside BitflowCollectorService.getFirstLines() ");
		BitflowCollectorCSVReader reader = new BitflowCollectorCSVReader(path); //to get it using factory method
		try {
			logger.debug("Inside BitflowCollectorService.getSize(). Calling: BitflowCollectorCSVReader.getFirstLines().");
			return reader.getFirstXLines(numberOfLines);
		} catch (NullPointerException | IOException e) {
			logger.error(e.getMessage());			
			return "";
		}
	}

	public String getLastLines(String path, long numberOfLines) {
		logger.debug("Inside BitflowCollectorService.getLastLines() ");
		BitflowCollectorCSVReader reader = new BitflowCollectorCSVReader(path); //to get it using factory method
		try {
			logger.debug("Inside BitflowCollectorService.getSize(). Calling: BitflowCollectorCSVReader.getLastLines().");
			return reader.getLastXLines(numberOfLines);
		} catch (NullPointerException | IOException e) {
			logger.error(e.getMessage());			
			return "";
		}
	}
}
