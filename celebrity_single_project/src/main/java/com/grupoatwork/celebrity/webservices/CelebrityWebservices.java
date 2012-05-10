package com.grupoatwork.celebrity.webservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupoatwork.celebrity.dao.CelebrityDao;
import com.grupoatwork.celebrity.entities.Celebrity;

@Component
@Path("/celebrity")
public class CelebrityWebservices {
	@Inject
	private CelebrityDao celebrityDao;
	
	@GET
	@Path("/")
	@Produces({"application/json", "application/xml"})
	public List<Celebrity> getMessage() {
		return celebrityDao.getCelebrities();
	}
	
	@GET
	@Path("/plain")
	@Produces("text/plain")
	public String getClichedMessage() {
		return "Hello Android";
	}
//	
//	@GET
//	@Path("/xml")
//	@Produces("text/xml")
//	public String getXMLMessage() {
//		return "<?xml version=\"1.0\"?>" + "<hello> //Hello Android" + "</hello>";
//	}
//	
//	@GET
//	@Path("/html")
//	@Produces("text/html")
//	public String getHTMLMessage() {
//		return "<html> " + "<title>" + "Hello Android" + "</title>" + "<body><h1>" + "Hello Android" + "</body></h1>" + "</html> ";
//	}
}
