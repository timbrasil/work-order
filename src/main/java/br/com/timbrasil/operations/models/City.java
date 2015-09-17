package br.com.timbrasil.operations.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private State state;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private int ddd;
	
	public City(State state, String name, int ddd) {
		this.state = state;
		this.name = name;
		this.ddd = ddd;
	}
	
	/**
	 * Hibernate only
	 */
	@Deprecated
	public City(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
