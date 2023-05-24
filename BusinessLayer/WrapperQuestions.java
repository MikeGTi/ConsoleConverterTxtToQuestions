package BusinessLayer;

import java.util.ArrayList;


public class WrapperQuestions {

    private final ArrayList<? extends Question> questions;

    private String wrappedQuestions = "";

    public WrapperQuestions(ArrayList<? extends Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return wrappedQuestions;
    }

    public void toHTML(String _title){
        StringBuilder  stringBuilder = new StringBuilder();
        for (Question que: questions) {
            stringBuilder.append(que.toHTML());
        }
        wrappedQuestions = wrapTxtAsHTML(_title, stringBuilder.toString());
    }

    private String wrapTxtAsHTML(String _title, String _txt){
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

        return stringBuilder.toString();
    }

}
