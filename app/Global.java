
import com.avaje.ebean.Ebean;
import models.Choix;
import models.Question;
import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		if (Question.find.findRowCount() > 0) {
			for(Question question: Question.find.all()){
				question.delete();
			}
		}
	}
}