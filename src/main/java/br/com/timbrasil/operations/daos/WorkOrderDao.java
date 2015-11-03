package br.com.timbrasil.operations.daos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.timbrasil.operations.models.CheckList;
import br.com.timbrasil.operations.models.Technology;
import br.com.timbrasil.operations.models.WorkOrder;
import org.hibernate.HibernateException;

import java.util.List;

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

	public void update(WorkOrder workOrder) throws HibernateException{
		manager.merge(workOrder);
	}
	
	public WorkOrder find(long id){
		return manager.find(WorkOrder.class, id);
	}

	/**
	 * Procura por TicketId ou Id da WorkOrder
	 * @param workOrder - TicketId ou Id devem estar preenchidos.
	 * @return - Retorna a WorkOrder encontrada
	 */
	public WorkOrder find(WorkOrder workOrder){
		String jpql = "select t from WorkOrder as t where id = :pId or ticketId = :pTicketId";
		TypedQuery<WorkOrder> typedQuery = manager.createQuery(jpql,WorkOrder.class);

		typedQuery.setParameter("pId", workOrder.getId());
		typedQuery.setParameter("pTicketId", workOrder.getTicketId());
		try{
			return typedQuery.getSingleResult();
		}
		catch (NoResultException nre){
			return null;
		}
	}

	public List<WorkOrder> listTechnology(Technology technology){
		String jpql = "select t from WorkOrder as t where technology = :pTechnology";
		TypedQuery<WorkOrder> typedQuery = manager.createQuery(jpql,WorkOrder.class);

		typedQuery.setParameter("pTechnology", technology);
		try{
			return typedQuery.getResultList();
		}
		catch (NoResultException nre){
			return null;
		}
	}


	public List<WorkOrder> list() {
		String jpql = "select t from WorkOrder as t";
		TypedQuery<WorkOrder> typedQuery = manager.createQuery(jpql,WorkOrder.class);
		try{
			return typedQuery.getResultList();
		}
		catch (NoResultException nre){
			return null;
		}
	}
}
