import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        final int MAX_NUM = 50;

        //functional programming
        new FizzBuzzClassic(MAX_NUM).Print();

        //On classes (https://www.youtube.com/watch?v=TWmmfDvcYO0&t=1241s on typeScript)
        ArrayList<Object> rulesColl = new ArrayList<Object>(List.of(
            new TagNumRule(new Tag("FizzBuzz"),new AndStrategy(new ArrayList<Object>(List.of(new DividerCondition(3),new DividerCondition(5))))),
            new TagNumRule(new Tag("Fizz"),new AndStrategy(new ArrayList<Object>(List.of(new DividerCondition(3))))),
            new TagNumRule(new Tag("Buzz"),new AndStrategy(new ArrayList<Object>(List.of(new DividerCondition(5)))))));

        TagNumRulesCollection numTags = new TagNumRulesCollection(rulesColl);

        for (int i = 0; i < MAX_NUM; i++) {
            new Printer(numTags.find(i, String.valueOf(new Tag(i)))).PrintLn();
        }
    }
}

class FakeDataTxt{
    private String str1;
    FakeDataTxt(String path){
        try(FileReader reader = new FileReader(path))
        {
            // ������ �����������
            int c;
            while((c=reader.read())!=-1){

                System.out.print((char)c);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }


    str1 ="""1. ������������ ���������� � ��������� ���������� �����������
    �. ���������, ��������, ���������
    �. ��������, ���������
    �. ���������, ��������, ���������, ���������
    Answer: �.

2. ���������� ������������ I � II ������ ����������� �� ����������� ����� ��������� �� �������, ���
    �. 1 �����
    �. 40 ����
    �. 2 ������
    Answer: �.

3. ������� � ������������� ������ ������� ����������� ����������� �����, �� ���������� � �������� ��������� ��������
    �. ��
    �. ���
    Answer: �.


4. ����� ������������ ����� ���� �� ����������� ��� ���������������
    �. ��
    �. ���
    �. �� ���������� �������� �����
    Answer: �.


5. ���������� ����������� �� �������� ������������� �
    �. 14 ���
    �. 16 ���
    �. 18 ���
    Answer: �.


6.  ����������� ������ ����� �������� ������������
    �. �������
    �. ���������
    �. ������������
    Answer: �.


7. ������� � ������� ���� ������������� ����� ����� ������������ ��� ����������� ������
    �. ��
    �. ���
    �. ����� ���� ��������� �������������
    Answer: �.


8. ����� ����� ���� ���������
    �. ��������� ���������
    �. ������� ������
    �. ������������� ������
    �. ����������� ���������
    �. ��� �����������������
    Answer: �.


9. ������ ������ ������ ���� �������
    �. ������������� �������� ����� �� ���� � �� ������������
    �. ������� ������, ������������� �������� ����� �� ���� � �� ������������
    �. ������������� �������� ����� �� �������, ���� � �� ������������
    Answer: �.


10. �������� � ������� ��������� ������������ ������� �� ���������� ������������ ������������
    �. ����������� �������� ����� �� �������
    �. ����������� �������� ����� �� ������������
    �. ����������
    �. ��� �����������������
    Answer: �.


11. ����������� ���������� ������������ � ���������� ������������ ���������.
            �. ��
    �. ���
    �. ����� ��� ���������� �������� �����
    Answer: �.


12. ������������ ������������ � ������� ������ ���������
    �. ����� �� ���������
    �. ����� ��� ����������
    �. ����������� �������� ����� �� ����
    �. ����������� �������� ����� �� ������������
    Answer: �., �.


13. ������� � ������ ������ ������� �� ����� ����������
    �. ����������� �������� ����� �� ���� � �� �������
    �. ������� ������, ������������� �������� ����� �� ���� � �� ������������
    �. ������������� �������� ����� �� �������, ���� � �� ������������
    Answer: �.


14. �� ������������ �������� ������������ ����������� ������ �� �������
    �. ��
    �. ���
    �. ����� � ���������� �������� �����
    Answer: �.


15. ������� �� ���� ������������ ��������� ���������
    �. ������� �����
    �. ����������� �������� ����� �� ����
    �. ����� �� ������
    �. ������� �����, ����������� �������� ����� �� ����
    �. ����������� �������� ����� �� ����, ����� �� ������
    Answer: �.


16. ������ � ���� �������� ����� � �������� �����������
    �. ������������� ���������� �����������
    �. ���������� �������� ������
    �. ����������� �������������� ������
    �. ����������� �������������� ���������
    �.  �����, ������� ���������� �������� �����
    Answer: �.


17. ������ ������������� ����������, ����������� � ���� ��������, � ����������� ���������, ��������� ������� � ��. ���������� � ��������� ������������
    �. ������� ���������
    �. ������� ����� ��� ����������
    �. ����������� �������� ����� �� ����
    �. ������� �����
    Answer: �.


18.   � ���� ��������� �����������
    �. ���������
    �. �������
    �. �������������
    �. ��� �����������������
    Answer: �.


19. ����������� ������ �� ������� �� ������������� ��������� ����� ����
    �. ������� �����
    �. ����������� �������� ����� �� ����
    �. ����������� �������� �����
    �. ��������� �����
    �. �����������
    �. ��� �����������������
    Answer: �.""";
    String Get(){
        Return str1
    }
}


class Printer {
    private String context;
    Printer(String _context) {
        this.context = _context;
    }

    void PrintLn() {
        System.out.println(context);
    }

    void PrintFile(){
        String[] lines = context.split("\n");
        for(String line : lines){
            System.out.println(line);
        }
    }

}

abstract class Condition {
    public abstract boolean isTruthy(int num);
}

abstract class Strategy implements ITruthy {
    public abstract boolean isTruthy(int num);
    /* extends Strategy */
}

interface ITruthy{
    boolean isTruthy(int num);
}

class Tag{
    private String value;
    //private int value;
    Tag(int _value ) {
        //this.value = String.valueOf(_value);
        this.value = String.valueOf(_value);
    }

    Tag(String _value) {
        this.value = _value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}

class DividerCondition implements ITruthy {
    private int divider;

    DividerCondition(int _divider) {
        this.divider = _divider;
    }

    @Override
    public boolean isTruthy(int num) {
        return num % this.divider == 0;
    }
}

class AndStrategy extends Strategy {
    private ArrayList<ITruthy> conditionsOrStrategies = new ArrayList();
    AndStrategy(ArrayList _conditionsOrStrategies) {
        this.conditionsOrStrategies = _conditionsOrStrategies;
    }
    @Override
    public boolean isTruthy(int num) {
        for(ITruthy item1 : conditionsOrStrategies){
            if (!item1.isTruthy(num)) {
                return false;
            }
        }
        return true;
    }
}

class TagNumRule {
    public Tag tag;
    private Strategy strategy;
    TagNumRule(Tag _tag, Strategy _strategy){
        this.tag = _tag;
        this.strategy = _strategy;
    }

    public boolean isSucces(int num) {
        return this.strategy.isTruthy(num);
    }
}

class TagNumRulesCollection {
    private ArrayList<TagNumRule> tags;
    TagNumRulesCollection(ArrayList<Object> _tag){
        this.tags = new ArrayList(_tag);
    }
    String find(int num, String defaultValue){
        for(TagNumRule item1 : tags){
            if (item1.isSucces(num)) {
                return item1.tag.getValue();
            }
        }
        return defaultValue;
    }
}