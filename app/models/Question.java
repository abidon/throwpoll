package models;

/**
 * Created by Timote on 19/12/14.
 */

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
    public Long id;

    @Constraints.Required
    public String name;

    @OneToMany(mappedBy = "question")
    public List<Choix> choixList = new ArrayList<Choix>();


    public boolean multiple;

    public Question(){
        // keep empty
    }

    public Question(String name, boolean multiple){
        this.name = name;
        this.multiple = multiple;
    }

    public String getName() {
    	return this.name;
    }
    
    public List<Choix> getChoixList() {
    	return this.choixList;
    }
    
    public boolean isMultiple() {
    	return this.multiple;
    }

    public void setChoixList(List<Choix> choixList){
        this.choixList = choixList;
    }

}
