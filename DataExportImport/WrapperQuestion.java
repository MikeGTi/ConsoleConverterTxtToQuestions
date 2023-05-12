package DataExportImport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class WrapperQuestion {

    private final ArrayList<? extends Question> questions;

    private String wrappedQuestions = "";

    public WrapperQuestion(ArrayList<? extends Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return wrappedQuestions;
    }

    public void wrapHTML(String _title){
        StringBuilder  stringBuilder = new StringBuilder();
        for (Question que: questions) {
            stringBuilder.append(wrapQuestionAsHTML(que));
        }
        wrappedQuestions = wrapTxtAsHTML(_title, stringBuilder.toString());
    }

    private String wrapTxtAsHTML(String _title, String _txt){
        StringBuilder stringBuilder = new StringBuilder();
        //.append( "<h1>List of questions</h1>\n" );
        //.append( "<ul>\n" );
        //.append( "<div>" + _txt + "</div>\n" )
        stringBuilder
                .append("<!doctype html>\n")
                .append("<html lang='ru'>\n")
                .append("<head>\n")
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n")
                .append("<title>")
                .append(_title)
                .append("</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append(_txt)
                .append("\n")
                .append( "</body>\n" )
                .append( "</html>" );

        return stringBuilder.toString();
    }

    private String wrapQuestionAsHTML(Question question){
        //need to be recoded
        String element = "radio";
        if (Objects.equals(question.getType(), "Multiple Selection")) element = "checkbox";

        StringBuilder  stringBuilder = new StringBuilder();
        stringBuilder
                .append("<table border=\"0\" cellpadding=\"5\">\n")
                .append("<tr>\n")
                .append("<td valign=\"top\">").append("<b>" + question.getNumber() + ".</b>").append("</td>\n")
                .append("<td valign=\"top\">\n")
                .append("<div><span style=\"font-family: Calibri; font-size: 11pt;\">" + question.getStem() + "</div>\n")
                .append(getHtmlAnswers(question.getAnswers(), question.getRightAnswers(), Integer.toString(question.getNumber())).replaceAll("dummyElementType",element))
                //.append("<div><span style=\"font-family: Calibri; font-size: 11pt;\">Difficulty: " + question.getDifficulty() + "</div>\n")
                .append("</td>\n")
                .append("</tr>\n")
                .append("</table>\n");

        return stringBuilder.toString();
    }

    private String getHtmlAnswers(HashMap<String, String> answers, HashMap<String, String> rightAnswers, String questionNumber){
        StringBuilder  stringBuilder = new StringBuilder();
        for(HashMap.Entry<String, String> entry : answers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String isRightAnswer = rightAnswers.containsKey(key) ? "1" : "0";

            stringBuilder
                .append("<table border=\"0\">\n")
                .append("<tr>\n")
                .append("<td valign=\"top\" nowrap=\"nowrap\"><input type=\"dummyElementType\" name=\"Ans" + questionNumber + "\" value=\"" + isRightAnswer + "\" /><b>"+ key +".</b></td>\n")
                .append("<td><div><span style=\"font-family: Calibri; font-size: 11pt;\">"+ value +"</span><br /></div></td>\n")
                .append("</tr>\n")
                .append("</table>\n");
        }
        return stringBuilder.toString();
    }
}
