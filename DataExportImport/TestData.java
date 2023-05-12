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
                "1. Соревнования проводятся в следующих спортивных дисциплинах\n" +
                "а. Трудность, скорость, болдеринг\n" +
                "б. Скорость, двоеборье\n" +
                "в. Трудность, скорость, болдеринг, двоеборье\n" +
                "Answer: в.\n" +
                "\n";
    }

    public String GetQuestionsString(){
        return  "Question type: Multiple Choice\n" +
                "1. Соревнования проводятся в следующих спортивных дисциплинах\n" +
                "а. Трудность, скорость, болдеринг\n" +
                "б. Скорость, двоеборье\n" +
                "в. Трудность, скорость, болдеринг, двоеборье\n" +
                "Answer: в.\n" +
                "\n" +
                "Question type: Multiple Choice\n" +
                "Question title: Регламенты\n" +
                "Question uuid: 6388e9a8-d6f1-4b47-8e6e-19d63c91d473\n" +
                "2. Регламенты соревнований I и II класса размещаются на официальном сайте Федерации не позднее, чем\n" +
                "а. 1 месяц\n" +
                "б. 40 дней\n" +
                "в. 2 недели\n" +
                "Answer: б.\n" +
                "\n" +
                "Question type: Multiple Choice\n" +
                "3. Тренеры и представители команд обязаны подчиняться требованиям судей, не вмешиваясь в действия судейской коллегии\n" +
                "а. да\n" +
                "б. нет\n" +
                "Answer: а.\n" +
                "Difficulty: Easy.\n" +
                "\n";
    }

    public Question GetQuestionObject(){
        HashMap<String, String> answers = new HashMap<String, String>();
        answers.put("a","Трудность, скорость, болдеринг");
        answers.put("б","Скорость, двоеборье");
        answers.put("в","Трудность, скорость, болдеринг, двоеборье");

        return  new QuestionMultipleChoice(1,
                            "Соревнования проводятся в следующих спортивных дисциплинах",
                            answers,
                            "в",
                            Difficulty.EASY);
    }

    @Override
    public String toString() {
        return txt;
    }

}


