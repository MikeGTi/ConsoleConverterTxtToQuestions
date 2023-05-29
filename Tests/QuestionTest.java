package Tests;

import Product.Question;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class QuestionTest {

    private Question question;

    private QuestionDataForTesting questionDataForTesting = new QuestionDataForTesting();

    public <T extends Question> QuestionTest(T question) {
        this.question = question;
    }

    @Test
    public void shouldReturn() {
        Assertions.assertTrue(true);

    }



}

