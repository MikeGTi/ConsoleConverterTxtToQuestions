package Product.Entity.Enums;

public enum QuestionPart {
    QUESTION_TYPE("Type"),
    QUESTION_NUMBER("Number"),
    QUESTION_STEM("Stem"),
    QUESTION_ANSWERS("Answers"),
    QUESTION_ANSWER_ONE("Answer"),
    QUESTION_RIGHT_ANSWERS("Right answers"),
    QUESTION_DIFFICULTY("Difficulty");

    private final String name;

    private QuestionPart(String _value) {
        this.name = _value;
    }

    @Override
    public String toString(){
        return name;
    }

}