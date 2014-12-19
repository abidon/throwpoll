package controllers;

import models.Choix;
import models.Question;
import play.*;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.*;
import assets.QuestionForm;

import views.html.*;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {
    private static final Form<QuestionForm> questionForm = Form.form(QuestionForm.class);

    public static Result saveNewPoll() {
        Form<QuestionForm> boundForm = questionForm.bindFromRequest();
        System.out.println(boundForm);
        QuestionForm q = boundForm.get();
        // get last question id
        Question last = Question.getLast();

        return redirect(routes.Application.showVoteInterface(last.id));
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
    	return ok();
    }

}


