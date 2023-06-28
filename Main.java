import DataExportImport.FileIOdata;
import DataExportImport.Printer;
import Product.Entity.Question;
import Product.HtmlWrap.QuestionsWrapper;
import Product.QuestionParser;

import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class Main {
    public static void main(String[] args) {
        //---------------------------------------------------------------------
        //IO block
        String path = "";
        Integer needQue = 0;

        if (!(args.length == 0)) {
            path = args[0];
            needQue = Integer.parseInt(args[1]);
        }

        if (path.isEmpty()) {
            System.out.println("¬ведите путь к файлу: ");
            try {
                Scanner scanner = new Scanner(System.in);
                path = java.net.URLDecoder.decode(scanner.next().toString().trim(), "UTF8").toString();
                //System.out.println("Path: '" + path + "'");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        //if (path.isEmpty()){ path = "D:\\JavaProjects\\CreateQuestionsTest\\TechTask\\Input\\билеты_тест.txt"; }

        //String txt = new QuestionDataForTesting("D:\\JavaProjects\\CreateQuestionsTest\\TechTask\\Input\\билеты_тест.txt").toString();
        //String txt = new QuestionDataForTesting().getQuestionsString();
        String txt = new FileIOdata().readFile(path, "cp1251").toString();
        if (txt.isEmpty()) {
            System.err.println("File Not found\nPath: " + path);
            return;
        }

        Optional<ArrayList<Question>> questions = new QuestionParser(txt).getList();
        if (questions.isEmpty()) {
            System.err.println("Parse file error\nPath: " + path);
            return;
        }
        //----------------------------------------------
        //input from console block
        if (needQue == 0) {
            System.out.println("¬ведите кол-во необходимых вопросов(не более " + questions.get().size() + "): ");
            Scanner scanner = new Scanner(System.in);
            needQue = scanner.nextInt();
        }

        int resultQueTotalCount = needQue;
        if (questions.get().size() <= resultQueTotalCount) {
            resultQueTotalCount = questions.get().size();
        }
        //----------------------------------------------
        Integer[] queRndNumbers = getUniqueRandomIntAr(resultQueTotalCount, 0, questions.get().size()-1);

        ArrayList<Question> rndQueList =
                Arrays.stream(queRndNumbers).map(queRndNumber -> questions.get().get(queRndNumber)).collect(Collectors.toCollection(ArrayList::new));

        rndQueList.forEach(question -> new Printer(question.toString()).printLn());

        QuestionsWrapper questionsWrapper = new QuestionsWrapper(rndQueList);
                         questionsWrapper.toHTML("Questions assessment", true);

        //System.out.println(questionsWrapper.toString());

        //new Printer(questionsWrapper.toString()).printLn();

        Path folderPath = Paths.get(path).getParent();



        FileIOdata ioData =  new FileIOdata();
                   //ioData.writeFile("D:\\JavaProjects\\CreateQuestionsTest\\TechTask\\Output\\test1.html", questionsWrapper.toString());
                   ioData.writeFile(folderPath + "\\test1.html", questionsWrapper.toString());
        //FileIOdata.writeObjectToFile("out\\questions.bin", questions);

        /*ArrayList<? extends Question> questions2 = FileIOdata.<Question>readObjectFromFile("out\\questions.bin");
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
    
    private static Integer[] getUniqueRandomIntAr(int upperBound, int start, int end){
        if ((upperBound - (start + end - 1)) == 0 || (upperBound - (start + end + 1)) == 0) {
            return IntStream.rangeClosed(start, end).boxed().toArray(n -> new Integer[upperBound]);
        }

        Random rnd = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < upperBound) {
            set.add(rnd.nextInt(start, end));
        }
        return set.toArray(new Integer[set.size() - 1]);
    }
    
}