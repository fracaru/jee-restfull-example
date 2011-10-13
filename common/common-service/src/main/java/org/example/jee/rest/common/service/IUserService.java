/**
 * 
 */
package org.example.jee.rest.common.service;

import java.util.Set;

import org.example.jee.rest.common.domain.UserExample;


/**
 * Services to manage users
 * <P>
 * 
 */
public interface IUserService {

	/**
	 * Create a new user
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            email
	 * @return the created user
	 */
	UserExample createUser(String name, String email);

	/**
	 * Create a new user
	 * 
	 * @param user
	 *            the user
	 * @return the created user
	 */
	UserExample createUser(UserExample user);

	/**
	 * Delete a user
	 * 
	 * @param id
	 *            user id
	 */
	void deleteUser(long id);

	/**
	 * Update a user
	 * 
	 * @param user
	 *            user
	 */
	void updateUser(UserExample user);

	/**
	 * Retrieve user
	 * 
	 * @param id
	 *            user id
	 * @return the retrieved user
	 */
	UserExample getUser(long id);

	/**
	 * Retrieve a set of all users
	 * 
	 * @return set of users
	 */
	Set<UserExample> listUsers();

}
