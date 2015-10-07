package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.CheckListModel;
import br.com.timbrasil.operations.models.Technology;
import org.hibernate.HibernateException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class CheckListModelDao {

    private EntityManager manager;

    @Inject
    public CheckListModelDao(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * CDI
     */
    @Deprecated
    public CheckListModelDao() {
        this(null);
    }

    public void save(CheckListModel checkListModel) {
        manager.persist(checkListModel);
    }

    public List<CheckListModel> listActive(Boolean active) {
        String jpql = "select t from CheckListModel as t where active = :pActive";
        TypedQuery<CheckListModel> typedQuery = manager.createQuery(jpql,CheckListModel.class);

        typedQuery.setParameter("pActive", active);
        try{
            return typedQuery.getResultList();
        }
        catch (NoResultException nre){
            return null;
        }
    }

    public List<CheckListModel> list() {
        String jpql = "select t from CheckListModel as t";
        TypedQuery<CheckListModel> typedQuery = manager.createQuery(jpql,CheckListModel.class);
        try{
            return typedQuery.getResultList();
        }
        catch (NoResultException nre){
            return null;
        }
    }

    public List<CheckListModel> listByTechnologyAndActive(Technology technology, boolean active){
        String jpql = "select t from CheckListModel as t where active = :pActive and technology = :pTechnology";
        TypedQuery<CheckListModel> typedQuery = manager.createQuery(jpql,CheckListModel.class);

        typedQuery.setParameter("pActive", active);
        typedQuery.setParameter("pTechnology", technology);
        try{
            return typedQuery.getResultList();
        }
        catch (NoResultException nre){
            return null;
        }
    }

    public CheckListModel find(long id){
        return manager.find(CheckListModel.class, id);
    }

    public CheckListModel find(CheckListModel checkListModel){
        return manager.find(CheckListModel.class, checkListModel.getId());
    }


}
