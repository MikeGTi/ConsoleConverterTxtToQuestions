package BusinessLayer;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public abstract class Question implements Serializable {
    @Serial
    private static final long serialVersionUID = -5925620994711686560L;
    static int counter = 1;
    private String QueUUID = UUID.randomUUID().toString();
    private final QuestionTypes QueType;
    private int QueNumber;
    private String QueTitle = "";
    private String Stem = "";
    private HashMap<String, String> Answers;
    private Character[] RightAnswers;
    private BusinessLayer.Difficulty Difficulty;

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

    public HashMap<String, String> getAnswers() {
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

    public void setDifficulty(BusinessLayer.Difficulty _difficulty) {
        Difficulty = _difficulty;
    }

    public String fieldsToString() {
        StringBuilder stringBuilder = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        stringBuilder.append( this.getClass().getName() );
        stringBuilder.append( " Object {" );
        stringBuilder.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Class<?> current = this.getClass();
        /*while(current.getSuperclass()!=null){ // we don't want to process Object.class
            // do something with current's fields
            current = current.getSuperclass();
        }*/
        Field[] fields = current.getClass().getDeclaredFields();
        //Field[] fields = this.getClass().getDeclaredFields();
        //Field[] fields = this.getClass().getFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            stringBuilder.append("  ");
            try {
                //field.setAccessible(true);
                stringBuilder.append( field.getName() );
                stringBuilder.append(": ");
                //requires access to private field:
                stringBuilder.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            stringBuilder.append(newLine);
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n" + "Question type: " + this.QueType.toString() + "\n")
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

    private String GetHashMapKeysValues(HashMap<String, String> _answers){
        StringBuilder stringBuilder = new StringBuilder();

        for (HashMap.Entry<String, String> entry : _answers.entrySet()) {
            if (_answers.size() > 1) {
                stringBuilder
                        .append(entry.getKey().trim())
                        .append(". ")
                        .append(entry.getValue().trim())
                        .append("\n");
            } else{
                stringBuilder
                        .append(entry.getKey().trim())
                        .append(".")
                        .append("\n");
            }

        }
        return stringBuilder.toString();
    }

}