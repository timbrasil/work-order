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
    @Enumerated(EnumType.STRING)
    private Technology technology;

	@NotNull
    @ManyToOne
	private Site site;
	
	@ManyToMany
	private List<TypeWorkOrder> typeWorkOrders;

	@OneToMany
	private List<LogStatus> logStatus;
	
	@OneToMany
	private List<LogAcception> logAcception;
	
	public WorkOrder(String ticketId, Site site, List<TypeWorkOrder> typeWorkOrders,LogStatus logStatus, Technology technology) {
		this.ticketId = ticketId;
		this.site = site;
		this.typeWorkOrders = typeWorkOrders;
		this.logStatus = new ArrayList<LogStatus>();
		this.logStatus.add(logStatus);
        this.technology = technology;
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

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "id=" + id +
                ", ticketId='" + ticketId + '\'' +
                ", technology=" + technology +
                ", site=" + site +
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
