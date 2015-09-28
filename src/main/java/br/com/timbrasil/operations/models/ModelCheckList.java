package br.com.timbrasil.operations.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ModelCheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    @Enumerated(EnumType.STRING)
    private Technology technology;

    @NotNull
    @ManyToMany
    private List<ItemCheckList> itemsCheckList;

    public ModelCheckList(String name, Technology technology, List<ItemCheckList> itemsCheckList) {
        this.name = name;
        this.technology = technology;
        this.itemsCheckList = itemsCheckList;
    }

    /**
     * Hibernate
     */
    @Deprecated
    public ModelCheckList() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public List<ItemCheckList> getItemsCheckList() {
        return itemsCheckList;
    }

    public void setItemsCheckList(List<ItemCheckList> itemsCheckList) {
        this.itemsCheckList = itemsCheckList;
    }
}
