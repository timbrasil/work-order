package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.CheckListModel;
import org.hibernate.HibernateException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
}
