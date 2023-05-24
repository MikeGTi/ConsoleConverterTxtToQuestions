package DataExportImport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
//import java.io.*;

public class TestData {
    private String txt="";

    TestData() {
        txt = "1. ������������ ���������� � ��������� ���������� �����������\n" +
                "�. ���������, ��������, ���������\n" +
                "�. ��������, ���������\n" +
                "�. ���������, ��������, ���������, ���������\n" +
                "Answer: �.\n" +
                "\n" +
                "2. ���������� ������������ I � II ������ ����������� �� ����������� ����� ��������� �� �������, ���\n" +
                "�. 1 �����\n" +
                "�. 40 ����\n" +
                "�. 2 ������\n" +
                "Answer: �.\n" +
                "\n" +
                "3. ������� � ������������� ������ ������� ����������� ����������� �����, �� ���������� � �������� ��������� ��������\n" +
                "�. ��\n" +
                "�. ���\n" +
                "Answer: �.\n" +
                "Difficulty: Easy.\n" +
                "\n";
    }

    TestData(String _path) {
        txt = GetDataFromFile(_path);
    }

    public String GetQuestionString(){
        return  "1. ������������ ���������� � ��������� ���������� �����������\n" +
                "�. ���������, ��������, ���������\n" +
                "�. ��������, ���������\n" +
                "�. ���������, ��������, ���������, ���������\n" +
                "Answer: �.\n" +
                "\n";
    }

    public String GetQuestionsString(){
        return  "1. ������������ ���������� � ��������� ���������� �����������\n" +
                "�. ���������, ��������, ���������\n" +
                "�. ��������, ���������\n" +
                "�. ���������, ��������, ���������, ���������\n" +
                "Answer: �.\n" +
                "\n" +
                "2. ���������� ������������ I � II ������ ����������� �� ����������� ����� ��������� �� �������, ���\n" +
                "�. 1 �����\n" +
                "�. 40 ����\n" +
                "�. 2 ������\n" +
                "Answer: �.\n" +
                "\n" +
                "3. ������� � ������������� ������ ������� ����������� ����������� �����, �� ���������� � �������� ��������� ��������\n" +
                "�. ��\n" +
                "�. ���\n" +
                "Answer: �.\n" +
                "Difficulty: Easy.\n" +
                "\n";
    }

    public Question GetQuestionObject(){
        HashMap<String, String> answers = new HashMap<String,String>();
        answers.put("a","���������, ��������, ���������");
        answers.put("�","��������, ���������");
        answers.put("�","���������, ��������, ���������, ���������");

        HashMap<String, String> rightAnswers = new HashMap<String,String>();
        rightAnswers.put("�", answers.get("�"));

        return  new Question(1,
                             "������������ ���������� � ��������� ���������� �����������",
                            answers,
                            rightAnswers);
    }

    private static String GetDataFromFile(String path){
        String txt1="";
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            /*String str1;
            List<String> str1 =reader.readAllLines(@NotNull  Path path, @NotNull  Charset cs);*/
            txt1 = reader.readLine();
            while (txt1 != null) {
                //System.out.println(txt);
                txt1 = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return txt1;
    }

    @Override
    public String toString() {
        return txt;
    }

}


