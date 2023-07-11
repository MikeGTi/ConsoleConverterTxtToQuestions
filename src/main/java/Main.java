import dataImportExport.FileIOdata;
import org.apache.commons.cli.*;
import product.QuestionParser;
import product.entity.Question;
import product.htmlWrap.QuestionsWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

import static utils.Utils.getUniqueRandomIntAr;

public class Main {
    public static void main(String[] args) throws IOException {
        //----------------------------------------------
        //Input from console block
        Options options = new Options();

        Option input = new Option("i", "input", true, "input text file path (*.txt)");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "output html file (*.html)");
        output.setRequired(true);
        options.addOption(output);

        Option queCount = new Option("n", "questions", true, "number of questions in assessment");
        queCount.setRequired(true);
        options.addOption(queCount);

        Option textFileCharset = new Option("inCharSet", "input text", true, "input text file charset, for cyrillic - cp1251");
        queCount.setRequired(true);
        options.addOption(textFileCharset);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;//not a good practice, it serves it purpose

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("CreateQuestionsList.jar", options);

            System.exit(1);
        }

        String inputTextFilePath = cmd.getOptionValue("input"); //java.net.URLDecoder.decode(scanner.next().toString().trim(), "UTF8").toString();
        String outputFilePath = cmd.getOptionValue("output");
        int needQue = Integer.parseInt(cmd.getOptionValue("questions"));

        /*System.out.println(inputTextFilePath);
        System.out.println(outputFilePath);
        System.out.println(needQue);*/

        //----------------------------------------------
        //Input questions from *.bin file block
        /*ArrayList<? extends Question> questionsBin = FileIOdata.<Question>readObjectFromFile(Paths.get(inputTextFilePath).getParent() + "\\questions.bin");
        System.out.println(questionsBin);*/
        //----------------------------------------------
        //Input questions text from text file block
        //inputTextFilePath = "D:\\JavaProjects\\CreateQuestionsTest\\TechTask\\Input\\билеты_тест.txt";
        String txt = new FileIOdata().readFile(inputTextFilePath, textFileCharset.toString());
        if (txt.isEmpty()) {
            System.err.println("File Not found\nPath: " + inputTextFilePath);
            return;
        }
        //Input questions text from QuestionDataForTesting class
        //String txt = new QuestionDataForTesting("D:\\JavaProjects\\CreateQuestionsTest\\TechTask\\Input\\билеты_тест.txt").toString();
        //String txt = new QuestionDataForTesting().getQuestionsString();
        //----------------------------------------------
        //Parser block
        Optional<ArrayList<Question>> questions = new QuestionParser(txt).getList();
        if (questions.isEmpty()) {
            System.err.println("Parse file error\nPath: " + inputTextFilePath);
            return;
        }
        //----------------------------------------------
        //Questions count in result  block
        int resultQueTotalCount = needQue;
        if (questions.get().size() <= resultQueTotalCount) {
            resultQueTotalCount = questions.get().size();
        }
        //----------------------------------------------
        //Random numbers questions list block
        Integer[] queRndNumbers = getUniqueRandomIntAr(resultQueTotalCount, 0, questions.get().size() - 1);
        Arrays.sort(queRndNumbers);
        ArrayList<Question> rndQueList =
                                        Arrays.stream(queRndNumbers).map(queRndNumber -> questions.get().get(queRndNumber)).collect(Collectors.toCollection(ArrayList::new));
        //rndQueList.forEach(question -> new Printer(question.toString()).printLn());
        //----------------------------------------------
        //Re-number questions block
        int i = 1;
        Iterator<Question> iterator = rndQueList.iterator();
        while (iterator.hasNext()) {
            Question que = iterator.next();
            que.setNumber(i++);
        }
        //----------------------------------------------
        //Wrapper block
        QuestionsWrapper questionsWrapper = new QuestionsWrapper(rndQueList);
                         questionsWrapper.toHTML("Questions assessment", true);
        //new Printer(questionsWrapper.toString()).printLn();
        //----------------------------------------------
        //Output file save block
        FileIOdata ioData =  new FileIOdata();
                   ioData.writeFile(outputFilePath, questionsWrapper.toString());
        //----------------------------------------------
        //Output question objects to *.bin file block
        //FileIOdata.writeObjectToFile(Paths.get(outputFilePath).getParent() + "\\questions_" + getRandomCharsString(4) + ".bin", questions.get());
        //File questionsBinFile = File.createTempFile("questions", ".bin", new File(Paths.get(outputFilePath).getParent().toUri()));
    }
}