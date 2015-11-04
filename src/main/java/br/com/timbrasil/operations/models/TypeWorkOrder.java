package br.com.timbrasil.operations.models;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class TypeWorkOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	/**
	 * Hibernate only
	 */
	@Deprecated
	public TypeWorkOrder(){
		
	}

	public TypeWorkOrder(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TypeWorkOrder{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
