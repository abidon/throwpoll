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

    @Id
    private Long id;

    @Constraints.Required
    private String name;

    @ManyToOne
    private Question question;

    @ManyToMany
    private List<Vote> votes;

    public Choix(String name)
    {
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
