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
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){

                System.out.print((char)c);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }


    str1 ="""1. Соревнования проводятся в следующих спортивных дисциплинах
    а. Трудность, скорость, болдеринг
    б. Скорость, двоеборье
    в. Трудность, скорость, болдеринг, двоеборье
    Answer: в.

2. Регламенты соревнований I и II класса размещаются на официальном сайте Федерации не позднее, чем
    а. 1 месяц
    б. 40 дней
    в. 2 недели
    Answer: б.

3. Тренеры и представители команд обязаны подчиняться требованиям судей, не вмешиваясь в действия судейской коллегии
    а. да
    б. нет
    Answer: а.


4. Судьи соревнований могут быть их участниками или представителями
    а. Да
    б. Нет
    в. По разрешению главного судьи
    Answer: б.


5. Спортсмены допускаются ко взрослым соревнованиям с
    а. 14 лет
    б. 16 лет
    в. 18 лет
    Answer: б.


6.  Непрерывная черная линия является ограничением
    а. Строгим
    б. Нестрогим
    в. Направляющим
    Answer: б.


7. Верхний и боковые края искусственной стены можно использовать при прохождении трассы
    а. Да
    б. Нет
    в. Может если разрешено постановщиком
    Answer: в.


8. Старт может быть обозначен
    а. Стартовой площадкой
    б. Красной линией
    в. Страховочными матами
    г. Электронная платформа
    д. Все вышеперечисленное
    Answer: д.


9. Каждая трасса должна быть принята
    а. Заместителями главного судьи по виду и по безопасности
    б. Главным судьей, заместителями главного судьи по виду и по безопасности
    в. Заместителями главного судьи по трассам, виду и по безопасности
    Answer: а.


10. Качество и текущее состояние страховочной веревки на протяжении соревнований контролирует
    а. Заместитель главного судьи по трассам
    б. Заместитель главного судьи по безопасности
    в. Страховщик
    г. Все вышеперечисленные
    Answer: б.


11. Запрещается проведение соревнований в отсутствие медицинского персонала.
            а. Да
    б. Нет
    в. Можно при разрешении главного судьи
    Answer: б.


12. Правильность привязывания к веревке должны проверить
    а. Судья на страховке
    б. Судья при участниках
    в. Заместитель главного судьи по виду
    г. Заместитель главного судьи по безопасности
    Answer: а., б.


13. Частоту и способ чистки зацепов на стене определяют
    а. Заместители главного судьи по виду и по трассам
    б. Главным судьей, заместителями главного судьи по виду и по безопасности
    в. Заместителями главного судьи по трассам, виду и по безопасности
    Answer: а.


14. До официального закрытия соревнований запрещается лазать по трассам
    а. Да
    б. Нет
    в. Можно с разрешения главного судьи
    Answer: б.


15. Решение по сути технического инцидента принимает
    а. Главный судья
    б. Заместитель главного судьи по виду
    в. Судья на трассе
    г. Главный судья, заместитель главного судьи по виду
    д. Заместитель главного судьи по виду, судья на трассе
    Answer: б.


16. Доступ в зону изоляции после её закрытия разрешается
    а. Представителю проводящей организации
    б. Участникам текущего раунда
    в. Официальным представителям команд
    г. Официальным представителям Федерации
    д.  Лицам, имеющие разрешение главного судьи
    Answer: д.


17. Должен предупреждать участников, находящихся в зоне изоляции, о технических перерывах, задержках стартов и др. изменениях в программе соревнований
    а. Главный секретарь
    б. Старший Судья при участниках
    в. Заместитель главного судьи по виду
    г. Главный судья
    Answer: в.


18.   В зону просмотра допускаются
    а. Участники
    б. Тренера
    в. Представители
    г. Все вышеперечисленные
    Answer: а.


19. Официальные ответы по трассам на соревнованиях трудности может дать
    а. Главный судья
    б. Заместитель главного судьи по виду
    в. Заместитель главного судьи
    г. Начальник трасс
    д. Подготовщик
    е. Все вышеперечисленные
    Answer: б.""";
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