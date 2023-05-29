package Product;

import Product.HtmlSerializer.HTMLelement;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public abstract class Question implements Serializable, HtmlWrappable {
    @Serial
    private static final long serialVersionUID = -5925620994711686560L;

    static int counter = 1;

    private String QueUUID = UUID.randomUUID().toString();

    private final QuestionTypes QueType;

    @HTMLelement(key = "Question number")
    private int QueNumber;

    private String QueTitle = "";

    @HTMLelement(key = "Question stem")
    private String Stem = "";

    @HTMLelement(key = "Question answers")
    private HashMap<String, String> Answers;

    @HTMLelement(key = "Question right answers")
    private Character[] RightAnswers;

    @HTMLelement(key = "Question difficulty")
    private Product.Difficulty Difficulty;

    Question(QuestionTypes _queType){
        QueNumber = counter++;
        this.QueType = _queType;
    }

    Question(QuestionTypes _queType, int _queNumber, String _stem, HashMap<String, String> _answers, Character[] _rightAnswers, Difficulty _difficulty){
        QueNumber = counter++;
        this.QueType = _queType;
        this.QueNumber = _queNumber;
        this.Stem = _stem.trim();
        this.Answers = _answers;
        this.RightAnswers = _rightAnswers;
        this.Difficulty = _difficulty;
    }

    public String getUUID() { return QueUUID; }

    public void setUUID(String _questionUUID) {
        if (!(_questionUUID.trim()).isEmpty())  QueUUID = _questionUUID;
    }

    public QuestionTypes getType() { return QueType; }

    public int getNumber() {  return QueNumber; }

    public void setNumber(int _queNumber) { QueNumber = _queNumber; }

    public String getTitle() {
        /*String res = "";
        if (res.isEmpty()) {
            res = res.substring(0, res.indexOf(' ')) + "...";
        }*/
        return QueTitle.trim();
    }

    public void setTitle(String _queTitle) { QueTitle = _queTitle; }

    public String getStem() {
        return Stem;
    }

    public void setStem(String _stem) {
        Stem = _stem.trim();
    }

    public HashMap<String,String> getAnswers() {
        return Answers;
    }

    public String getAnswersString() {
        return this.GetHashMapKeysValues(this.Answers);
    }

    public String getRightAnswersString() {
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = ", ";
        for (int i = 0; i < this.RightAnswers.length; i++) {
            if (i == this.RightAnswers.length - 1) delimiter = ".";
            if (!this.RightAnswers[i].toString().isEmpty()) {
                stringBuilder.append(this.RightAnswers[i]).append(delimiter);
            }
        }
        return stringBuilder.toString();
    }

    public void setAnswers(HashMap<String, String> _answers) {
        Answers = _answers;
    }

    public Character[] getRightAnswers() {
        return RightAnswers;
    }

    public void setRightAnswers(Character[] _rightAnswers) {
        RightAnswers = _rightAnswers;
    }

    public String getDifficulty() {
        return this.Difficulty.toString();
    }

    public void setDifficulty(Product.Difficulty _difficulty) {
        Difficulty = _difficulty;
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append("\n" + "Question type: " + this.QueType.toString() + "\n")
            .append("Question title: " + this.getTitle() + "\n")
            .append("Question uuid: " + this.QueUUID + "\n")
            .append(this.QueNumber + ". " +  this.Stem + "\n")
            .append(this.getAnswersString())
            .append("Answer: " + this.getRightAnswersString() + "\n")
            .append("Difficulty: " + this.getDifficulty());
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question question)) return false;
        return QueType == question.QueType && Objects.equals(getStem(), question.getStem()) && Objects.equals(getAnswers(), question.getAnswers()) && Objects.equals(getRightAnswers(), question.getRightAnswers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(QueType, getStem(), getAnswers(), getRightAnswers());
    }

    @Override
    public HashMap<String, String> getHtmlTagFieldMap(){
        HashMap<String, String> htmlTagsMap = new HashMap<>();

        htmlTagsMap.put("Question number","<table border=\"0\" cellpadding=\"5\">\n" + "<tr>\n" + "<td valign=\"top\">" + "<b>" + "dummyValue" + ".</b>" + "</td>\n");
        htmlTagsMap.put("Question stem","<td valign=\"top\">\n" + "<div><span style=\"font-family: Calibri; font-size: 11pt;\">" + "dummyValue" + "</div>\n");

        return htmlTagsMap;
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
                .append(getHtmlAnswers(this.getAnswers(), this.getRightAnswers(), Integer.toString(this.getNumber())).replaceAll("dummyElementType", "radio"))
                //.append("<div><span style=\"font-family: Calibri; font-size: 11pt;\">Difficulty: " + question.getDifficulty() + "</div>\n")
                .append("</td>\n")
                .append("</tr>\n")
                .append("</table>\n");

        return stringBuilder.toString();
    }

    @HTMLelement(key = "Answers")
    public String getHtmlAnswers(HashMap<String, String> answers, Character[] rightAnswers, String questionNumber){
        StringBuilder  stringBuilder = new StringBuilder();
        for(HashMap.Entry<String, String> entry : answers.entrySet()) {
            stringBuilder
                    .append("<table border=\"0\">\n")
                    .append("<tr>\n")
                    .append("<td valign=\"top\" nowrap=\"nowrap\"><input type=\"dummyElementType\" name=\"Ans").append(questionNumber).append("\" value=\"").append(isRightAnswer(answers, rightAnswers)).append("\" />")
                    .append("<b>"+ entry.getKey() +".</b></td>\n")
                    .append("<td><div><span style=\"font-family: Calibri; font-size: 11pt;\">"+ entry.getValue() +"</span><br /></div></td>\n")
                    .append("</tr>\n")
                    .append("</table>\n");
        }
        return stringBuilder.toString();
    }

    private String isRightAnswer(HashMap<String, String> answers, Character[] rightAnswers){
        for (Character chr: rightAnswers) {
            if (answers.containsKey(chr.toString())) return "1";
        }
        return "0";
    }

    private String GetHashMapKeysValues(HashMap<String, String> _answers){
        StringBuilder stringBuilder = new StringBuilder();

        for (HashMap.Entry<String, String> entry : _answers.entrySet()) {
            if (_answers.size() > 1) {
                stringBuilder
                        .append(entry.getKey().trim())
                        .append(". ")
                        .append(entry.getValue().trim())
                        .append("\n");
            } else {
                stringBuilder
                        .append(entry.getKey().trim())
                        .append(".")
                        .append("\n");
            }
        }
        return stringBuilder.toString();
    }
}