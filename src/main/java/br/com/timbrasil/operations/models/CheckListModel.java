package br.com.timbrasil.operations.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class CheckListModel {

    @Id
    private long id;

    @NotNull
    @OneToMany
    private List<ItemCheckList> itemsCheckList;

    @NotEmpty
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Technology technology;

    @NotNull
    private boolean active;

    public CheckListModel(long id, List<ItemCheckList> itemsCheckList, String description, Technology technology, boolean active) {
        this.id = id;
        this.itemsCheckList = itemsCheckList;
        this.description = description;
        this.technology = technology;
        this.active = active;
    }

    @Deprecated
    public CheckListModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ItemCheckList> getItemsCheckList() {
        return itemsCheckList;
    }

    public void setItemsCheckList(List<ItemCheckList> itemsCheckList) {
        this.itemsCheckList = itemsCheckList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
