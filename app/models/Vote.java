package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timote on 19/12/14.
 */
@Entity
public class Vote extends Model{
    public static Finder<Long, Vote> find = new Finder<Long, Vote>(Long.class, Vote.class);

    @Id
    private Long id;


    private String ip;

    @ManyToMany
    private List<Choix> choixList = new ArrayList<Choix>();

    public Vote(String ipAdress, Long cid){
        this.ip = ipAdress;
        this.choixList.add(Choix.getChoix(cid));
    }

    public static boolean exist(String ipAdress){
        return find.where().eq("ip", ipAdress).findRowCount() == 1 ;
    }

    public void addChoix(Choix c){
        this.choixList.add(c);
    }

    public static Vote getByIp(String ip){
        return find.where().eq("ip", ip).findUnique();
    }
}
