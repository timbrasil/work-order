package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.AnswerItemCheckList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by IgorVasconcelos on 09/10/2015.
 */
@RequestScoped
public class AnswerItemCheckListDao {

    private EntityManager manager;

    @Inject
    public AnswerItemCheckListDao(EntityManager manager) {
        this.manager = manager;
    }

    public AnswerItemCheckListDao() {
    }

    public void save(AnswerItemCheckList answer){
        manager.persist(answer);
    }

    public void save(List<AnswerItemCheckList> answers){
        for(AnswerItemCheckList answer : answers){
            this.save(answer);
        }
    }
}

