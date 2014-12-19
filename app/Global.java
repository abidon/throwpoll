import java.util.ArrayList;
import java.util.List;

import models.Choix;
import models.Question;

import com.avaje.ebean.Ebean;

public class Global extends play.GlobalSettings {
	@Override
	public void onStart(play.Application arg0)
	{
		List<Choix> choix = new ArrayList<Choix>();
		choix.add(new Choix("Oui"));
		choix.add(new Choix("Non"));
		choix.add(new Choix("Obi-Wan Kenobi"));
		choix.add(new Choix("La réponse D"));
		
		Ebean.save(new Question("Wesh maggle! Tranquil bien posé ou quoi ?", choix, false));
	}
}
