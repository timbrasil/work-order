package br.com.timbrasil.operations.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "\"User\"")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String name;
	
	@Email(message = "Email inválido")
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	//Matricula F8012345
	@NotNull
	@Size(min = 1, max = 8)
	private String register;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Area area;
	
	@Enumerated(EnumType.STRING)
	@NotNull
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
	
	@Override
	public String toString() {
		return name;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}
}
