import models.Question;
import org.fest.assertions.Assertions;
import org.junit.Test;


public class ModelTest extends BaseModelTest{

    @Test
    public void saveQuestion() {
        Question question = new Question("Question test ?", false);
        question.save();
        Assertions.assertThat(question.getId()).isNotNull();
    }
}
