package br.com.timbrasil.operations.daos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.timbrasil.operations.models.User;

@RequestScoped
public class UserDao {
	
	private final EntityManager manager;

	@Inject
	public UserDao(EntityManager manager) {
		this.manager = manager;
	}
	
	/**
	 * CDI ONLY
	 */
	@Deprecated
	public UserDao(){
		this(null);
	}
	
	public void save(User user){
		manager.persist(user);
	}
	
	public User find(long id){
		return manager.find(User.class, id);
	}

	public User find(User user) {
		String jpql = "select u from User u where email = :pEmail and password = :pPassword";
		TypedQuery<User> typedQuery = manager.createQuery(jpql, User.class);
		
		typedQuery.setParameter("pEmail", user.getEmail());
		typedQuery.setParameter("pPassword", user.getPassword());
		try{
			return typedQuery.getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}

	public User update(User user) {
		return manager.merge(user);
	}
	

}
