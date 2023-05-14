package BusinessLayer;

import java.io.Serial;
import java.util.HashMap;

public class QuestionMultipleChoice extends Question {
    @Serial
    private static final long serialVersionUID = 6434123350448509776L;

    QuestionMultipleChoice() {
        super( QuestionTypes.MultipleChoice );
    }

    QuestionMultipleChoice(int _queNumber, String _stem, HashMap<String, String> _answers, Character[] _rightAnswers, BusinessLayer.Difficulty _difficulty) {
        super(QuestionTypes.MultipleChoice, _queNumber, _stem, _answers, _rightAnswers, _difficulty);
    }

    /*@Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n" + "Question type: " + super.getType().toString() + "\n")
                .append("Question title: " + super.getTitle() + "\n")
                .append("Question uuid: " + super.getUUID() + "\n")
                .append(super.getNumber() + ". " +  super.getStem() + "\n")
                .append(super.getAnswersString())
                .append("Answer: " + super.getRightAnswersString() + "\n")
                .append("Difficulty: " + super.getDifficulty());
        return stringBuilder.toString();
    }*/

}
