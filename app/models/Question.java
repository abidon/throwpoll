package models;

/**
 * Created by Timote on 19/12/14.
 */

import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Question extends Model {
	
	public static Finder<Long, Question> find = new Finder<Long, Question>(Long.class, Question.class);
	
    @Id
    private Long id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    @OneToMany(mappedBy = "question")
    private List<Choix> choixList;

    @Constraints.Required
    private boolean multiple;

    public Question(String name, List<Choix> choixList, boolean multiple) {
        this.multiple = multiple;
        this.name = name;
        this.choixList = choixList;
        
        Iterator<Choix> it = choixList.iterator();
		while (it.hasNext())
		{
			it.next().setQuestion(this);
		}
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
}
