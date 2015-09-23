package br.com.timbrasil.operations.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class LogStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar atribution;
	
	@Temporal(TemporalType.DATE)
	private Calendar execution;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusWorkOrder status;
	
	
	public LogStatus(Calendar atribution, StatusWorkOrder status) {
		this.atribution = atribution;
		this.status = status;
	}
	
	/**
	 * Hibernate only
	 */
	@Deprecated
	public LogStatus(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getAtribution() {
		return atribution;
	}

	public void setAtribution(Calendar atribution) {
		this.atribution = atribution;
	}

	public Calendar getExecution() {
		return execution;
	}

	public void setExecution(Calendar execution) {
		this.execution = execution;
	}

	public StatusWorkOrder getStatus() {
		return status;
	}

	public void setStatus(StatusWorkOrder status) {
		this.status = status;
	}

    @Override
    public String toString() {
        return "LogStatus{" +
                "id=" + id +
                ", atribution=" + atribution +
                ", execution=" + execution +
                ", status=" + status +
                '}';
    }
}
