package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    public  static Choix getChoix(Long id){
        return find.byId(id);
    }

    public static boolean isValidId(Long cid){
        return find.byId(cid) != null;
    }

    public static Integer getVoteCount(Choix c){
        return c.votes.size();
    }
    
    public void setQuestion(Question question)
    {
    	this.question = question;
    }

}
