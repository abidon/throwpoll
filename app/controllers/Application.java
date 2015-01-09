package controllers;

import models.Choix;
import models.Question;
import models.Vote;
import play.data.Form;
import play.mvc.*;
import utils.QuestionForm;

import views.html.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application extends Controller {
    private static final Form<QuestionForm> questionForm = Form.form(QuestionForm.class);

    public static Result saveNewPoll() {
        // get form data
        Form<QuestionForm> boundForm = questionForm.bindFromRequest();

        // check for error
        if(boundForm.hasErrors()){
            return badRequest(index.render(boundForm));
        }
        // get last question id
        Long qid = (Long) Http.Context.current().args.get("qid");

        return redirect(controllers.routes.Application.showVoteInterface(qid));
    }
    
    public static Result showPollCreator() {
        return ok(index.render(questionForm)) ;
    }


    public static Result showVoteInterface(Long qid) {
    	Question q = Question.find.byId(qid);
        Question question = Question.find.byId(qid);
        if (question == null){
            return notFound("question not found");
        }

    	return ok(views.html.vote.render(q));

    }
    
    public static Result voteForQuestion(Long qid) {

        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        Question question = Question.find.byId(qid);
        if (question == null){
            return notFound("question not found");
        }

        if(Vote.alreadyVoted(request().remoteAddress(), Question.find.byId(qid))){
            return badRequest("You already voted for this question");
        }

        for(String s: values.get("choosen")){
            if (Choix.isValidId(Long.valueOf(s))){
                Choix c = Choix.find.byId(Long.valueOf(s));
                Vote v = new Vote(request().remoteAddress(), c, Question.find.byId(qid));
                // update choix vote list
                List<Vote> choix_votes = c.getVotes();
                choix_votes.add(v);
                c.setVotes(choix_votes);
                v.save();

                List<Vote> question_votes = question.getVotes();
                question_votes.add(v);
                question.setVotes(question_votes);
                question.update();

            }
        }

    	return redirect(controllers.routes.Application.showPollResult(qid));
    }

    public static Result showPollResult(Long qid){
        Question question = Question.find.byId(qid);
        if (question == null){
            return notFound("question not found");
        }
        return ok(views.html.result.render(Question.find.byId(qid)));
    }

}


