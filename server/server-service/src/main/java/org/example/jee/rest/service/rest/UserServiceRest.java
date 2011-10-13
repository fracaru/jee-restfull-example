/**
 * 
 */
package org.example.jee.rest.service.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.example.jee.rest.common.domain.UserExample;
import org.example.jee.rest.common.domain.utils.DomainFactory;
import org.example.jee.rest.common.domain.wrapper.UserWrapper;
import org.example.jee.rest.common.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Short Description goes here.
 * <P>
 * Explanation goes here.
 * <P>
 * 
 */
@ManagedBean
@Path("users")
@Consumes(MediaType.APPLICATION_XML)
public class UserServiceRest {

	@Inject
	IUserService psb;

	/** the logger */
	private final Logger logger = LoggerFactory
			.getLogger(UserServiceRest.class);

	@POST
	@Path("/")
	public void updateUser(JAXBElement<UserExample> jaxbPart) {
		UserExample user = jaxbPart.getValue();
		psb.updateUser(user);
	}

	@PUT
	@Path("/test")
	// @Produces(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_XML)
	public UserExample createUser(@QueryParam("name") String name,
			@QueryParam("email") String email) {
		if (name == null || email == null) {
			Response response = Response.status(Response.Status.BAD_REQUEST)
					.entity("Name and Email parameters must not be null")
					.build();

			throw new WebApplicationException(response);
		}

		System.out.println("Name: " + name + ", email: " + email);
		UserExample p = DomainFactory.createUser();
		p.setId(0);
		p.setName(name);
		p.setEmail(email);
		return psb.createUser(p);
	}

	@PUT
	@Path("/")
	// @Produces(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response createUser(JAXBElement<UserExample> jaxbPart) {
		if (jaxbPart == null) {
			Response response = Response.status(Response.Status.BAD_REQUEST)
					.entity("User must not be null").build();
			throw new WebApplicationException(response);
		}

		UserExample user = jaxbPart.getValue();
		user = psb.createUser(user);
		return Response.status(Status.CREATED).entity(user).build();
	}

	@DELETE
	@Path("/{id}/")
	public void deleteUser(@PathParam("id") long id) {
		logger.info("Deleting user " + id);
		psb.deleteUser(id);
	}

	@POST
	@Path("/{id}/")
	public void updateUser(@PathParam("id") long userId,
			@QueryParam("name") String name, @QueryParam("email") String email) {
		System.out.println("Updating user " + userId);
		UserExample user = DomainFactory.createUser();
		user.setId(userId);
		user.setName(name);
		user.setEmail(email);
		psb.updateUser(user);
	}

	@GET
	@Path("/{id}/")
	@Produces({ MediaType.TEXT_XML, MediaType.TEXT_PLAIN,
			MediaType.APPLICATION_JSON })
	public Response getUser(@PathParam("id") long id) {
		UserExample participant = psb.getUser(id);
		return Response.ok().entity(participant).build();
	}

	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response listUsers() throws JAXBException {
		Set<UserExample> result = psb.listUsers();
		UserWrapper pw = new UserWrapper();
		List<UserExample> list = new ArrayList<UserExample>();
		list.addAll(result);
		pw.setUsers(list);
		return Response.ok().entity(pw).build();
	}

}
