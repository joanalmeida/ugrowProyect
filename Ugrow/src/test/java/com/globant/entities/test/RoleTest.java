package com.globant.entities.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.globant.entities.Role;

public class RoleTest{
	@Test
	public void roleTest(){
		Role rolea=new Role();
		rolea.setIdRole(1);
		rolea.setName("user");
		
		assertEquals(Integer.valueOf(1),rolea.getIdRole());
		assertEquals("user",rolea.getName());
		
		Role roleb=new Role(1,"supervisor");
		
		assertTrue(rolea.equals(roleb));
		assertEquals(rolea.hashCode(),roleb.hashCode());
		
		roleb.setIdRole(2);
		assertFalse(rolea.equals(roleb));
	}
}