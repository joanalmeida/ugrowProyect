package com.globant.persistence.test;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.junit.Test;
import com.globant.entities.Role;
import com.globant.persistence.RoleDAO;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class RoleDAOTest {
	@Test
	public void roleDAOTest(){
		Role rolea=new Role(1,"user");
		Role roleb=new Role(2,"supervisor");
		List<Role> roles=new ArrayList<Role>();
		roles.add(rolea);
		roles.add(roleb);
		
		Query query=createMock(Query.class);
		expect(query.setParameter("name","user")).andReturn(query).times(1);
		expect(query.getSingleResult()).andReturn((Object)rolea).times(1);
		expect(query.getResultList()).andReturn(roles).times(1);
		
		EntityManager entityManager=createMock(EntityManager.class);
		expect(entityManager.createQuery("FROM Role r WHERE r.name=:name")).andReturn(query).times(1);
		expect(entityManager.createQuery("FROM Role")).andReturn(query).times(1);
		
		EntityManagerFactory entityManagerFactory=createMock(EntityManagerFactory.class);
		expect(entityManagerFactory.createEntityManager()).andReturn(entityManager).times(1);
		
		replay(query,entityManager,entityManagerFactory);
		
		RoleDAO roleDAO=new RoleDAO(entityManagerFactory);
		
		assertEquals(rolea,roleDAO.getRoleByName("user"));
		assertEquals(roles,roleDAO.getAllRoles());
		
		verify(query,entityManager,entityManagerFactory);
	}
}