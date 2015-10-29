package br.com.timbrasil.operations.models;

import com.amazonaws.services.simpledb.model.Item;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class CheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public boolean isSampling() {
        return sampling;
    }

    public void setSampling(boolean sampling) {
        this.sampling = sampling;
    }

    @Override
    public String toString() {
        return "CheckList{" +
                "id=" + id +
                ", answers=" + answers +
                ", sampling=" + sampling +
                '}';
    }

    //Get e Setter personalizado.
    public AnswerItemCheckList getAnswerByItemCheckList(ItemCheckList itemCheckList){
        for(AnswerItemCheckList answer: this.answers){
            if(answer.getItemCheckList()==itemCheckList){
                return answer;
            }
        }
        return null;
    }
}
