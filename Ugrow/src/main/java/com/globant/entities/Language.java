package com.globant.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Language {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLang;
	@Column(unique=true, nullable=false)
	private String language;
	
	public Language(int id, String name) {
		setIdLang(id);
		setLanguage(name);
	}
	
	public Language(){
		
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLang;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (idLang != other.idLang)
			return false;
		return true;
	}

	public int getIdLang() {
		return idLang;
	}
	public void setIdLang(int idLang) {
		this.idLang = idLang;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

}
