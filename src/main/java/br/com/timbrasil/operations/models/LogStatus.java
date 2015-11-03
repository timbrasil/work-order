package br.com.timbrasil.operations.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class LogStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Calendar execution;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusWorkOrder status;

	@OneToOne
	private CheckList checkList;

	public LogStatus(Calendar execution, StatusWorkOrder status) {
		this.execution = execution;
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

	public CheckList getCheckList() {
		return checkList;
	}

	public void setCheckList(CheckList checkList) {
		this.checkList = checkList;
	}

	@Override
	public String toString() {
		return "LogStatus{" +
				"id=" + id +
				", execution=" + execution +
				", status=" + status +
				", checkList=" + checkList +
				'}';
	}
}
