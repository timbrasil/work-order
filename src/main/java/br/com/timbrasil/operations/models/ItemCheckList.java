package br.com.timbrasil.operations.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
public class ItemCheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ItemCheckList parent;

    @OneToMany
    private List<ItemCheckList> childrens;

    @NotEmpty
    private String description;

    public ItemCheckList(ItemCheckList parent, List<ItemCheckList> childrens, String description) {
        this.parent = parent;
        this.childrens = childrens;
        this.description = description;
    }

    /**
     * Hibernate
     */
    @Deprecated
    public ItemCheckList() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ItemCheckList getParent() {
        return parent;
    }

    public void setParent(ItemCheckList parent) {
        this.parent = parent;
    }

    public List<ItemCheckList> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<ItemCheckList> childrens) {
        this.childrens = childrens;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
