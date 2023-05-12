package DataExportImport;

enum QuestionTypes
{
    MultipleChoice("Multiple Choice"),
    MultipleSelection("Multiple Selection"),
    TrueFalse("True False"),
    TextEntry("Text Entry"),
    TextEntryWithDropdown("Text Entry with Dropdown"),
    Essay("Essay");

    private final String name;
    QuestionTypes(String _value){ this.name = _value; }

    @Override
    public String toString() { return name; }
}

public class QuestionFactory {
    private int multipleChoiceQueCount = 0;
    private int multipleSelectionQueCount = 0;

    public Question CreateQuestion(QuestionTypes _type) {
        Question res = null;

        switch (_type) {
            case MultipleSelection:
                multipleSelectionQueCount++;
                res = new QuestionMultipleSelection();
                break;
            default:
                multipleChoiceQueCount++;
                res = new QuestionMultipleChoice();
                break;
                //throw new IllegalArgumentException("Wrong question type:" + type);
        }
        return res;
    }

}
