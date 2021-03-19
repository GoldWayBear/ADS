/**
 * 
 */
package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.model.User;
import com.revature.util.HibernateSessionFactory;

/**
 * @author Jinwei Xiong
 *
 */
@Repository(value ="userDAO")
public class UserDAOImpl implements UserDAO {
	private static final Logger LOGGER = LogManager.getFormatterLogger(UserDAOImpl.class);
	private Session currentSession;

	@Override
	public List<User> findAllUsers() {
		List<User> userList = new ArrayList<User>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        
	        userList = currentSession.createQuery("From User").getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				currentSession.getTransaction().rollback();				
			}				
			LOGGER.error("Error at finding all Users.",e);
			e.printStackTrace();
			userList = null;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return userList;
	}

	@Override
	public List<User> findUsersByUsername(String username) {
		List<User> userList = new ArrayList<User>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
	        String searchCriteria = "username";
	        String hql = "From User as user where user."+searchCriteria+" like :searchField";
			Query<User> query = currentSession.createQuery(hql);
			query.setParameter("searchField", "%"+username+"%");
	        userList = query.getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding users by user name.",e);
			e.printStackTrace();
			userList = null;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return userList;
	}

	@Override
	public User findUserById(int userId) {
		User user = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
			user = currentSession.get(User.class, userId);
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding user by user ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return user;
	}

	@Override
	public User findUserByUsername(String username) {
		User user = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
	        String hql = "Select * From project1.employee as empl where empl.username=:username";
			Query<User> query = currentSession.createNativeQuery(hql,User.class);
			query.setParameter("username", username);
			user = (User)(query.uniqueResult());
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding employee by user name.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
	        String hql = "Select * From project1.employee as empl where empl.email=:email";
			Query<User> query = currentSession.createNativeQuery(hql,User.class);
			query.setParameter("email", email);
			user = (User)query.uniqueResult();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding employee by Email.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return user;
	}

	@Override
	public int addUser(User user) {
		int id = 0;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        currentSession.save(user);
	        currentSession.getTransaction().commit();
			id = user.getUserId(); 
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at adding an User, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
		}finally {
				currentSession.close();			
		}
		return id;
	}

	@Override
	public boolean updateUser(User user) {
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        currentSession.update(user);
	        currentSession.getTransaction().commit();
			return true;
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at updating an user, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

}
