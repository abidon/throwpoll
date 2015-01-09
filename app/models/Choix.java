package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
public class Choix extends Model {
    public static Finder<Long, Choix> find = new Finder<Long, Choix>(Long.class, Choix.class);

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @ManyToOne
    public Question question;

    @ManyToMany(mappedBy = "choixList", cascade = CascadeType.ALL)
    public List<Vote> votes;

    private int voteCount = 0;

    public Choix(){
        // keep empty
    }

    public Choix(String name){
        this.name = name;
    }

    public static boolean isValidId(Long id){
        return find.byId(id) != null;
    }

    public static List<Choix> getOrdered(Question question){
        return find.where().eq("question", question).orderBy("id").findList();
    }

    public int getVoteCount(){
        this.setVoteCount(this.votes.size());
        return this.voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Long getId(){
        return this.id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Vote> getVotes() {
        return this.votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
