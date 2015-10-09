package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.LogAcception;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by IgorVasconcelos on 08/10/2015.
 */
@RequestScoped
public class LogAcceptionDao {

    private EntityManager manager;

    @Inject
    public LogAcceptionDao(EntityManager manager) {
        this.manager = manager;
    }

    @Deprecated
    public LogAcceptionDao() {
    }

    public void save(LogAcception logAcception){
        manager.persist(logAcception);
    }
}
