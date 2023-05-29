package Product;

public class QuestionFactory {
    private int multipleChoiceQueCount = 0;
    private int multipleSelectionQueCount = 0;

    public <T extends Question> T newQuestion(QuestionTypes _type) {
        T result;

        switch (_type) {
            case MultipleSelection -> {
                multipleSelectionQueCount++;
                result = (T) new QuestionMultipleSelection();
            }
            default -> {
                multipleChoiceQueCount++;
                result = (T) new QuestionMultipleChoice();
            }
            //throw new IllegalArgumentException("Wrong question type:" + type);
        }
        return result;
    }

}
