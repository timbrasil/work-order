package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.LogStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by IgorVasconcelos on 22/09/2015.
 */
@RequestScoped
public class LogStatusDao {

    private EntityManager manager;

    @Inject
    public LogStatusDao(EntityManager manager) {
        this.manager = manager;
    }

    @Deprecated
    public LogStatusDao() {
    }

    public void save(LogStatus logStatus){
        manager.persist(logStatus);
    }
}
