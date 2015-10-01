package br.com.timbrasil.operations.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
public class ItemCheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String description;

    public ItemCheckList(String description) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ItemCheckList{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
