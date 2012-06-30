package com.globant.entities;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class User{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUser;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String company;
	@Column(nullable=false)
	private String jobTitle;
	@Column(nullable=false)
	private String email;	
	@ManyToOne
	@JoinColumn(name="idRole")
	private Role role;
	private String experience;
	@ManyToMany
	private Set<Language> languages;
	@Column(nullable=false)
	private String username;
	@Column(nullable=false)
	private String password;
	private String imgUrl;
	private boolean enabled;
	
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public User(){
		setLanguages(new HashSet<Language>());
	}
	
	public User(Integer id,String name,String company,String jobTitle,String email,Role role,String experience,Set<Language> languages,String username,String password,boolean enabled){
		setIdUser(id);
		setName(name);
		setCompany(company);
		setJobTitle(jobTitle);
		setEmail(email);
		setRole(role);
		setExperience(experience);
		setLanguages(languages);
		setUsername(username);
		setPassword(password);
		setEnabled(enabled);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
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
		User other = (User) obj;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}
	public Set<Language> getLanguages() {
		return languages;
	}
	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	}
	public boolean speaks(String lang) {
		Iterator<Language> it = getLanguages().iterator();
		while(it.hasNext()) {
			Language language = it.next();
			if(language.getLanguage().equals(lang)) {
				return true;
			}
		}
		return false;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}