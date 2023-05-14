package BusinessLayer;

public enum QuestionTypes {
    MultipleChoice("Multiple Choice"),
    MultipleSelection("Multiple Selection"),
    TrueFalse("True False"),
    TextEntry("Text Entry"),
    TextEntryWithDropdown("Text Entry with Dropdown"),
    Essay("Essay");

    private final String name;

    QuestionTypes(String _value) {
        this.name = _value;
    }

    @Override
    public String toString() {
        return name;
    }
}
