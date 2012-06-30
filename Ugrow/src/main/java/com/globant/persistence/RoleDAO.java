package com.globant.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globant.entities.Role;

@Repository
public class RoleDAO {

	private EntityManager eM;

	public EntityManager getEntityManager() {
		return eM;
	}

	public void setEntityManager(EntityManager eManager) {
		this.eM = eManager;
	}

	@Autowired
	public RoleDAO(EntityManagerFactory eMF) {
		setEntityManager(eMF.createEntityManager());
	}

	public Role getRoleByName(String name) {
		Query q =getEntityManager().createQuery("FROM Role r WHERE r.name=:name");
		return (Role) q.setParameter("name",name).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getAllRoles(){
		return getEntityManager().createQuery("FROM Role").getResultList();
	}
}