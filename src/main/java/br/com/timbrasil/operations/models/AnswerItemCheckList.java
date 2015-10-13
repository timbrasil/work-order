package br.com.timbrasil.operations.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class AnswerItemCheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    private ItemCheckList itemCheckList;

    @Enumerated(EnumType.STRING)
    private AnswersItemChecklist answer;

    private String justification;

    public AnswerItemCheckList(ItemCheckList itemCheckList, AnswersItemChecklist answer, String justification) {
        this.itemCheckList = itemCheckList;
        this.answer = answer;
        this.justification = justification;
    }

    /**
     * Hibernate
     */
    @Deprecated
    public AnswerItemCheckList() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ItemCheckList getItemCheckList() {
        return itemCheckList;
    }

    public void setItemCheckList(ItemCheckList itemCheckList) {
        this.itemCheckList = itemCheckList;
    }

    public AnswersItemChecklist getAnswer() {
        return answer;
    }

    public void setAnswer(AnswersItemChecklist answersItemChecklist) {
        this.answer = answersItemChecklist;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public String toString() {
        return "AnswerItemCheckList{" +
                "id=" + id +
                ", itemCheckList=" + itemCheckList +
                ", answer=" + answer +
                ", justification='" + justification + '\'' +
                '}';
    }
}
