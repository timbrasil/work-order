package br.com.timbrasil.operations.daos;

import br.com.timbrasil.operations.models.Address;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by IgorVasconcelos on 22/09/2015.
 */
@RequestScoped
public class AddressDao {

    private EntityManager manager;

    @Inject
    public AddressDao(EntityManager manager) {
        this.manager = manager;
    }

    @Deprecated
    public AddressDao() {
    }

    public void save(Address address){
        manager.persist(address);
    }

    public Address find(long id){
        return manager.find(Address.class, id);
    }

    public Address find(Address address){
        return manager.find(Address.class, address.getId());
    }
}
