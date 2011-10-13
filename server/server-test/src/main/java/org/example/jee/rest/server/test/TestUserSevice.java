package org.example.jee.rest.server.test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import junit.framework.Assert;

import org.example.jee.rest.common.domain.UserExample;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class TestUserSevice {

	public final static String BASE_URL = "http://localhost:8080/user-example-framework/services/users";

	String user = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><userExample><id>1</id><name>Florin</name><email>florin@akka.eu</email></userExample>";

	@BeforeClass
	public static void setUp() {
		System.out.println("Starting tests ...");
	}

	@AfterClass
	public static void shutDown() {
		System.out.println("End of tests ...");
	}

	@Test
	public void testCreateUserParameters() {
		ClientConfig cc = new DefaultClientConfig();
		cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
		Client c = Client.create(cc);
		WebResource resource = c.resource(BASE_URL).path("test");

		// lets get the XML as a String
		// String text = resource.get(String.class);
		// System.out.println("Text : " + text);

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("name", "Jane");
		queryParams.add("email", "john@doe.com");

		WebResource.Builder builder = resource.queryParams(queryParams).accept(
				MediaType.APPLICATION_XML, MediaType.TEXT_XML);

		ClientResponse response = builder.put(ClientResponse.class);
		int status = response.getStatus();
		String textEntity = response.getEntity(String.class);
		System.out.println("Text : " + textEntity + ", " + status);
		Assert.assertEquals(200, status);
	}

	@Test
	public void testCreateUser() {
		System.out.println("Test Create User");
		ClientConfig cc = new DefaultClientConfig();
		cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
		Client c = Client.create(cc);
		WebResource resource = c.resource(BASE_URL);

		UserExample user = new UserExample();
		user.setName("John");
		user.setEmail("john@doe.com");

		// lets get the XML as a String
		// String text = resource.get(String.class);
		// System.out.println("Text : " + text);

		WebResource.Builder builder = resource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML, MediaType.TEXT_XML);

		ClientResponse response = builder.put(ClientResponse.class, user);
		int status = response.getStatus();
		String textEntity = response.getEntity(String.class);
		System.out.println("Text : " + textEntity + ", " + status);
		Assert.assertEquals(201, status);
	}

	@Test
	public void testListUsers() {
		ClientConfig cc = new DefaultClientConfig();
		cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
		Client c = Client.create(cc);
		WebResource resource = c.resource(BASE_URL);

		WebResource.Builder builder = resource.accept(
				MediaType.APPLICATION_XML, MediaType.TEXT_XML);

		ClientResponse response = builder.get(ClientResponse.class);
		int status = response.getStatus();
		String textEntity = response.getEntity(String.class);
		System.out.println("Text : " + textEntity + ", " + status);
		Assert.assertEquals(200, status);

	}

	@Test
	public void testGetUser() {
		ClientConfig cc = new DefaultClientConfig();
		cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
		Client c = Client.create(cc);
		WebResource resource = c.resource(BASE_URL).path("1");

		// lets get the XML as a String
		// String text = resource.get(String.class);
		// System.out.println("Text : " + text);

		WebResource.Builder builder = resource.accept(
				MediaType.APPLICATION_XML, MediaType.TEXT_XML);

		ClientResponse response = builder.get(ClientResponse.class);
		int status = response.getStatus();
		String textEntity = response.getEntity(String.class);
		System.out.println("Text : " + textEntity + ", " + status);
		Assert.assertEquals(200, status);
	}

	@Test
	public void testDeleteUser() {
		ClientConfig cc = new DefaultClientConfig();
		cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
		Client c = Client.create(cc);
		WebResource resource = c.resource(BASE_URL).path("2");

		WebResource.Builder builder = resource.accept(
				MediaType.APPLICATION_XML, MediaType.TEXT_XML);

		ClientResponse response = builder.delete(ClientResponse.class);
		int status = response.getStatus();
		Assert.assertEquals(204, status);
	}

}
