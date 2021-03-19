/**
 * 
 */
package com.revature.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.model.Role;

import com.revature.util.HibernateSessionFactory;

/**
 * @author Jinwei Xiong
 *
 */
public class RoleDAOImpl implements RoleDAO{
	private Session currentSession;
	@Override
	public Role findById(int roleId) {
		// TODO Auto-generated method stub
		Role role = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        
	        //empl_role
	        String hql = "Select * From project2.app_role as role where role.roleId=:roleId";
			Query<Role> query = currentSession.createNativeQuery(hql,Role.class);
			query.setParameter("roleId", roleId);
			role = query.uniqueResult();
	        currentSession.getTransaction().commit();			
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				currentSession.getTransaction().rollback();				
			}			
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return role;
	}
}
