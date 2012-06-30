package com.globant.persistence;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globant.entities.Language;

@Repository
public class LanguageDAO {
	public static final int RESULTS = 20;

	private EntityManager eM;

	public EntityManager getEntityManager() {
		return eM;
	}

	public void setEntityManager(EntityManager eManager) {
		this.eM = eManager;
	}

	@Autowired
	public LanguageDAO(EntityManagerFactory eMF) {
		setEntityManager(eMF.createEntityManager());
	}
	
	public Language getLanguageByName(String language) {
		Query q = getEntityManager().createQuery("FROM Language l WHERE l.language=:language");
		return (Language) q.setParameter("language", language).getSingleResult();
	}
}
