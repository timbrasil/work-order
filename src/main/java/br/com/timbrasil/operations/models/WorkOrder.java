package br.com.timbrasil.operations.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
    @Column(unique = true)
	private String ticketId;

    @ElementCollection(targetClass = Technology.class)
    @Enumerated(EnumType.STRING)
    private List<Technology> technologies;

	@NotNull
    @ManyToOne
	private Site site;
	
	@ManyToMany
	private List<TypeWorkOrder> typeWorkOrders;

	@OneToMany
	private List<LogStatus> logStatus;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar atribution;
	
	public WorkOrder(String ticketId, Site site, List<TypeWorkOrder> typeWorkOrders,LogStatus logStatus, Technology technology) {
		this.ticketId = ticketId;
		this.site = site;
		this.typeWorkOrders = typeWorkOrders;
		this.logStatus = new ArrayList<>();
		this.logStatus.add(logStatus);
        this.technologies = new ArrayList<>();
        this.technologies.add(technology);
	}
	
	/**
	 * Hibernate only
	 */
	@Deprecated
	public WorkOrder(){
		this.technologies = new ArrayList<>();
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

    public Technology getTechnology() {
        if(this.technologies.size()==0){
            return null;
        }
        return this.technologies.get(0);
    }

    public void setTechnology(Technology technology) {
        this.technologies.clear();
        this.technologies.add(technology);
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
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
                ", technologies=" + technologies +
                ", site=" + site +
                ", typeWorkOrders=" + typeWorkOrders +
                ", logStatus=" + logStatus +
                ", atribution=" + atribution +
                '}';
    }

    //Getters e Setters personalizados.

    public void pushLogStatus(LogStatus logStatus) {
        if(this.logStatus==null){
            this.logStatus = new ArrayList<LogStatus>();
        }
        this.logStatus.add(logStatus);
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

    public LogStatus getLogStatus(int index){
        try{
            return this.logStatus.get(index);
        }
        catch (NullPointerException e){
            return null;
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public LogStatus getLastLogStatusWithCheckList(){
        for (int i=this.logStatus.size()-1;i>=0;i--){
            if(this.logStatus.get(i).getCheckList()!=null){
                return this.logStatus.get(i);
            }
        }
        return null;
    }

    public boolean hasTypeWorkOrder(TypeWorkOrder typeWorkOrder){
        return this.typeWorkOrders.contains(typeWorkOrder);
    }
}
