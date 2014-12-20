package controllers;

import models.Choix;
import models.Question;
import models.Vote;
import play.data.Form;
import play.mvc.*;
import utils.QuestionForm;

import views.html.*;

import java.util.Map;

public class Application extends Controller {
    private static final Form<QuestionForm> questionForm = Form.form(QuestionForm.class);

    public static Result saveNewPoll() {
        System.out.println("Save before");
        Form<QuestionForm> boundForm = questionForm.bindFromRequest();
        System.out.println("Save after bind");
        System.out.println(boundForm);
        System.out.println("Save after get()");
        if(boundForm.hasErrors()){
            System.out.println("Json: ");
            System.out.println(boundForm.errorsAsJson());
            return badRequest(index.render(boundForm));
        }
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
            if (Choix.isValidId(Long.valueOf(s))){
                Choix c = Choix.getChoix(Long.valueOf(s));
                if (Vote.exist(request().remoteAddress())){
                    Vote v = Vote.getByIp(request().remoteAddress());
                    v.addChoix(Choix.getChoix(Long.valueOf(s)));
                    c.votes.add(v);
                    v.update();
                } else {
                    Vote v = new Vote(request().remoteAddress(), Long.valueOf(s));
                    c.votes.add(v);
                    v.save();
                }
                c.update();
            }
        }

    	return redirect(routes.Application.showPollResult(qid));
    }

    public static Result showPollResult(Long qid){
        return ok(views.html.result.render(Question.find.byId(qid)));
    }

}


