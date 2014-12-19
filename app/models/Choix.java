package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by Timote on 19/12/14.
 */
@Entity
public class Choix extends Model {
    public static Finder<Long, Choix> find = new Finder<Long, Choix>(Long.class, Choix.class);
    @Id
    public Long id;


    public String name;

    @ManyToOne
    public Question question;

    @ManyToMany
    public List<Vote> votes;

    public Choix(String name){
        this.name = name;
    }

    
    public Long getId()
    {
    	return this.id;
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public void setQuestion(Question question)
    {
    	this.question = question;
    }

}
