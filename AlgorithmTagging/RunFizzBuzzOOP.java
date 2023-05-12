package AlgorithmTagging;

import java.util.ArrayList;
import java.util.List;

public class RunFizzBuzzOOP {
    public static void main(String[] args) {

        /*final int MAX_NUM = 100;

        //On classes (https://www.youtube.com/watch?v=TWmmfDvcYO0&t=1241s on typeScript)
        ArrayList<TagRule> rulesColl = new ArrayList<>(List.of(
            new TagRule(new Tag("FizzBuzz"),new AndStrategy(new ArrayList<>(List.of(new DividerCondition(3),new DividerCondition(5))))),
            new TagRule(new Tag("Fizz"),new AndStrategy(new ArrayList<>(List.of(new DividerCondition(3))))),
            new TagRule(new Tag("Buzz"),new AndStrategy(new ArrayList<>(List.of(new DividerCondition(5)))))));

        TagNumRulesCollection numTags = new TagNumRulesCollection(rulesColl);

        for (int i = 1; i <= MAX_NUM; i++) {
            new DataExportImport.Printer(numTags.Find(i, new Tag(String.valueOf(i)).toString())).PrintLn();
        }*/

        final int MAX_NUM = 20;

        ArrayList<String> fields= new ArrayList<>(List.of("Stem", "Opa", "Answer", "Opa2", "Right answer"));

        //On classes (https://www.youtube.com/watch?v=TWmmfDvcYO0&t=1241s on typeScript)
        ArrayList<TagRule> rulesColl = new ArrayList<>(List.of(
                new TagRule<String>(new Tag("FizzBuzz"),new AndStrategy<String>(new ArrayList<>(List.of(new EqualCondition("Stem"))))),
                new TagRule<String>(new Tag("Fizz"),new AndStrategy<String>(new ArrayList<>(List.of(new EqualCondition("Answer"))))),
                new TagRule<String>(new Tag("Buzz"),new AndStrategy<String>(new ArrayList<>(List.of(new EqualCondition("Right answer")))))));

        TagRulesCollection tagsRulesColl = new TagRulesCollection(rulesColl);

        for (int i = 0; i < fields.size(); i++) {
            new DataExportImport.Printer(tagsRulesColl.Find(fields.get(i), new Tag(fields.get(i)).toString())).printLn();
        }

    }
}

interface ITruthy<T>{
    boolean isTruthy(T _value);
}

abstract class Condition<T> implements ITruthy<T> {
    public abstract boolean isTruthy(T _value);
}
abstract class Strategy<T> implements ITruthy<T> {
    public abstract boolean isTruthy(T _value);
}

class TagRule<T> {
    private Tag tag;
    public String getTag() {
        return tag.toString();
    }

    private Strategy strategy;
    public Strategy getStrategy() {
        return strategy;
    }

    TagRule(Tag _tag, Strategy _strategy){
        this.tag = _tag;
        this.strategy = _strategy;
    }

    public boolean isSuccess(T value) { return strategy.isTruthy(value);
    }

}

class Tag{
    private String value;
    Tag(String _value) { this.value = _value; }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString(){
        return String.valueOf( this.value );
    }
}

class DividerCondition extends Condition<Integer> {
    private int divider;

    DividerCondition(int _divider) {
        this.divider = _divider;
    }

    @Override
    public boolean isTruthy(Integer _value) {
        return _value % this.divider == 0;
    }
}

class EqualCondition extends Condition<String> {
    private String coincidence;

    EqualCondition(String _coincidence) {
        this.coincidence = _coincidence;
    }

    @Override
    public boolean isTruthy(String _value) {
        return coincidence.equalsIgnoreCase(_value);
    }
}



class AndStrategy<T> extends Strategy<T> {
    private ArrayList<ITruthy> conditionsOrStrategies;
    AndStrategy(ArrayList<ITruthy> _conditionsOrStrategies) {
        this.conditionsOrStrategies = _conditionsOrStrategies;
    }
    @Override
    public boolean isTruthy(T value) {
        for(ITruthy item1 : conditionsOrStrategies){
            if (!item1.isTruthy(value)) {
                return false;
            }
        }
        return true;
    }
}

class TagNumRulesCollection {
    private ArrayList<TagRule> tags;
    TagNumRulesCollection(ArrayList<TagRule> _tag){
        this.tags = _tag;
    }
    String Find(int value, String defaultValue){
        for(TagRule item1 : tags){
            if (item1.isSuccess(value)) {
                return item1.getTag();
            }
        }
        return defaultValue;
    }
}

class TagRulesCollection {
    private ArrayList<TagRule> tags;
    TagRulesCollection(ArrayList<TagRule> _tag){
        this.tags = _tag;
    }
    String Find(String value, String defaultValue){
        for(TagRule item1 : tags){
            if (item1.isSuccess(value)) {
                return item1.getTag();
            }
        }
        return defaultValue;
    }
}