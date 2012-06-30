package com.globant.entities.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.globant.entities.Language;

public class LanguageTest{
	@Test
	public void languageTest(){
		Language languagea=new Language();
		languagea.setIdLang(1);
		languagea.setLanguage("Spanish");
		
		assertEquals(1,languagea.getIdLang());
		assertEquals("Spanish",languagea.getLanguage());
		
		Language languageb=new Language(1,"English");
		
		assertTrue(languagea.equals(languageb));
		assertEquals(languagea.hashCode(),languageb.hashCode());
		
		languageb.setIdLang(2);
		assertFalse(languagea.equals(languageb));
	}
}