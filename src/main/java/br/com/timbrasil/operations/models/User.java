package br.com.timbrasil.operations.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String name;
	
	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Area area;
	
	@Enumerated(EnumType.STRING)
	private Region region;

	/**
	 * Hibernate only
	 */
	@Deprecated
	public User() {
	}
	
	public User(String name, String email, String password, Area area, Region region) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.area = area;
		this.region = region;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
