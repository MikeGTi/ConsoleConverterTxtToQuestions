package BusinessLayer;

import java.io.Serial;
import java.util.HashMap;

public class QuestionMultipleSelection extends Question {
    @Serial
    private static final long serialVersionUID = 3439308714477920320L;

    QuestionMultipleSelection() {
        super( QuestionTypes.MultipleSelection );
    }

    QuestionMultipleSelection(int _queNumber, String _stem, HashMap<String, String> _answers, Character[] _rightAnswers, BusinessLayer.Difficulty _difficulty) {
        super(QuestionTypes.MultipleChoice, _queNumber, _stem, _answers, _rightAnswers, _difficulty);
    }

}
