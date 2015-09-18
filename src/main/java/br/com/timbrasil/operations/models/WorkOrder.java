package br.com.timbrasil.operations.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class WorkOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String ticketId;
	
	@NotNull
	private Technology technology;
	
	@NotNull
	private Site site;
	
	@NotNull
	private Address address;
	
	@OneToMany
	private List<TypeWorkOrder> types;

	@OneToMany
	private List<LogStatus> logStatus;
	
	@OneToMany
	private List<LogAcception> logAcception;
	
	public WorkOrder(String ticketId, Technology technology, Site site, List<TypeWorkOrder> types, Address address, LogStatus logStatus) {
		this.ticketId = ticketId;
		this.technology = technology;
		this.site = site;
		this.types = types;
		this.address = address;
		this.logStatus = new ArrayList<LogStatus>();
		this.logStatus.add(logStatus);
	}
	
	/**
	 * Hibernate only
	 */
	@Deprecated
	public WorkOrder(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public List<TypeWorkOrder> getTypes() {
		return types;
	}

	public void setTypes(List<TypeWorkOrder> types) {
		this.types = types;
	}

	public List<LogStatus> getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(List<LogStatus> logStatus) {
		this.logStatus = logStatus;
	}

	public List<LogAcception> getLogList() {
		return logAcception;
	}

	public void setLogList(List<LogAcception> logList) {
		this.logAcception = logList;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
