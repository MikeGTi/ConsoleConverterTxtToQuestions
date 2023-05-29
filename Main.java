import Product.ParserQuestion;
import Product.Question;
import Product.WrapperQuestions;
import DataExportImport.IOdata;
import DataExportImport.Printer;
import Tests.QuestionDataForTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //---------------------------------------------------------------------
        //random numbers block
        final int queCount = 100;

        //input from console block
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("¬ведите кол-во необходимых вопросов: ");
        final int testQueCount = scanner.nextInt();*/

        final int testQueCount = 20;
        int[] queRndNumbers = new Random().ints(testQueCount, 1, queCount).toArray();
        System.out.println(Arrays.toString(queRndNumbers));
        System.out.println("\n");

        //---------------------------------------------------------------------



        //IO block
        String txt1 = new QuestionDataForTesting().GetQuestionsString();
        //System.out.println(txt1);

        String txt2 = new QuestionDataForTesting("D:\\JavaProjects\\CreateQuestionsTest\\TechTask\\Input\\билеты_тест.txt").toString();
        if (txt2 == null) return;
        String txt = txt1;

        ArrayList<? extends Question> questions = new ParserQuestion(txt).getList();
        questions.forEach(question -> new Printer(question.toString()).printLn());
        WrapperQuestions wrapperQuestions = new WrapperQuestions(questions);
                         wrapperQuestions.toHTML("Questions assessment");

        //new Printer(wrapperQuestions.toString()).printLn();

        IOdata ioData =  new IOdata();
        ioData.writeFile("D:\\JavaProjects\\CreateQuestionsTest\\TechTask\\Output\\test1.html", wrapperQuestions.toString());


        //IOdata.writeObjectToFile("out\\questions.bin", questions);

        /*ArrayList<? extends Question> questions2 = IOdata.<Question>readObjectFromFile("out\\questions.bin");
        System.out.println(questions2);*/

        //---------------------------------------------------------------------
        //Template block  for tagging
        /*final int MAX_NUM = 50;

        //On classes (https://www.youtube.com/watch?v=TWmmfDvcYO0&t=1241s on typeScript)
        ArrayList<Object> rulesColl = new ArrayList<>(List.of(
                new TagNumRule(new Tag("FizzBuzz"), new AndStrategy(new ArrayList<Object>(List.of(new DividerCondition(3), new DividerCondition(5))))),
                new TagNumRule(new Tag("Fizz"), new AndStrategy(new ArrayList<Object>(List.of(new DividerCondition(3))))),
                new TagNumRule(new Tag("Buzz"), new AndStrategy(new ArrayList<Object>(List.of(new DividerCondition(5)))))));

        TagNumRulesCollection numTags = new TagNumRulesCollection(rulesColl);

        for (int i = 0; i < MAX_NUM; i++) {
            new Printer(numTags.find(i, String.valueOf(new Tag(i)))).PrintLn();
        }*/
    }
}