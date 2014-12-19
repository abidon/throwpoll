package controllers;

import models.Question;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
    public static Result saveNewPoll() {
    	return ok();
    }
    
    public static Result showPollCreator() {
        return ok();
    }
    
    public static Result showVoteInterface(String qid) {
    	Question q = Question.find.byId(Long.valueOf(qid));
    	if (q == null)
    	{
    		flash("error", "La question demandée n'existe pas");
    	}
    	return ok(views.html.vote.render(q));
    }
    
    public static Result voteForQuestion(String qid) {
    	return ok();
    }
}
