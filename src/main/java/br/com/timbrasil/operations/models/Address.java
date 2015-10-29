package br.com.timbrasil.operations.models;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.com.caelum.vraptor.util.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@ManyToOne
	private City city;
	
	@NotEmpty
	private String street;

	public Address(City city, String street) {
		this.city = city;
		this.street = street;
	}
	
	/**
	 * Hibernate ONly
	 */
	@Deprecated
	public Address(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", city=" + city +
				", street='" + street + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Address address = (Address) o;

		if (!getCity().equals(address.getCity())) return false;
		return getStreet().equals(address.getStreet());

	}
}
