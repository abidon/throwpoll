import com.avaje.ebean.Ebean;
import models.Choix;
import models.Question;
import play.Application;
import play.GlobalSettings;

/**
 * Created by Timote on 19/12/14.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app){
        if(Question.find.findRowCount() > 0){
            Ebean.delete(Question.find.all());
        }
        if(Choix.find.findRowCount() > 0){
            Ebean.delete(Choix.find.all());
        }
    }
}
