package com.globant.persistence.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.junit.Test;
import com.globant.entities.Language;
import com.globant.entities.Role;
import com.globant.entities.User;
import com.globant.persistence.UserDAO;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class UserDAOTest {
	@Test
	public void userDAOTest(){
		Role role=new Role(2,"user");
		Set<Language> languages=new HashSet<Language>();
		languages.add(new Language(1,"Spanish"));
		languages.add(new Language(2,"English"));
		User user=new User(Integer.valueOf(1),"John Smith","Globant","Java Developer","john.smith@globant.com",role,"none",languages,"john.smith","1234",Boolean.TRUE);
		
		EntityTransaction entityTransaction=createMock(EntityTransaction.class);
		entityTransaction.begin();
		expectLastCall().times(3);
		entityTransaction.commit();
		expectLastCall().times(3);
		
		Query querya=createMock(Query.class);
		expect(querya.setParameter("username","john.smith")).andReturn(querya).times(1);
		expect(querya.getSingleResult()).andReturn((Object)user).times(1);
		expect(querya.setFirstResult(0)).andReturn(querya).times(1);
		expect(querya.setMaxResults(UserDAO.RESULTS)).andReturn(querya).times(1);
		expect(querya.setParameter("value","%john%")).andReturn(querya).times(1);
		expect(querya.getResultList()).andReturn(Arrays.asList(user)).times(1);
		
		Query queryb=createMock(Query.class);
		expect(queryb.setParameter("value","%john%")).andReturn(queryb).times(1);
		expect(queryb.getSingleResult()).andReturn((Object)Long.valueOf(1)).times(1);
		
		EntityManager entityManager=createMock(EntityManager.class);
		expect(entityManager.getTransaction()).andReturn(entityTransaction).times(6);
		entityManager.persist(user);
		entityManager.remove(user);
		expect(entityManager.find(User.class,1)).andReturn(user).times(1);
		expect(entityManager.createQuery("FROM User u WHERE u.username=:username")).andReturn(querya).times(1);
		expect(entityManager.createQuery("FROM User u WHERE u.name LIKE :value OR u.company LIKE :value OR u.experience LIKE :value ORDER BY u.name ASC")).andReturn(querya).times(1);
		expect(entityManager.createQuery("SELECT COUNT(*) FROM User u WHERE u.name LIKE :value OR u.company LIKE :value OR u.experience LIKE :value")).andReturn(queryb).times(1);
		
		EntityManagerFactory entityManagerFactory=createMock(EntityManagerFactory.class);
		expect(entityManagerFactory.createEntityManager()).andReturn(entityManager).times(1);
		
		replay(entityTransaction,querya,queryb,entityManager,entityManagerFactory);
		
		UserDAO userDAO=new UserDAO(entityManagerFactory);
		
		assertEquals(user,userDAO.load(1));
		assertEquals(user,userDAO.getUserByName("john.smith"));
		assertEquals(Arrays.asList(user),userDAO.find("john","name","ASC",0));
		assertEquals(Long.valueOf(1),userDAO.count("john"));
		userDAO.save(user);
		userDAO.delete(user);
		
		verify(entityTransaction,querya,queryb,entityManager,entityManagerFactory);
	}
}