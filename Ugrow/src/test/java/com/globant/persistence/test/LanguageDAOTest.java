package com.globant.persistence.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.junit.Test;
import com.globant.entities.Language;
import com.globant.persistence.LanguageDAO;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class LanguageDAOTest {
	@Test
	public void languageDAOTest(){
		Language language=new Language(1,"Spanish");
		
		Query query=createMock(Query.class);
		expect(query.setParameter("language","Spanish")).andReturn(query).times(1);
		expect(query.getSingleResult()).andReturn((Object)language).times(1);
		
		EntityManager entityManager=createMock(EntityManager.class);
		expect(entityManager.createQuery("FROM Language l WHERE l.language=:language")).andReturn(query).times(1);
		
		EntityManagerFactory entityManagerFactory=createMock(EntityManagerFactory.class);
		expect(entityManagerFactory.createEntityManager()).andReturn(entityManager).times(1);
		
		replay(query,entityManager,entityManagerFactory);
		
		LanguageDAO languageDAO=new LanguageDAO(entityManagerFactory);
		
		assertEquals(language,languageDAO.getLanguageByName("Spanish"));
		
		verify(query,entityManager,entityManagerFactory);
	}
}