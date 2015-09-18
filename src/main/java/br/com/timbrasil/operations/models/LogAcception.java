package br.com.timbrasil.operations.models;

import java.util.Calendar;

import javax.persistence.*;

@Entity
public class LogAcception {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private WorkOrder workOrder;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;

	@Transient
	private CheckList checkList;
	
	@Enumerated
	private StatusAcception status;
	
	
	public LogAcception(Calendar date, CheckList checkList, StatusAcception status) {
		this.date = date;
		this.checkList = checkList;
		this.status = status;
	}
	
	/**
	 * Hibernate Only
	 */
	@Deprecated
	public LogAcception(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public CheckList getCheckList() {
		return checkList;
	}

	public void setCheckList(CheckList checkList) {
		this.checkList = checkList;
	}

	public StatusAcception getStatus() {
		return status;
	}

	public void setStatus(StatusAcception status) {
		this.status = status;
	}
}
