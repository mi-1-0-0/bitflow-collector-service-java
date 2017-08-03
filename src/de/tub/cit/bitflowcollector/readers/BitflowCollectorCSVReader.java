package de.tub.cit.bitflowcollector.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;

import com.opencsv.CSVReader;

public class BitflowCollectorCSVReader {
	private static Logger logger = Logger.getLogger(BitflowCollectorCSVReader.class);

	private String path;
	
	public BitflowCollectorCSVReader() {
		this.path = null;
	}
	
	public BitflowCollectorCSVReader(String path) {
		this.path = path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public long getCSVFileSize() throws NullPointerException, FileNotFoundException {
		File file = new File(this.path);
		return file.length();
	}
	
	public String getFirstXLines(long numberOfLines) throws FileNotFoundException, IOException {
		StringBuilder builder = new StringBuilder();
		if  (numberOfLines < 0) {
			logger.warn("Number of lines are not valid.");
			return builder.toString();
		}		
		numberOfLines = numberOfLines <= this.getCSVRecordCount() ? numberOfLines : this.getCSVRecordCount();
		logger.info("Reading " + numberOfLines + " number of lines from the beginning of the file.");
		try (CSVReader csvReader = new CSVReader(getFileReader(path), ',')) {
			long lineNumber = 1;
			while (lineNumber <= numberOfLines) {
				String[] nextLine = csvReader.readNext();
				builder.append(String.join(",", nextLine)).append(System.lineSeparator());
				lineNumber++;
			}
		}
		return builder.toString();
	}
	
	public String getLastXLines(long numberOfLines) throws FileNotFoundException, IOException {		
		StringBuilder builder = new StringBuilder();
		if  (numberOfLines < 0) {
			logger.warn("Number of lines are not valid.");
			return builder.toString();
		}
		numberOfLines = numberOfLines <= this.getCSVRecordCount() ? numberOfLines : this.getCSVRecordCount();
		logger.info("Reading " + numberOfLines + " number of lines from the end of the file.");
		long totalCount = getCSVRecordCount();
		try (CSVReader csvReader = new CSVReader(getFileReader(path), ',')) {
			long lineNumber = 1, linesToSkip = totalCount - numberOfLines;
			while (lineNumber <= linesToSkip) {
				csvReader.readNext();
				lineNumber++;
			}
			while (lineNumber <= totalCount) {
				String[] nextLine = csvReader.readNext();
				builder.append(String.join(",", nextLine)).append(System.lineSeparator());
				lineNumber++;
			}
		}
		return builder.toString();
	}
	
	public long getCSVRecordCount() throws IOException {
		long count = 0;
		//donot use readAll().length. it will consume more memory
		try (CSVReader csvReader = new CSVReader(getFileReader(path), ',')) {
			while (csvReader.readNext() != null) {
				++count;
			}
		}
		return count;
	}

	private Reader getFileReader(String path) throws FileNotFoundException {
		return new FileReader(new File (path));
	}
	
	@Override
	public String toString() {
		return "{'path' : '" + this.path  + "'}";
	}
}
