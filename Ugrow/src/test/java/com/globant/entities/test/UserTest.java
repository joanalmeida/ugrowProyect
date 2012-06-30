package com.globant.entities.test;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import com.globant.entities.Language;
import com.globant.entities.Role;
import com.globant.entities.User;

public class UserTest{
	@Test
	public void userTest(){
		User usera=new User();
		Role rolea=new Role(3,"supervisor");
		usera.setIdUser(1);
		usera.setName("John Smith");
		usera.setCompany("Globant");
		usera.setJobTitle("Java Developer");
		usera.setEmail("john.smith@globant.com");
		usera.setRole(rolea);
		usera.setExperience("none");
		Set<Language> languagesa=new HashSet<Language>();
		languagesa.add(new Language(1,"Spanish"));
		languagesa.add(new Language(2,"English"));
		usera.setLanguages(languagesa);
		usera.setUsername("john.smith");
		usera.setPassword("admin");
		usera.setEnabled(true);
		
		assertEquals(Integer.valueOf(1),usera.getIdUser());
		assertEquals("John Smith",usera.getName());
		assertEquals("Globant",usera.getCompany());
		assertEquals("Java Developer",usera.getJobTitle());
		assertEquals("john.smith@globant.com",usera.getEmail());
		assertEquals(rolea,usera.getRole());
		assertEquals("none",usera.getExperience());
		assertEquals(languagesa,usera.getLanguages());
		assertEquals("john.smith",usera.getUsername());
		assertEquals("admin",usera.getPassword());
		assertEquals(true,usera.isEnabled());
		
		Role roleb=new Role(2,"user");
		Set<Language> languagesb=new HashSet<Language>();
		languagesb.add(new Language(1,"Spanish"));
		User userb=new User(1,"Jennifer Lopez","Hollywood","Actress","jennifer.lopez@globant.com",roleb,"a lot",languagesb,"jennifer.lopez","1234",false);
		
		assertTrue(usera.equals(userb));
		assertEquals(usera.hashCode(),userb.hashCode());
		
		userb.setIdUser(2);
		assertFalse(usera.equals(userb));
	}
}