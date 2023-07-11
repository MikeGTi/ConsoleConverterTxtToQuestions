import product.entity.entityEnums.QuestionPart;
import product.entity.Question;
import product.QuestionParser;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Optional;

public class QuestionTest {

    private QuestionDataForTesting questionData;

    @BeforeAll
    private void initTestData(){
        this.questionData = new QuestionDataForTesting();
    }

    @Test
    public void QuestionParserTest(){
        initTestData();
        shouldReturnCheckQuestionParserOnBrokenQuestionText();
    }

    @Test
    public void QuestionWrapperTest(){
        Assertions.assertTrue(true);
    }

    private void shouldReturnCheckQuestionParserOnBrokenQuestionText() {
        String txt;
        QuestionParser questionParser;
        Optional<ArrayList<Question>> questions;
        Question question;

        for (QuestionPart quePart: QuestionPart.values()) {
            txt = questionData.getBrokenQuestionString(quePart);
            questionParser = new QuestionParser(txt);
            questions = questionParser.getList();

            Assertions.assertNotEquals(questions,Optional.empty());
            Assertions.assertEquals(questions.get().size(), 1);

            for (int i = 0; i < questions.get().size(); i++) {
                question = questions.get().get(i);
                for (Field fl: question.getClass().getDeclaredFields()) {
                    Assertions.assertNotNull(fl);
                }
            }
        }
    }
}

