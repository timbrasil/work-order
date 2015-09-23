package br.com.timbrasil.operations.daos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.timbrasil.operations.models.WorkOrder;
import org.hibernate.HibernateException;

@RequestScoped
public class WorkOrderDao {
	
	private final EntityManager manager;

	@Inject
	public WorkOrderDao(EntityManager manager) {
		this.manager = manager;
	}
	
	/**
	 * CDI
	 */
	@Deprecated
	public WorkOrderDao(){
		this(null);
	}
	
	public void save(WorkOrder workOrder) throws HibernateException{
		manager.persist(workOrder);
	}
	
	public WorkOrder find(long id){
		return manager.find(WorkOrder.class, id);
	}
	

}
