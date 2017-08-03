package de.tub.cit.bitflowcollector.resources;

import java.io.IOException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.tub.cit.bitflowcollector.service.BitflowCollectorService;
import de.tub.cit.bitflowcollector.utils.PropertiesReader;

import org.apache.log4j.Logger;

@Path("/")
public class BitflowCollectorResource {

	private static Logger logger = Logger.getLogger(BitflowCollectorResource.class);
	
	private BitflowCollectorService service = new BitflowCollectorService();
	private String path = null; 
	
	public BitflowCollectorResource() {
		try {
			this.path = PropertiesReader.readProperty("PATH").trim();			
		} catch(IOException ex) {
			logger.error(ex.getMessage());
		}
	}
	
	@GET
	@Path("/size")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSize() {
		logger.info("Getting size of the file.");
		return service.getSize(path) + " byte(s)";
	}
	
	@GET
	@Path("/lines")
	@Produces(MediaType.TEXT_PLAIN)
	public long getLinesNumber() {
		logger.info("Getting line numbers of the file.");
		return service.getLinesNumber(path);
	}
	
	@GET
	@Path("/head")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFirstLines(@DefaultValue("1") @QueryParam("num") long numberOfLines) {	
		logger.info("Getting first " + numberOfLines + " line(s) from the file.");
		return service.getFirstLines(path, numberOfLines);
	}
	
	@GET
	@Path("/tail")
	@Produces(MediaType.TEXT_PLAIN)
	public String getLastLines(@DefaultValue("1") @QueryParam("num") long numberOfLines) {	
		logger.info("Getting last " + numberOfLines + " line(s) from the file.");
		return service.getLastLines(path, numberOfLines);
	}
}
