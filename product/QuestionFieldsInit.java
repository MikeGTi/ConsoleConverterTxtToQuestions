package product;

import product.entity.QuestionMultipleChoice;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class QuestionFieldsInit {

    public static void main(String[] args) {
        var que = new QuestionMultipleChoice(){};
        fillField(1, _queNumber -> que.setNumber(_queNumber));
        fillField("f7ec8052-9830-4a12-bbe4-cc515b9fdd07", _queUUID -> que.setUUID(_queUUID));
        fillField("Question stem...", _queStem -> que.setStem(_queStem));
        System.out.println(que.toString());
    }

    public static <T> void fillField(T value, Consumer<T> consumer){
        consumer.accept(value);
    }

    public static <T> void fillField (Supplier<T> supplier, Consumer<T> consumer){
        consumer.accept(supplier.get());
    }

}
