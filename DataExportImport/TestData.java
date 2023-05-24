package DataExportImport;

import java.util.HashMap;

import static DataExportImport.IOdata.readFile;

//import java.io.*;

public class TestData {
    private String txt = GetQuestionsString();

    public TestData() {}

    public TestData(String _path) {
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
                "\n";
    }

    public Question GetQuestionObject(){
        HashMap<String, String> answers = new HashMap<String, String>();
        answers.put("a","���������, ��������, ���������");
        answers.put("�","��������, ���������");
        answers.put("�","���������, ��������, ���������, ���������");

        return  new QuestionMultipleChoice(1,
                            "������������ ���������� � ��������� ���������� �����������",
                            answers,
                            "�",
                            Difficulty.EASY);
    }

    @Override
    public String toString() {
        return txt;
    }

}


