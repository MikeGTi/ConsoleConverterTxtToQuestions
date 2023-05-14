package BusinessLayer;

public class QuestionFactory {
    private int multipleChoiceQueCount = 0;
    private int multipleSelectionQueCount = 0;

    public <T extends Question> T newQuestion(QuestionTypes _type) {
        T res;

        switch (_type) {
            case MultipleSelection -> {
                multipleSelectionQueCount++;
                res = (T) new QuestionMultipleSelection();
            }
            default -> {
                multipleChoiceQueCount++;
                res = (T) new QuestionMultipleChoice();
            }
            //throw new IllegalArgumentException("Wrong question type:" + type);
        }
        return res;
    }

}
