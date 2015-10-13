package br.com.timbrasil.operations.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ItemCheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String dirId;

    @NotEmpty
    private String description;

    @NotNull
    private boolean active;

    @ElementCollection(targetClass = Technology.class)
    @Enumerated(EnumType.STRING)
    private List<Technology> technologies;

    public ItemCheckList(String dirId, String description, boolean active, List<Technology> technologies) {
        this.dirId = dirId;
        this.description = description;
        this.active = active;
        this.technologies = technologies;
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

    public String getDirId() {
        return dirId;
    }

    public void setDirId(String dirId) {
        this.dirId = dirId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    @Override
    public String toString() {
        return "ItemCheckList{" +
                "id=" + id +
                ", dirId='" + dirId + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", technologies=" + technologies +
                '}';
    }
}
