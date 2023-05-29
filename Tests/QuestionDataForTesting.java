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
                "\n" +
                "Question type: Multiple Selection\n" +
                "4. Тренеры и представители команд обязаны подчиняться требованиям судей, не вмешиваясь в действия судейской коллегии\n" +
                "а. да\n" +
                "б. нет\n" +
                "Answer: а, б.\n" +
                "Difficulty: Easy.\n";

    }

    public <T extends Question> T initQuestionObject(T question){
        //T question = new QuestionFactory().newQuestion(questionTypes);
        HashMap<String, String> answers = new HashMap<String, String>();
                                answers.put("a","Трудность, скорость, болдеринг");
                                answers.put("б","Скорость, двоеборье");
                                answers.put("в","Трудность, скорость, болдеринг, двоеборье");

        question.setNumber(1);
        question.setStem("Соревнования проводятся в следующих спортивных дисциплинах");
        question.setAnswers(answers);
        question.setRightAnswers(new Character[]{'а','в'});
        if (question.getType() == QuestionTypes.MultipleChoice) question.setRightAnswers(new Character[]{'а'});
        question.setDifficulty(Difficulty.EASY);

        return question;
    }

    @Override
    public String toString() {
        return txt;
    }

}


