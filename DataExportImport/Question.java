package DataExportImport;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

enum Difficulty
{
    EASY("Easy"),MIDDLE("Middle"),HARD("Hard");
    private final String name;
    Difficulty(String _value){
        this.name = _value;
    }

    @Override
    public String toString() { return name; }
}

public abstract class Question implements Serializable {
    @Serial
    private static final long serialVersionUID = -5925620994711686560L;
    static int counter = 1;
    private String QueUUID = UUID.randomUUID().toString();
    private QuestionTypes QueType;
    private int QueNumber;
    private String QueTitle;
    private String Stem;
    private HashMap<String, String> Answers;
    private HashMap<String, String> RightAnswers;
    private Difficulty Difficulty;

    Question(QuestionTypes _queType){
        QueNumber = counter++;
        this.QueType = _queType;
    }

    Question(QuestionTypes _queType, int _queNumber, String _stem, HashMap<String, String> _answers, HashMap<String, String> _rightAnswers, Difficulty _difficulty){
        QueNumber = counter++;
        this.QueType = _queType;
        this.QueNumber = _queNumber;
        this.Stem = _stem;
        this.Answers = _answers;
        this.RightAnswers = _rightAnswers;
        this.Difficulty = _difficulty;
    }

    public String getUUID() {
        return QueUUID;
    }

    public void setUUID(String _questionUUID) {
        if (!(_questionUUID.trim()).isEmpty())  QueUUID = _questionUUID; ;
    }

    public String getType() { return QueType.toString(); }

    public int getNumber() {  return QueNumber; }

    public void setNumber(int _queNumber) { QueNumber = _queNumber; }

    public String getTitle() {
        String res = QueTitle.trim();
        if (res.isEmpty()) {
            /*res = Stem.trim();
            res = res.substring(0, res.indexOf(' ')) + "...";*/
        }
        return res;
    }

    public void setTitle(String _queTitle) { QueTitle = _queTitle; }

    public String getStem() {
        return Stem;
    }

    public void setStem(String _stem) {
        Stem = _stem;
    }

    public HashMap<String, String> getAnswers() {
        return Answers;
    }

    public void setAnswers(HashMap<String, String> _answers) {
        Answers = _answers;
    }

    public HashMap<String, String> getRightAnswers() {
        return RightAnswers;
    }

    public void setRightAnswers(HashMap<String, String> _rightAnswers) {
        RightAnswers = _rightAnswers;
    }

    public String getDifficulty() {
        return this.Difficulty.toString();
    }

    public void setDifficulty(DataExportImport.Difficulty _difficulty) {
        Difficulty = _difficulty;
    }

    @Override
    public String toString(){
        StringBuilder  stringBuilder = new StringBuilder();
        stringBuilder.append("\n" + "Question type: " + this.QueType.toString() + "\n")
                     .append("Question title: " + this.getTitle() + "\n")
                     .append("Question uuid: " + this.QueUUID + "\n")
                     .append(this.QueNumber + ". " +  this.Stem  + "\n")
                     .append(this.GetHashMapKeysValues(this.Answers))
                     .append("Answer: " + this.GetHashMapKeysValues(this.RightAnswers))
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

    private String GetHashMapKeysValues(HashMap<String, String> _answers){
        StringBuilder strBuilder = new StringBuilder();
        for (HashMap.Entry<String, String> entry : _answers.entrySet()) {
            strBuilder.append(entry.getKey()).append(". ").append(entry.getValue()).append("\n");
        }
        return strBuilder.toString();
    }

}