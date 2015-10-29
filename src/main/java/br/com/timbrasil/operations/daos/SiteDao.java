package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.Address;
import br.com.timbrasil.operations.models.City;
import br.com.timbrasil.operations.models.Site;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Created by IgorVasconcelos on 22/09/2015.
 */
@RequestScoped
public class SiteDao {

    private EntityManager manager;
    private CityDao cityDao;
    private AddressDao addressDao;

    @Inject
    public SiteDao(EntityManager manager, CityDao cityDao, AddressDao addressDao) {
        this.manager = manager;
        this.cityDao = cityDao;
        this.addressDao = addressDao;
    }

    @Deprecated
    public SiteDao() {
    }

    public void save(Site site){
        manager.persist(site);
    }

    public Site find(Site site){
        String jpql = "select s from Site as s where s.name = :pName or s.id = :pId";
        TypedQuery<Site> typedQuery = manager.createQuery(jpql, Site.class);

        typedQuery.setParameter("pName", site.getName());
        typedQuery.setParameter("pId", site.getId());
        try{
            return typedQuery.getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }

    public Site findOrPersist(Site site, City city, Address address){
        if(this.find(site)==null){
            //City
            city = cityDao.find(city);

            //Address
            address.setCity(city);
            System.out.println(address);
            addressDao.save(address);

            site.setAddress(address);
            this.save(site);
        }
        else{
            site = this.find(site);
        }
        return site;
    }

}
