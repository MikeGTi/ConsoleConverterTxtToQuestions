package Product.HtmlWrap;

import Product.Entity.Question;

import java.util.ArrayList;


public class QuestionsWrapper {

    private int questionsCount;
    private int answersCount;

    private final ArrayList<? extends Question> questions;

    private String wrappedQuestions = "";

    public QuestionsWrapper(ArrayList<? extends Question> questions) {
        this.questions = questions;
        this.questionsCount = questions.size();
        this.answersCount = questions.stream().reduce(0, (integer, question) -> integer + (question.getAnswers()).size(), Integer::sum);
        //System.out.println(answersCount);
    }

    @Override
    public String toString() {
        return wrappedQuestions;
    }

    public void toHTML(String _title, boolean addGradeScripts){
        StringBuilder  stringBuilder = new StringBuilder();
        for (Question que: questions) {
            stringBuilder.append(que.toHTML());
        }
        wrappedQuestions = wrapTxtAsHTML(_title, stringBuilder.toString(), addGradeScripts);
    }

    private String wrapTxtAsHTML(String _title, String _txt, boolean addGradeScripts){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("<!doctype html>\n")
                .append("<html lang='ru'>\n")
                .append("<head>\n")
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n")
                .append("<title>").append(_title).append("</title>\n")
                .append("</head>\n")
                .append("<body>\n").append(_txt).append("\n").append( "</body>\n" )
                .append( "</html>" );

        if (addGradeScripts){
            return insertScripts(wrapForGrade(stringBuilder.toString()));
        } else {
            return stringBuilder.toString();
        }
    }

    private String insertScripts(String html){
        String tag = "\n</body>";
        return html.replaceFirst(tag,"\n" + getScripts() + tag);
    }

    private String wrapForGrade(String html){
        String firstTag = "<body bgcolor=\"#FFFFFF\">\n" +
                          "   <form method=\"get\">\n" +
                          "   <div>Name: __________________________  Date: _____________</div>\n" +
                          "   <input type=\"hidden\" name=\"Hd1\" /> <br />\n" +
                          "   <br /><br />\n";

        String lastTag  = "\n" +
                          "    <br /><br />\n" +
                          "    <hr size=\"1\" color=\"silver\" />\n" +
                          "    <p><img src=\"\\test1_files/stop.gif\" width=\"30\" height=\"30\" alt=\"STOP\" /> This is the end of the test.  When you have completed all the questions and reviewed your answers, press the button below to grade the test.</p>\n" +
                          "    <p><input type=\"submit\" value=\"Grade the Test\" onclick=\"Grade(this.form)\" /></p>\n" +
                          "    </form>\n" +
                          "</body>";

        html = html.replaceFirst("<body>", firstTag);
        html = html.replaceFirst("</body>", lastTag);

        return html;
    }

    private String getScripts(){
        return "<script src=\"scripts/gradeWOanalitics.js\"></script>";
    }
}
