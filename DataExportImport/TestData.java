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
        txt = "1. Соревнования проводятся в следующих спортивных дисциплинах\n" +
                "а. Трудность, скорость, болдеринг\n" +
                "б. Скорость, двоеборье\n" +
                "в. Трудность, скорость, болдеринг, двоеборье\n" +
                "Answer: в.\n" +
                "\n" +
                "2. Регламенты соревнований I и II класса размещаются на официальном сайте Федерации не позднее, чем\n" +
                "а. 1 месяц\n" +
                "б. 40 дней\n" +
                "в. 2 недели\n" +
                "Answer: б.\n" +
                "\n" +
                "3. Тренеры и представители команд обязаны подчиняться требованиям судей, не вмешиваясь в действия судейской коллегии\n" +
                "а. да\n" +
                "б. нет\n" +
                "Answer: а.\n" +
                "Difficulty: Easy.\n" +
                "\n";
    }

    TestData(String _path) {
        txt = GetDataFromFile(_path);
    }

    public String GetQuestionString(){
        return  "1. Соревнования проводятся в следующих спортивных дисциплинах\n" +
                "а. Трудность, скорость, болдеринг\n" +
                "б. Скорость, двоеборье\n" +
                "в. Трудность, скорость, болдеринг, двоеборье\n" +
                "Answer: в.\n" +
                "\n";
    }

    public String GetQuestionsString(){
        return  "1. Соревнования проводятся в следующих спортивных дисциплинах\n" +
                "а. Трудность, скорость, болдеринг\n" +
                "б. Скорость, двоеборье\n" +
                "в. Трудность, скорость, болдеринг, двоеборье\n" +
                "Answer: в.\n" +
                "\n" +
                "2. Регламенты соревнований I и II класса размещаются на официальном сайте Федерации не позднее, чем\n" +
                "а. 1 месяц\n" +
                "б. 40 дней\n" +
                "в. 2 недели\n" +
                "Answer: б.\n" +
                "\n" +
                "3. Тренеры и представители команд обязаны подчиняться требованиям судей, не вмешиваясь в действия судейской коллегии\n" +
                "а. да\n" +
                "б. нет\n" +
                "Answer: а.\n" +
                "Difficulty: Easy.\n" +
                "\n";
    }

    public Question GetQuestionObject(){
        HashMap<String, String> answers = new HashMap<String,String>();
        answers.put("a","Трудность, скорость, болдеринг");
        answers.put("б","Скорость, двоеборье");
        answers.put("в","Трудность, скорость, болдеринг, двоеборье");

        HashMap<String, String> rightAnswers = new HashMap<String,String>();
        rightAnswers.put("в", answers.get("в"));

        return  new Question(1,
                             "Соревнования проводятся в следующих спортивных дисциплинах",
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


