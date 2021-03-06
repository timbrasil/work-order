package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.AnswerItemCheckList;
import br.com.timbrasil.operations.models.CheckList;
import org.hibernate.annotations.Check;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by IgorVasconcelos on 08/10/2015.
 */
@RequestScoped
public class CheckListDao {

    private EntityManager manager;
    private AnswerItemCheckListDao answerItemCheckListDao;

    @Inject
    public CheckListDao(EntityManager manager, AnswerItemCheckListDao answerItemCheckListDao) {
        this.manager = manager;
        this.answerItemCheckListDao = answerItemCheckListDao;
    }

    @Deprecated
    public CheckListDao() {
    }

    public void save(CheckList checkList){
        answerItemCheckListDao.save(checkList.getAnswers());
        manager.persist(checkList);
    }

    public CheckList find(CheckList checkList){
        return manager.find(CheckList.class,checkList.getId());
    }
}
