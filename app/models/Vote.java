package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Timote on 19/12/14.
 */
@Entity
public class Vote extends Model{

    @Id
    private String ip;

    @ManyToMany
    private List<Choix> choixList;
}
