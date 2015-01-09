package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Vote extends Model{
    public static Finder<Long, Vote> find = new Finder<Long, Vote>(Long.class, Vote.class);

    @Id
    private Long id;

    private String ip;

    @ManyToMany
    private List<Choix> choixList = new ArrayList<Choix>();

    @ManyToOne
    private Question question;


    public Vote(String ipAdress, Choix choix, Question question){
        this.ip = ipAdress;
        this.choixList.add(choix);
        this.question = question;
    }

    public static boolean alreadyVoted(String ip, Question question){
        return find.where().eq("ip", ip).eq("question", question).findRowCount() != 0;
    }

    public void addChoix(Choix c){
        this.choixList.add(c);
    }

    public static Vote getByIp(String ip){
        return find.where().eq("ip", ip).findUnique();
    }
}
