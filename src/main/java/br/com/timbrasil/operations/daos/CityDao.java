package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.City;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class CityDao {

    private EntityManager manager;

    @Inject
    public CityDao(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * CDI
     */
    @Deprecated
    public CityDao() {
        this(null);
    }

    public City find(City city){
        return manager.find(City.class,city.getId());
    }

    public List<City> list(){
        String jpql = "select c from City as c";
        TypedQuery<City> typedQuery = manager.createQuery(jpql,City.class);
        try{
            return typedQuery.getResultList();
        }
        catch (NoResultException nre){
            return null;
        }

    }

}
