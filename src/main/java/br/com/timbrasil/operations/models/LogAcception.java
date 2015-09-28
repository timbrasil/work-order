package br.com.timbrasil.operations.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class LogAcception {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;

	@NotNull
	@OneToMany
	private List<AnswerItemCheckList> answerItemCheckList;

	@Enumerated(EnumType.STRING)
	private LogAcceptionType logAcceptionType;
	
	@Enumerated(EnumType.STRING)
	private StatusAcception status;

	public LogAcception(Calendar date, List<AnswerItemCheckList> answerItemCheckList, LogAcceptionType logAcceptionType, StatusAcception status) {
		this.date = date;
		this.answerItemCheckList = answerItemCheckList;
		this.logAcceptionType = logAcceptionType;
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

	public List<AnswerItemCheckList> getAnswerItemCheckList() {
		return answerItemCheckList;
	}

	public void setAnswerItemCheckList(List<AnswerItemCheckList> answerItemCheckList) {
		this.answerItemCheckList = answerItemCheckList;
	}

	public LogAcceptionType getLogAcceptionType() {
		return logAcceptionType;
	}

	public void setLogAcceptionType(LogAcceptionType logAcceptionType) {
		this.logAcceptionType = logAcceptionType;
	}

	public StatusAcception getStatus() {
		return status;
	}

	public void setStatus(StatusAcception status) {
		this.status = status;
	}
}
