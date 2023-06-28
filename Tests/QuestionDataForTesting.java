package Tests;

import DataExportImport.Printer;
import Product.Entity.Enums.Difficulty;
import Product.Entity.Enums.QuestionPart;
import Product.Entity.Enums.QuestionTypes;
import Product.Entity.Question;
import Product.QuestionParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static DataExportImport.FileIOdata.readFile;

public class QuestionDataForTesting {


    public static void main(String[] args) {
        /*Question questionTest = getQuestionObject(new QuestionFactory().newQuestion(QuestionTypes.MultipleChoice));
        System.out.println(questionTest.toString());
        System.out.println(questionTest.toHTML());*/

        ArrayList<Question> queList = getQuestionsObjects().get();
        queList.forEach(question -> { new Printer(question.toString()).printLn(); });
        queList.forEach(question -> { new Printer(question.toHTML()).printLn(); });
    }

    private String txt = getQuestionsString();

    public QuestionDataForTesting() {}

    public QuestionDataForTesting(String _path) {
        txt = readFile(_path, "cp1251"); //"utf8"
    }

    public static String getQuestionString(){
        //getBrokenQuestionString - strong connection
        return  "Question type: Multiple Choice\n" +
                "1. ������������ ���������� � ��������� ���������� �����������\n" +
                "�. ���������, ��������, ���������\n" +
                "�. ��������, ���������\n" +
                "�. ���������, ��������, ���������, ���������\n" +
                "Answer: �.\n" +
                "Difficulty: Easy\n" +
                "\n";
    }

    public String getBrokenQuestionString(QuestionPart brokenIn){
        String questionText = getQuestionString();
        switch (brokenIn) {
            case QUESTION_TYPE -> {
                questionText = questionText.replaceFirst("Question type: Multiple Choice\n","");
            }
            case QUESTION_NUMBER -> {
                questionText = questionText.replaceFirst("1\\. ","");
            }
            case QUESTION_STEM -> {
                questionText = questionText.replaceFirst("������������ ���������� � ��������� ���������� �����������","");
            }
            case QUESTION_ANSWERS -> {
                questionText = questionText.replaceFirst("�\\. ���������, ��������, ���������\n","");
                questionText = questionText.replaceFirst("�\\. ��������, ���������\n","");
            }
            case QUESTION_ANSWER_ONE -> {
                questionText = questionText.replaceFirst("�\\. ���������, ��������, ���������, ���������\n","");
            }
            case QUESTION_RIGHT_ANSWERS -> {
                questionText = questionText.replaceFirst("Answer: �\\.\n","");
            }
            case QUESTION_DIFFICULTY -> {
                questionText = questionText.replaceFirst("Difficulty: Easy\n","");
            }
        }
        return questionText;
    }

    public static String getQuestionsString(){
        return  "Question type: Multiple Choice\n" +
                "1. ������������ ���������� � ��������� ���������� �����������\n" +
                "�. ���������, ��������, ���������\n" +
                "�. ��������, ���������\n" +
                "�. ���������, ��������, ���������, ���������\n" +
                "Answer: �.\n" +
                "\n" +
                "Question type: Multiple Choice\n" +
                "Question title: ���������\n" +
                "Question uuid: 6388e9a8-d6f1-4b47-8e6e-19d63c91d473\n" +
                "2. ���������� ������������ I � II ������ ����������� �� ����������� ����� ��������� �� �������, ���\n" +
                "�. 1 �����\n" +
                "�. 40 ����\n" +
                "�. 2 ������\n" +
                "Answer: �.\n" +
                "\n" +
                "Question type: Multiple Choice\n" +
                "3. ������� � ������������� ������ ������� ����������� ����������� �����, �� ���������� � �������� ��������� ��������\n" +
                "�. ��\n" +
                "�. ���\n" +
                "Answer: �.\n" +
                "Difficulty: Easy.\n" +
                "\n" +
                "Question type: Multiple Selection\n" +
                "4. ������� � ������������� ������ ������� ����������� ����������� �����, �� ���������� � �������� ��������� ��������\n" +
                "�. ��\n" +
                "�. ���\n" +
                "Answer: �, �.\n" +
                "Difficulty: Easy.\n";
    }

    public static Optional<ArrayList<Question>> getQuestionsObjects(){
        QuestionParser parser = new QuestionParser(getQuestionsString());
        Optional<ArrayList<Question>> optionalQuestionsList = parser.getList();

        if (optionalQuestionsList.isPresent()) {
            return optionalQuestionsList;
        } else {
            return Optional.empty();
        }
    }


    public static <T extends Question> T getQuestionObject(T question){
        //T question = new QuestionFactory().newQuestion(questionTypes);
        HashMap<String, String> answers = new HashMap<String, String>();
                                answers.put("a","���������, ��������, ���������");
                                answers.put("�","��������, ���������");
                                answers.put("�","���������, ��������, ���������, ���������");

        Character[] rightAnswers = new Character[]{'�','�'};
        if (question.getType() == QuestionTypes.MultipleChoice) question.setRightAnswers(new Character[]{'�'});

        question
            .setNumber(1)
            .setStem("������������ ���������� � ��������� ���������� �����������")
            .setAnswers(answers)
            .setRightAnswers(rightAnswers)
            .setDifficulty(Difficulty.EASY);

        return question;
    }

    @Override
    public String toString() {
        return txt;
    }
}


