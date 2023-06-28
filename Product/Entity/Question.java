package Product.Entity;

import Product.HtmlSerializer.HTMLelement;
import Product.HtmlWrap.HtmlWrappable;
import Product.Entity.Enums.QuestionTypes;

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
    private Product.Entity.Enums.Difficulty Difficulty;

    Question(QuestionTypes _queType){
        QueNumber = counter++;
        this.QueType = _queType;
    }

    Question(QuestionTypes _queType, int _queNumber, String _stem, HashMap<String, String> _answers, Character[] _rightAnswers, Product.Entity.Enums.Difficulty _difficulty){
        QueNumber = counter++;
        this.QueType = _queType;
        this.QueNumber = _queNumber;
        this.Stem = _stem.trim();
        this.Answers = _answers;
        this.RightAnswers = _rightAnswers;
        this.Difficulty = _difficulty;
    }

    public String getUUID() { return QueUUID; }

    public Question setUUID(String _questionUUID) {
        if (!(_questionUUID.trim()).isEmpty())  QueUUID = _questionUUID;
        return this;
    }

    public QuestionTypes getType() { return QueType; }

    public int getNumber() {  return QueNumber; }

    public Question setNumber(int _queNumber) {
        QueNumber = _queNumber;
        return this;
    }

    public String getTitle() {
        /*String res = "";
        if (res.isEmpty()) {
            res = res.substring(0, res.indexOf(' ')) + "...";
        }*/
        return QueTitle.trim();
    }

    public Question setTitle(String _queTitle) {
        QueTitle = _queTitle.trim();
        return this;
    }

    public String getStem() {
        return Stem;
    }

    public Question setStem(String _stem) {
        Stem = _stem.trim();
        return this;
    }

    public HashMap<String,String> getAnswers() {
        return Answers;
    }

    public String getAnswersString() {
        return this.getHashMapKeysValues(this.Answers);
    }

    public String getRightAnswersString() {
        if (this.RightAnswers == null) return "";
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = ", ";
        for (int i = 0; i < this.RightAnswers.length; i++) {
            if (i == this.RightAnswers.length - 1) delimiter = ".";
            if (!this.RightAnswers[i].toString().isEmpty()) {
                stringBuilder.append(this.RightAnswers[i].toString().trim()).append(delimiter);
            }
        }
        return stringBuilder.toString();
    }

    public Question setAnswers(HashMap<String, String> _answers) {
        Answers = _answers;
        return this;
    }

    public Character[] getRightAnswers() {
        return RightAnswers;
    }

    public Question setRightAnswers(Character[] _rightAnswers) {
        RightAnswers = _rightAnswers;
        //System.out.println(RightAnswers[0]);
        return this;
    }

    public String getDifficulty() {
        if (this.Difficulty == null) return "";
        return this.Difficulty.toString();
    }

    public Question setDifficulty(Product.Entity.Enums.Difficulty _difficulty) {
        Difficulty = _difficulty;
        return this;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append("\n")
            .append("Question type: " + this.QueType.toString() + "\n")
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
                .append("<div class=\"container\" name=\"QueBody\">\n")
                .append("    <div class=\"Question\">\n")
                .append("        <table border=\"0\" cellpadding=\"5\">\n")
                .append("            <tr>\n")
                .append("                <td valign=\"top\">")
                .append("    <div class=\"QueNumber\"><b>" + QueNumber + ". </b></div>")
                .append("                </td>\n")
                .append("                <td valign=\"top\">\n")
                .append("                    <div class=\"QueStem\"><span style=\"font-family: Calibri; font-size: 11pt;\">" + this.getStem() + "</div>\n")
                .append("                    <div class=\"QueAnswers\">\n")
                .append(getTableHtmlAnswers())
                .append("                    </div>\n")
                //.append("                  <div class=\"QueDifficulty\"><span style=\"font-family: Calibri; font-size: 11pt;\">Difficulty: " + question.getDifficulty() + "</div>\n")
                .append("                </td>\n")
                .append("            </tr>\n")
                .append("        </table>\n")
                .append("    </div>\n")
                .append("</div>\n");
        return stringBuilder.toString();
    }

    @HTMLelement(key = "Answers")
    private String getTableHtmlAnswers(){
        StringBuilder  stringBuilder = new StringBuilder();
        for(HashMap.Entry<String, String> entry : Answers.entrySet()) {
            stringBuilder
                .append("                        <table border=\"0\">\n")
                .append("                            <tr>\n")
                .append("                                <td valign=\"top\">")
                .append("   <input type=\"dummyElementType\" class=\"QueAnswerElement\" value=\"").append(isRightAnswer(entry.getKey(), RightAnswers)).append("\" />")
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