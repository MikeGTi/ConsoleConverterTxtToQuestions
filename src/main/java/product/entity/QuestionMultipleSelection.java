package product.entity;

import product.entity.entityEnums.QuestionTypes;

import java.io.Serial;

public class QuestionMultipleSelection extends Question {
    @Serial
    private static final long serialVersionUID = 3439308714477920320L;

    public QuestionMultipleSelection() {
        super( QuestionTypes.MultipleSelection );
    }

    @Override
    public String toHTML() {
        return super.toHTML().replaceAll("dummyElementType", "checkbox");
    }
}
