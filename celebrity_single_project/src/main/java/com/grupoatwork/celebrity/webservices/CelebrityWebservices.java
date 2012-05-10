package com.grupoatwork.celebrity.webservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.grupoatwork.celebrity.dao.CelebrityDao;
import com.grupoatwork.celebrity.entities.Celebrity;
import com.grupoatwork.celebrity.webservices.exceptions.ImpossibleToAddException;
import com.grupoatwork.celebrity.webservices.exceptions.ImpossibleToUpdateException;

@Component
@Path("/celebrity")
public class CelebrityWebservices {
	private CelebrityDao celebrityDao;
	
	@GET
	@Path("/")
	@Produces({"application/json"})
	public List<Celebrity> getCelebrities() {
		return celebrityDao.getCelebrities();
	}
	
	@GET
	@Path("/{id}")
	@Produces({"application/json"})
	public Celebrity getCelebrity(@PathParam("id") long id) {
		return celebrityDao.read(id);
	}
	
	@GET
	@Path("/findFirstName/{firstName}")
	@Produces({"application/json"})
	public List<Celebrity> getCelebrityByFirstName(@PathParam("firstName") String firstName) {
		return celebrityDao.getCelebritiesByFirstName(firstName);
	}
	
	@GET
	@Path("/findLastName/{lastName}")
	@Produces({"application/json"})
	public List<Celebrity> getCelebrityByLastName(@PathParam("lastName") String lastName) {
		return celebrityDao.getCelebritiesByLastName(lastName);
	}
	
	@PUT
	@Path("/")
	@Consumes("application/json")
	@Produces({"application/json"})
	public Celebrity createCelebrity(Celebrity celebrity) {
		if(celebrity.getId() != null) {
			celebrity.setId(null);
			//Log a warning that the person might have meant to update instead of creating a new record
		}
		return celebrityDao.save(celebrity);
	}
	
	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces({"application/json"})
	public Celebrity updateCelebrity(Celebrity celebrity) {
		Celebrity celebrityVer = celebrityDao.read(celebrity.getId());
		if(celebrityVer == null) {
			throw new ImpossibleToUpdateException("Impossible to add Celebrity to database since a record with that id doesnt exists. Do you mean to use PUT to create a new record?");
		}
		return celebrityDao.save(celebrity);
	}
	
	@DELETE
	@Path("/")
	@Consumes("application/json")
	@Produces({"application/json"})
	public void deleteCelebrity(Celebrity celebrity) {
		celebrityDao.delete(celebrity);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces({"application/json"})
	public void deleteCelebrity(@PathParam("id") long id) {
		celebrityDao.delete(id);
	}
	
	public void setCelebrityDaoProp(CelebrityDao celebrityDao) {
		this.celebrityDao = celebrityDao;
	}
	
	public CelebrityDao getCelebrityDaoProp() {
		return this.celebrityDao;
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
