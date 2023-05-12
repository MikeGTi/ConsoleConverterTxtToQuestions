package DataExportImport;

import java.io.Serial;
import java.util.HashMap;

public class QuestionMultipleChoice extends Question {
    @Serial
    private static final long serialVersionUID = 6434123350448509776L;

    QuestionMultipleChoice() {
        super(QuestionTypes.MultipleChoice);
    }

    QuestionMultipleChoice(int _queNumber, String _stem, HashMap<String, String> _answers, String _rightAnswers, DataExportImport.Difficulty _difficulty) {
        super(QuestionTypes.MultipleChoice, _queNumber, _stem, _answers, new HashMap<String, String>(){{ put(_rightAnswers,""); }}, _difficulty);
    }
}
