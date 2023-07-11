package product;

import product.entity.Question;
import product.entity.QuestionMultipleChoice;
import product.entity.QuestionMultipleSelection;
import product.entity.entityEnums.QuestionTypes;

import java.util.function.Supplier;

public class QuestionFactory <T extends Question>{
    private int multipleChoiceQueCount = 0;
    private int multipleSelectionQueCount = 0;

    public T getNewQuestion(Supplier<T> factory) {
        return factory.get();
    }

    public Question getNewQuestion(QuestionTypes _type) {
        Question result;

        switch (_type) {
            case MultipleChoice -> {
                multipleChoiceQueCount++;
                result = (T) new QuestionMultipleChoice();
            }
            case MultipleSelection -> {
                multipleSelectionQueCount++;
                result = (T) new QuestionMultipleSelection();
            }
            default -> {
                throw new IllegalArgumentException("Wrong question type: " + _type);
            }
        }
        return result;
    }
}
