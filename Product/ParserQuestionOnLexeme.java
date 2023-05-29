package Product;

import java.util.ArrayList;
import java.util.List;

//IN PROGRESS
public class ParserQuestionOnLexeme {
    public static void main(String[] args) {


        /*Parser rules
        expr : questionField* EOL ;

        questionField : QuestionType

        QuestionType

        field : factor *

        factor : ^NUMBER | ^CHAR | ^WORD | ^PHRASE | ^expr ;
        */
    }

    public enum LexemeType{
        QUESTION_TYPE, QUESTION_TITLE, QUESTION_UUID,
        QUESTION_NUMBER, QUESTION_STEM,
        QUESTION_ANSWER, QUESTION_ANSWER_CHAR, QUESTION_ANSWER_STEM,
        QUESTION_RIGHT_ANSWER_CHARS, QUESTION_RIGHT_ANSWER_CHAR,
        QUESTION_DIFFICULTY, BOL, EOL
    }

    public static class Lexeme {
        LexemeType type;
        String value;

        public Lexeme(LexemeType type, String value) {
            this.type = type;
            this.value = value;
        }

        public Lexeme(LexemeType type, Character value) {
            this.type = type;
            this.value = value.toString();
        }

        public Lexeme(LexemeType type, Integer value) {
            this.type = type;
            this.value = value.toString();
        }
    }

    public static List<Lexeme> lexAnalyze(String expText) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        ArrayList<String> expTextLines = new ArrayList<>();

        int row = 0;
        int pos = 0;

        while (row < expTextLines.size()) {
            String word = expText.substring(0, expText.indexOf(". "));
            String phrase = expText.substring(0, expText.indexOf(": "));



            /*while (pos < line.length()) {
                char c = (expText.substring(0, expText.indexOf(". "))).charAt(0);
                int number = Integer.parseInt(expText.substring(0, expText.indexOf(". ")));


                *//*switch (c) {
                    case 'Question type:':

                }*//*
            }*/
        }

        return null;
    }


}
