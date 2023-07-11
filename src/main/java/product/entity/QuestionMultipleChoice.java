package product.entity;

import product.entity.entityEnums.QuestionTypes;

import java.io.Serial;

public class QuestionMultipleChoice extends Question {
    @Serial
    private static final long serialVersionUID = 6434123350448509776L;

    public QuestionMultipleChoice() {
        super( QuestionTypes.MultipleChoice );
    }

    @Override
    public String toHTML() {
        return super.toHTML().replaceAll("dummyElementType", "radio");
    }
}
