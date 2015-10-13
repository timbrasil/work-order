package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.ItemCheckList;
import br.com.timbrasil.operations.models.Technology;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

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

    public List<ItemCheckList> listByTechnologyAndActive(Technology technology, Boolean active){
        String jpql = "select i from ItemCheckList as i where active = :pActive";
        TypedQuery<ItemCheckList> typedQuery = manager.createQuery(jpql, ItemCheckList.class);
        typedQuery.setParameter("pActive", active);
        List<ItemCheckList> technologyItems = new ArrayList<ItemCheckList>();

        for(ItemCheckList itemCheckList:typedQuery.getResultList()){
            if(itemCheckList.getTechnologies().contains(technology)){
                technologyItems.add(itemCheckList);
            }
        }
        return technologyItems;
    }

    public List<ItemCheckList> list(){
        String jpql = "select i from ItemCheckList as i";
        TypedQuery<ItemCheckList> typedQuery = manager.createQuery(jpql,ItemCheckList.class);

        return typedQuery.getResultList();
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
