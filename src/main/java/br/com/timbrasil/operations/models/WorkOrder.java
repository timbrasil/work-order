package br.com.timbrasil.operations.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;
import javax.persistence.metamodel.Type;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import sun.rmi.runtime.Log;

@Entity
public class WorkOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
    @Column(unique = true)
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

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar atribution;
	
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

    public Calendar getAtribution() {
        return atribution;
    }

    public void setAtribution(Calendar atribution) {
        this.atribution = atribution;
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

    //Getters e Setters personalizados.

    public void pushLogStatus(LogStatus logStatus) {
        if(this.logStatus==null){
            this.logStatus = new ArrayList<LogStatus>();
        }
        this.logStatus.add(logStatus);
    }

    public void pushLogAcception(LogAcception logAcception){
        if(this.logAcception==null){
            this.logAcception = new ArrayList<LogAcception>();
        }
        this.logAcception.add(logAcception);
    }

    /**
     * Consulta o ultimo elemento do Array de Histórico do LogStatus
     * @return LogStatus
     */
    public LogStatus getLastLogStatus(){
        if(this.logStatus==null){
            return null;
        }
        if(this.logStatus.size()==0){
            return null;
        }
        return this.logStatus.get(this.logStatus.size()-1);
    }

    /**
     * Consulta o ultimo elemento do Array de Histórico do LogAcception
     * @return LogAcception
     */
    public LogAcception getLastLogAcception(){
        if(this.logAcception==null){
            return null;
        }
        if(this.logAcception.size()==0){
            return null;
        }
        return this.logAcception.get(this.logAcception.size()-1);
    }

    public boolean hasTypeWorkOrder(TypeWorkOrder typeWorkOrder){
        return this.typeWorkOrders.contains(typeWorkOrder);
    }
}
