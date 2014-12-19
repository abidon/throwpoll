package models;

/**
 * Created by Timote on 19/12/14.
 */

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Question extends Model {

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
    }
}
