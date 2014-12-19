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
        System.out.println(q);
        return showVoteInterface(0L);
    }
    
    public static Result showPollCreator() {
        return ok(index.render(questionForm)) ;
    }
    
    public static Result showVoteInterface(Long qid) {
    	return ok();
    }
    
    public static Result voteForQuestion(Long qid) {
    	return ok();
    }

}


