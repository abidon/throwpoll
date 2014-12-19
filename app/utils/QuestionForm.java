package utils;

import models.Choix;
import models.Question;
import play.data.validation.ValidationError;
import play.mvc.Http;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timote on 19/12/14.
 */
public class QuestionForm {

    public String name;

    public String choix;

    public boolean multiple;

    // Required for form instantiation
    public QuestionForm(){
    }


    public List<ValidationError> validate(){
        List<ValidationError> errors = new ArrayList<>();
        List<Choix> createdChoix = new ArrayList<>();

        if (name == null || name.length() == 0){
            errors.add(new ValidationError("name", "No name was given"));
        }

        // string to string[]
        String[] cList = choix.split(System.getProperty("line.separator"));
        // trim line to remove empty ones
        List<String> cListTrimmed = new ArrayList<String>();
        for(String s : cList){
            if(!s.trim().isEmpty()){
                cListTrimmed.add(s.trim());
            }
        }
        // from string to choix
        for(String s : cListTrimmed){
            Choix c = new Choix(s);
            createdChoix.add(c);
        }


        if (errors.size() > 0){
            return errors;
        }
        else{
            Question q = new Question(name, multiple);
            q.save();
            for(Choix c : createdChoix){
                c.setQuestion(q);
                c.save();
            }
            Http.Context.current().args.put("qid", q.id);
        }

        return null;
    }

}
