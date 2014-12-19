package controllers;

import models.Question;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
    private static final Form<Question> questionForm = Form.form(Question.class);

    public static Result saveNewPoll() {
        Form<Question> boundForm = questionForm.bindFromRequest();

        String choixString = boundForm.data().get("choix");
        String[] choixList = choixString.split(System.getProperty("line.separator"));

        Question q = boundForm.get();
        return redirect(showVoteInterface(q.find.getId()));
    }
    
    public static Result showPollCreator() {

        return ok(index.render(questionForm)) ;
    }
    
    public static Result showVoteInterface(String qid) {
    	return ok();
    }
    
    public static Result voteForQuestion(String qid) {
    	return ok();
    }
}
