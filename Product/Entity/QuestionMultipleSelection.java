package Product.Entity;

import Product.Entity.Enums.QuestionTypes;

import java.io.Serial;
import java.util.HashMap;

public class QuestionMultipleSelection extends Question {
    @Serial
    private static final long serialVersionUID = 3439308714477920320L;

    public QuestionMultipleSelection() {
        super( QuestionTypes.MultipleSelection );
    }

    QuestionMultipleSelection(int _queNumber, String _stem, HashMap<String, String> _answers, Character[] _rightAnswers, Product.Entity.Enums.Difficulty _difficulty) {
        super(QuestionTypes.MultipleChoice, _queNumber, _stem, _answers, _rightAnswers, _difficulty);
    }

    @Override
    public String toHTML() {
        return super.toHTML().replaceAll("dummyElementType", "checkbox");
    }
}
