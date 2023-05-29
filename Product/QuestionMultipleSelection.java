package Product;

import java.io.Serial;
import java.util.HashMap;

public class QuestionMultipleSelection extends Question {
    @Serial
    private static final long serialVersionUID = 3439308714477920320L;

    QuestionMultipleSelection() {
        super( QuestionTypes.MultipleSelection );
    }

    QuestionMultipleSelection(int _queNumber, String _stem, HashMap<String, String> _answers, Character[] _rightAnswers, Product.Difficulty _difficulty) {
        super(QuestionTypes.MultipleChoice, _queNumber, _stem, _answers, _rightAnswers, _difficulty);
    }

    @Override
    public String toHTML() {
        StringBuilder  stringBuilder = new StringBuilder();
        stringBuilder
                .append("<table border=\"0\" cellpadding=\"5\">\n")
                .append("<tr>\n")
                .append("<td valign=\"top\">").append("<b>" + this.getNumber() + ".</b>").append("</td>\n")
                .append("<td valign=\"top\">\n")
                .append("<div><span style=\"font-family: Calibri; font-size: 11pt;\">" + this.getStem() + "</div>\n")
                .append(super.getHtmlAnswers(this.getAnswers(), this.getRightAnswers(), Integer.toString(this.getNumber())).replaceAll("dummyElementType", "checkbox"))
                //.append("<div><span style=\"font-family: Calibri; font-size: 11pt;\">Difficulty: " + question.getDifficulty() + "</div>\n")
                .append("</td>\n")
                .append("</tr>\n")
                .append("</table>\n");

        return stringBuilder.toString();
    }
}
