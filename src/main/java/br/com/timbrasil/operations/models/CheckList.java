package br.com.timbrasil.operations.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class CheckList {

    @Id
    private long id;

    @OneToMany
    private List<AnswerItemCheckList> answers;

    @NotNull
    private boolean sampling; //Para indicar se é por amostragem ou não.

    public CheckList(long id, List<AnswerItemCheckList> answers, boolean sampling) {
        this.id = id;
        this.answers = answers;
        this.sampling = sampling;
    }

    @Deprecated
    public CheckList() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<AnswerItemCheckList> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerItemCheckList> answers) {
        this.answers = answers;
    }
}
