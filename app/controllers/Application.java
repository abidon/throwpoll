package controllers;

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
    	return ok();
    }
    
    public static Result voteForQuestion(String qid) {
    	return ok();
    }
}
