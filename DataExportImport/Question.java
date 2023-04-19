package DataExportImport;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

enum Difficulty
    {
        EASY("Easy"),MIDDLE("Middle"),HARD("Hard");
        private final String name;
        Difficulty(String _value){ this.name = _value; }

        @Override
        public String toString() { return name; }
    }

    enum QuestionType
    {
        MultipleChoice,
        MultipleSelection,
        TrueFalse,
        TextEntry,
        TextEntryWithDropdown,
        Essay
    }

public class Question implements Serializable {
    @Serial
    private static final long serialVersionUID = -5925620994711686560L;
    static int counter = 0;
    private int QuestionID;
    private QuestionType QueType;
    private int QueNumber;
    private String QueTitle;

    private String Stem;

    private HashMap<String, String> Answers;
    private HashMap<String, String> RightAnswers;
    private Difficulty Difficulty;

    Question(QuestionType _queType, int _queNumber, String _queTitle, String _stem, HashMap<String, String> _answers, HashMap<String, String> _rightAnswers, Difficulty _difficulty){
        QuestionID = counter++;
        this.QueType = _queType;
        this.QueNumber = _queNumber;
        this.QueTitle = _queTitle;
        this.Stem = _stem;
        this.Answers = _answers;
        this.RightAnswers = _rightAnswers;
        this.Difficulty = _difficulty;
    }

    Question(QuestionType _queType, int _queID, String _stem, HashMap<String, String> _answers,HashMap<String, String> _rightAnswers, Difficulty _difficulty){
        QuestionID = counter++;
        this.QueType = _queType;
        this.QueNumber = _queID;
        this.Stem = _stem;
        this.Answers = _answers;
        this.RightAnswers = _rightAnswers;
        this.Difficulty = _difficulty;
    }
    Question(int _queNumber, String _stem, HashMap<String, String> _answers, HashMap<String, String> _rightAnswers){
        //minimal init fields requirements
        QuestionID = counter++;
        this.QueType = QuestionType.MultipleChoice;
        this.QueNumber = _queNumber;
        this.Stem = _stem;
        this.Answers = _answers;
        this.RightAnswers = _rightAnswers;
        this.Difficulty = DataExportImport.Difficulty.EASY;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public QuestionType getQueType() {
        return QueType;
    }

    public int getQueNumber() {
        return QueNumber;
    }

    public String getQueTitle() {
        return QueTitle;
    }

    public String getStem() {
        return Stem;
    }

    public HashMap<String, String> getAnswers() {
        return Answers;
    }

    public HashMap<String, String> getRightAnswers() {
        return RightAnswers;
    }

    public DataExportImport.Difficulty getDifficulty() {
        return Difficulty;
    }

    @Override
    public String toString(){
        return "\nType: " + this.QueType + "\n" +
               "Number: " + this.QueNumber + ". \n" +
               "Stem: " +  this.Stem  +
                this.GetHashMapKeysValues(this.Answers) +
               "Answer: " + this.GetHashMapKeysValues(this.RightAnswers) +
               "Difficulty: " + this.GetDifficulty(this.Difficulty);
    }

    private String GetHashMapKeysValues(HashMap<String, String> _answers){
        StringBuilder strBuilder = new StringBuilder();
        for (HashMap.Entry<String, String> entry : _answers.entrySet()) {
            strBuilder.append(entry.getKey()).append(". ").append(entry.getValue()).append("\n");
        }
        return strBuilder.toString();
    }

    private String GetDifficulty(DataExportImport.Difficulty difficulty) {
        return this.Difficulty.toString();
    }
}
