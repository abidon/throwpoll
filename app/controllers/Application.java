package controllers;

import models.Question;
import play.data.Form;
import play.mvc.*;
import utils.QuestionForm;

import views.html.*;

import java.util.Map;

public class Application extends Controller {
    private static final Form<QuestionForm> questionForm = Form.form(QuestionForm.class);

    public static Result saveNewPoll() {
        Form<QuestionForm> boundForm = questionForm.bindFromRequest();
        System.out.println(boundForm);
        QuestionForm q = boundForm.get();
        // get last question id
        Long qid = (Long) Http.Context.current().args.get("qid");

        return redirect(routes.Application.showVoteInterface(qid));
    }
    
    public static Result showPollCreator() {
        return ok(index.render(questionForm)) ;
    }


    public static Result showVoteInterface(Long qid) {
    	Question q = Question.find.byId(qid);
    	if (q == null)
    	{
    		flash("error", "La question demandée n'existe pas");
    	}
    	return ok(views.html.vote.render(q));

    }
    
    public static Result voteForQuestion(Long qid) {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        for(String s: values.get("choosen")){
            System.out.println(s);
            // validate choix
            // create Vote with ip (request().remoteAdress()) et s (choix.id)
        }

    	return redirect(routes.Application.showPollResult(qid));
    }

    public static Result showPollResult(Long qid){
        return TODO;
    }

}


