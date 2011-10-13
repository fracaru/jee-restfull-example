/**
 * 
 */
package org.example.jee.rest.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.example.jee.rest.common.domain.UserExample;
import org.example.jee.rest.common.domain.utils.DomainFactory;
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
@Stateless
public class UserServiceBean implements IUserService {

	@PersistenceContext(unitName = "UserPUnit")
	private EntityManager em;

	private final Logger logger = LoggerFactory
			.getLogger(UserServiceBean.class);

	@Override
	public UserExample createUser(String name, String email) {
		if (name == null || email == null) {
			throw new RuntimeException("Parameters must not be null");
		}
		UserExample user = DomainFactory.createUser();
		user.setId(0);
		user.setName(name);
		user.setEmail(email);
		em.persist(user);
		em.flush();
		return user;
	}

	@Override
	public UserExample createUser(UserExample user) {
		if (user == null) {
			throw new RuntimeException("User must not be null");
		}

		logger.info(user.toString());
		em.persist(user);
		return user;
	}

	@Override
	public void deleteUser(long id) {
		logger.info("Deleting " + id);
		UserExample user = em.find(UserExample.class, id);
		if (user != null) {
			em.remove(user);
			logger.info("Deleted user" + id);
		}

	}

	@Override
	public void updateUser(UserExample user) {
		em.merge(user);
		logger.info("User updated " + user);

	}

	@Override
	public UserExample getUser(long id) {
		return em.find(UserExample.class, id);
	}

	@Override
	public Set<UserExample> listUsers() {
		String query = "Select u from UserExample u";
		Query q = em.createQuery(query);

		@SuppressWarnings("unchecked")
		List<UserExample> retrievedResult = (List<UserExample>) q
				.getResultList();
		Set<UserExample> result = new HashSet<UserExample>(retrievedResult);
		return result;
	}

}
