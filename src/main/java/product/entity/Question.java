package product.entity;

import product.htmlSerializer.HTMLelement;
import product.htmlWrap.HtmlWrappable;
import product.entity.entityEnums.QuestionTypes;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public abstract class Question implements Serializable, HtmlWrappable {
    @Serial
    private static final long serialVersionUID = -5925620994711686560L;

    private String uuid = UUID.randomUUID().toString();

    private final QuestionTypes type;

    @HTMLelement
    private int number;

    private String title;

    @HTMLelement
    private String stem;

    @HTMLelement
    private HashMap<String, String> answers;

    @HTMLelement
    private Character[] rightAnswers;

    private product.entity.entityEnums.Difficulty difficulty;

    Question(QuestionTypes queType){
        this.type = queType;
    }

    Question(QuestionTypes queType, int queNumber, String queTitle, String stem, HashMap<String, String> answers, Character[] rightAnswers, product.entity.entityEnums.Difficulty difficulty){
        this.type = queType;
        this.number = queNumber;
        this.title = queTitle;
        this.stem = stem.trim();
        this.answers = answers;
        this.rightAnswers = rightAnswers;
        this.difficulty = difficulty;
    }

    public String getUUID() { return uuid; }

    public Question setUUID(String questionUUID) {
        if (!(questionUUID.trim()).isEmpty())  {
            uuid = questionUUID;
        }
        return this;
    }

    public QuestionTypes getType() { return type; }

    public int getNumber() {  return number; }

    public Question setNumber(int number) {
        this.number = number;
        return this;
    }

    public String getTitle() {
        /*String res = "";
        if (res.isEmpty()) {
            res = res.substring(0, res.indexOf(' ')) + "...";
        }*/
        return this.title;
    }

    public Question setTitle(String title) {
        this.title = title.trim();
        return this;
    }

    public String getStem() {
        return stem;
    }

    public Question setStem(String _stem) {
        stem = _stem.trim();
        return this;
    }

    public HashMap<String,String> getAnswers() {
        return answers;
    }

    public String getAnswersString() {
        return this.getHashMapKeysValues(this.answers);
    }

    public String getRightAnswersString() {
        if (this.rightAnswers == null) return "";
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = ", ";
        for (int i = 0; i < this.rightAnswers.length; i++) {
            if (i == this.rightAnswers.length - 1) delimiter = ".";
            if (!this.rightAnswers[i].toString().isEmpty()) {
                stringBuilder.append(this.rightAnswers[i].toString().trim()).append(delimiter);
            }
        }
        return stringBuilder.toString();
    }

    public Question setAnswers(HashMap<String, String> _answers) {
        answers = _answers;
        return this;
    }

    public Character[] getRightAnswers() {
        return rightAnswers;
    }

    public Question setRightAnswers(Character[] _rightAnswers) {
        rightAnswers = _rightAnswers;
        //System.out.println(rightAnswers[0]);
        return this;
    }

    public String getDifficulty() {
        if (this.difficulty == null) return "";
        return this.difficulty.toString();
    }

    public Question setDifficulty(product.entity.entityEnums.Difficulty _difficulty) {
        difficulty = _difficulty;
        return this;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append("\n")
            .append("Question type: " + this.type.toString() + "\n")
            .append("Question title: " + this.getTitle() + "\n")
            .append("Question uuid: " + this.uuid + "\n")
            .append(this.number + ". " +  this.stem + "\n")
            .append(this.getAnswersString())
            .append("Answer: " + this.getRightAnswersString() + "\n")
            .append("difficulty: " + this.getDifficulty());
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question question)) return false;
        return type == question.type && Objects.equals(getStem(), question.getStem()) && Objects.equals(getAnswers(), question.getAnswers()) && Objects.equals(getRightAnswers(), question.getRightAnswers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, getStem(), getAnswers(), getRightAnswers());
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
                .append("<div class=\"container\" name=\"QueBody\">\n")
                .append("    <div class=\"Question\">\n")
                .append("        <table border=\"0\" cellpadding=\"5\">\n")
                .append("            <tr>\n")
                .append("                <td valign=\"top\">")
                .append("    <div class=\"number\"><b>" + number + ". </b></div>")
                .append("                </td>\n")
                .append("                <td valign=\"top\">\n")
                .append("                    <div class=\"QueStem\"><span style=\"font-family: Calibri; font-size: 11pt;\">" + this.getStem() + "</div>\n")
                .append("                    <div class=\"QueAnswers\">\n")
                .append(getTableHtmlAnswers())
                .append("                    </div>\n")
                //.append("                  <div class=\"QueDifficulty\"><span style=\"font-family: Calibri; font-size: 11pt;\">difficulty: " + question.getDifficulty() + "</div>\n")
                .append("                </td>\n")
                .append("            </tr>\n")
                .append("        </table>\n")
                .append("    </div>\n")
                .append("</div>\n");
        return stringBuilder.toString();
    }

    @HTMLelement(key = "answers")
    private String getTableHtmlAnswers(){
        StringBuilder  stringBuilder = new StringBuilder();
        for(HashMap.Entry<String, String> entry : answers.entrySet()) {
            stringBuilder
                .append("                        <table border=\"0\">\n")
                .append("                            <tr>\n")
                .append("                                <td valign=\"top\">")
                .append("   <input type=\"dummyElementType\" class=\"QueAnswerElement\" value=\"").append(isRightAnswer(entry.getKey(), rightAnswers)).append("\" />")
                .append("   </td>\n")
                .append("                                <td valign=\"top\">")
                .append("   <div class=\"QueAnswerChar\"><b>"+ entry.getKey() +". </b></div>")
                .append("   </td>\n")
                .append("                                <td valign=\"top\">")
                .append("   <div class=\"QueAnswerStem\"><span style=\"font-family: Calibri; font-size: 11pt;\">"+ entry.getValue() +"</span><br /></div>")
                .append("   </td>\n")
                .append("                            </tr>\n")
                .append("                        </table>\n");
        }
        return stringBuilder.toString();
    }

    private String isRightAnswer(String curAnswerChar, Character[] rightAnswers){
        for (Character chr: rightAnswers) {
            if (curAnswerChar.equals(chr.toString())) return "1";
        }
        return "0";
    }

    private String getHashMapKeysValues(HashMap<String, String> _answers){
        if (_answers == null) return "";
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