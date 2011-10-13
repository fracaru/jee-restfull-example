package org.example.jee.rest.service.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Activate REST services
 * 
 * @author racaru
 * 
 */
@ApplicationPath("services")
public class RestUserApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		// Get a set of root resource and provider classes.
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(UserServiceRest.class);
		return s;
	}

}