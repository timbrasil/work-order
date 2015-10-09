package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.CheckListModel;
import br.com.timbrasil.operations.models.ItemCheckList;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.HibernateException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class ItemCheckListDao {

    private EntityManager manager;

    @Inject
    public ItemCheckListDao(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * CDI
     */
    @Deprecated
    public ItemCheckListDao() {
        this(null);
    }

    public void save(ItemCheckList itemCheckList) throws Exception{
        System.out.println(itemCheckList.toString());
        manager.persist(itemCheckList);
    }

    public void save(List<ItemCheckList> itemsCheckList) throws Exception{
        for(ItemCheckList itemCheckList : itemsCheckList){
            this.save(itemCheckList);
        }
    }
}
