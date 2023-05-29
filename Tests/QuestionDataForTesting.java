package Tests;

import Product.Difficulty;
import Product.Question;
import Product.QuestionFactory;
import Product.QuestionTypes;

import java.util.HashMap;

import static DataExportImport.IOdata.readFile;

public class QuestionDataForTesting {

    public static void main(String[] args) {
        QuestionDataForTesting questionDataForTesting = new QuestionDataForTesting();
        Question questionTest = questionDataForTesting.initQuestionObject(new QuestionFactory().newQuestion(QuestionTypes.MultipleChoice));
        System.out.println(questionTest.toString());
        System.out.println(questionTest.toHTML());
    }

    private String txt = GetQuestionsString();

    public QuestionDataForTesting() {}

    public QuestionDataForTesting(String _path) {
        txt = readFile(_path);
    }

    public String GetQuestionString(){
        return  "Question type: Multiple Choice\n" +
                "1. ������������ ���������� � ��������� ���������� �����������\n" +
                "�. ���������, ��������, ���������\n" +
                "�. ��������, ���������\n" +
                "�. ���������, ��������, ���������, ���������\n" +
                "Answer: �.\n" +
                "\n";
    }

    public String GetQuestionsString(){
        return  "Question type: Multiple Choice\n" +
                "1. ������������ ���������� � ��������� ���������� �����������\n" +
                "�. ���������, ��������, ���������\n" +
                "�. ��������, ���������\n" +
                "�. ���������, ��������, ���������, ���������\n" +
                "Answer: �.\n" +
                "\n" +
                "Question type: Multiple Choice\n" +
                "Question title: ����������\n" +
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

    public <T extends Question> T initQuestionObject(T question){
        //T question = new QuestionFactory().newQuestion(questionTypes);
        HashMap<String, String> answers = new HashMap<String, String>();
                                answers.put("a","���������, ��������, ���������");
                                answers.put("�","��������, ���������");
                                answers.put("�","���������, ��������, ���������, ���������");

        question.setNumber(1);
        question.setStem("������������ ���������� � ��������� ���������� �����������");
        question.setAnswers(answers);
        question.setRightAnswers(new Character[]{'�','�'});
        if (question.getType() == QuestionTypes.MultipleChoice) question.setRightAnswers(new Character[]{'�'});
        question.setDifficulty(Difficulty.EASY);

        return question;
    }

    @Override
    public String toString() {
        return txt;
    }

}


