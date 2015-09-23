package br.com.timbrasil.operations.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
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
    @ManyToOne
	private Site site;
	
	@NotNull
	@ManyToOne
	private Address address;
	
	@ManyToMany
	private List<TypeWorkOrder> typeWorkOrders;

	@OneToMany
	private List<LogStatus> logStatus;
	
	@OneToMany
	private List<LogAcception> logAcception;
	
	public WorkOrder(String ticketId, Site site, List<TypeWorkOrder> typeWorkOrders, Address address, LogStatus logStatus) {
		this.ticketId = ticketId;
		this.site = site;
		this.typeWorkOrders = typeWorkOrders;
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


    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<TypeWorkOrder> getTypeWorkOrders() {
        return typeWorkOrders;
    }

    public void setTypeWorkOrders(List<TypeWorkOrder> typeWorkOrders) {
        this.typeWorkOrders = typeWorkOrders;
    }

    public List<LogStatus> getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(List<LogStatus> logStatus) {
        this.logStatus = logStatus;
    }

    public List<LogAcception> getLogAcception() {
        return logAcception;
    }

    public void setLogAcception(List<LogAcception> logAcception) {
        this.logAcception = logAcception;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "id=" + id +
                ", ticketId='" + ticketId + '\'' +
                ", site=" + site +
                ", address=" + address +
                ", typeWorkOrders=" + typeWorkOrders +
                ", logStatus=" + logStatus +
                ", logAcception=" + logAcception +
                '}';
    }

    public void pushLogStatus(LogStatus logStatus) {
        if(this.logStatus==null){
            this.logStatus = new ArrayList<LogStatus>();
        }
        this.logStatus.add(logStatus);
    }
}
