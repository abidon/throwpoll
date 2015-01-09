package models;


import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.List;


import play.data.Form;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.db.ebean.Model;

@Entity
public class Question extends Model {
	
	public static Finder<Long, Question> find = new Finder<Long, Question>(Long.class, Question.class);
	
    @Id
    private Long id;

    @Constraints.Required
    private String name;

    @OneToMany(mappedBy = "question")
    @OrderBy
    private List<Choix> choixList = new ArrayList<Choix>();

    @OneToMany(mappedBy = "question")
    private List<Vote> votes = new ArrayList<Vote>();

    private String lol;

    private boolean multiple;

    public Question(){
        // keep empty
    }

    public Question(String name, boolean multiple){
        this.name = name;
        this.multiple = multiple;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Choix> getChoixList() {
        return choixList;
    }

    public List<Choix> getChoixListOrdered(){
        return Choix.getOrdered(this);
    }

    public void setChoixList(List<Choix> choixList) {
        this.choixList = choixList;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    @Override
    public void delete(){
        for(Choix c : choixList){
            c.delete();
        }
        for(Vote v : votes){
            v.delete();
        }
        super.delete();
    }


}
