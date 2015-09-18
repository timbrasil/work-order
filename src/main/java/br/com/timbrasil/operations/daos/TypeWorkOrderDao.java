package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.TypeWorkOrder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class TypeWorkOrderDao {

    private EntityManager manager;

    @Inject
    public TypeWorkOrderDao(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * CDI
     */
    @Deprecated
    public TypeWorkOrderDao() {
        this(null);
    }

    public List<TypeWorkOrder> list(){
        String jpql = "select t from TypeWorkOrder as t";
        TypedQuery<TypeWorkOrder> typedQuery = manager.createQuery(jpql,TypeWorkOrder.class);
        try{
            return typedQuery.getResultList();
        }
        catch (NoResultException nre){
            return null;
        }

    }

}
