package Product;

import Product.Entity.Question;
import Product.Entity.Enums.Difficulty;
import Product.Entity.Enums.QuestionTypes;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionParser {

    private String text;

    public QuestionParser(String text) {
        this.text = text;
    }

    public Optional<ArrayList<Question>> getList(){
        return getQuestionsObjectsList(getQuestionsTextBlocks());
    }

    private String[] getQuestionsTextBlocks(){
        String[] questionTextBlocks1 =
                getMatches(getRegexPatternsForQuestions().get("Questions"), this.text, 1).toArray(new String[0]);

        String[] questionTextBlocks2 =
                getMatches(getRegexPatternsForQuestions().get("Questions wo 'Question type'"), this.text, 1).toArray(new String[0]);

        String[] questionTextBlocks;

        if (questionTextBlocks1.length >= questionTextBlocks2.length) {
            questionTextBlocks = questionTextBlocks1;
        } else {
            questionTextBlocks = questionTextBlocks2;
        }

        if (questionTextBlocks.length == 0) {
            System.err.println("Question parser error (not found questions text blocks)");
            return null;
        } else {
            return questionTextBlocks;
        }
    }

    private static Optional<ArrayList<Question>> getQuestionsObjectsList(@Nullable String[] questionTextBlocks) {
        if (questionTextBlocks == null) {
            return Optional.empty();
        }
        Optional<ArrayList<Question>> questions = Optional.of(new ArrayList<>(questionTextBlocks.length));
        HashMap<String, Pattern> regxPatternsQues = getRegexPatternsForQuestions();
        var queFactory = new QuestionFactory<Question>();

        for (String queItm: questionTextBlocks) {
            var que = queFactory.getNewQuestion(QuestionTypes.valueOf(
                                                  (getMatch(getMatches(regxPatternsQues.get("Question type"),queItm,1),0, "Multiple Choice")).replaceAll("\\s+","")));
            //set fields from question text block
            que.setTitle(getMatch(getMatches(regxPatternsQues.get("Question title"),queItm,1),0,""))
               .setUUID(getMatch(getMatches(regxPatternsQues.get("Question uuid"),queItm,1),0,""))
               .setNumber(Integer.parseInt(getMatch(getMatches(regxPatternsQues.get("Question number"),queItm,1),0,"0")))
               .setStem(getMatch(getMatches(regxPatternsQues.get("Question stem"),queItm,1),0,""))
               .setAnswers(getHashMap(getMatches(regxPatternsQues.get("Question answers symbols"),queItm,1),
                                      getMatches(regxPatternsQues.get("Question answers stem"),queItm,1)))
               .setRightAnswers(getCharacterArray(getMatch(getMatches(regxPatternsQues.get("Question answers right"),queItm,1),0,"")))
               .setDifficulty(Difficulty.valueOf(getMatch(getMatches(regxPatternsQues.get("Question difficulty"),queItm,1),0,"Easy").toUpperCase()));

            questions.get().add(que);
        }
        return questions;
    }

    private static Character[] getCharacterArray(String string){
        String string1 = string.replaceAll("[^A-Za-zÀ-ßà-ÿ]", "");    //let A-z only
            // string1 = string.replaceAll("[^0-9] ","");                       //remove digits
        return string1.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
    }

    private static <K, V> HashMap<K, V> getHashMap(ArrayList<K> l1, ArrayList<V> l2){
        HashMap<K, V> res = new HashMap<K, V>();
        Iterator<K> key = l1.iterator();
        Iterator<V> value = l2.iterator();
        while (key.hasNext() || value.hasNext()) res.put(key.next(), value.next());
        return res;
    }

    private static ArrayList<String> getMatches(Pattern _pattern, String _txt, int _groupNumber){
        Matcher matcher = _pattern.matcher(_txt);
        ArrayList<String> matches = new ArrayList<String>();
        while (matcher.find()) {
            try {
                matches.add(matcher.group(_groupNumber).trim());
            } catch (IndexOutOfBoundsException e) {
                matches.add("");
            }
        }
        return matches;
    }

    private static String getMatch(ArrayList<String> _matches, int _index, String _defaultValue) {
        String res;
        try{
            res = _matches.get(_index);
        }catch(IndexOutOfBoundsException e) {
            res = _defaultValue;
            //System.err.println("Match not found");
        }
        return res;
    }

    private static HashMap<String, Pattern> getRegexPatternsForQuestions() {
        final HashMap<String, Pattern> patterns = new HashMap<>();

        patterns.put("Questions", Pattern.compile("(^Question type(.*\\n)+?(^\\n|\\z|\\Z|[Aa]nswer.*\\.)+?)", Pattern.MULTILINE));
        patterns.put("Questions wo 'Question type'", Pattern.compile("(^[0-9]{1,3}\\.(.*\\n)+?(^$|.*\\.$|^\\n|\\z|\\Z)+?)", Pattern.MULTILINE));
        patterns.put("Question fields by group", Pattern.compile("(^[0-9]{1,3})\\.(.*)\\n+?(^[À-ÿA-z]{1}\\..*[\\n{1,2}]){1,9}[Aa]nswer:.*[\\n{1,2}][Dd]ifficulty:(.*)\\n", Pattern.MULTILINE));
        patterns.put("Question type", Pattern.compile("^Question type: (.*)$", Pattern.MULTILINE));
        patterns.put("Question title", Pattern.compile("^Question title: (.*)$", Pattern.MULTILINE));
        patterns.put("Question uuid", Pattern.compile("^Question uuid: (.*)$", Pattern.MULTILINE));
        patterns.put("Question number", Pattern.compile("(^[0-9]{1,3})\\.", Pattern.MULTILINE));
        patterns.put("Question stem", Pattern.compile("^[0-9]{1,3}\\.(.*)\\n+?^[À-ÿA-z]{1}\\.", Pattern.MULTILINE));
        patterns.put("Question answers", Pattern.compile("(^[À-ÿA-z]{1}\\..*)", Pattern.MULTILINE));
        patterns.put("Question answers symbols", Pattern.compile("(^[À-ÿA-z]{1})\\.", Pattern.MULTILINE));
        patterns.put("Question answers stem", Pattern.compile("^[À-ÿA-z]{1}\\.(.*)", Pattern.MULTILINE));
        patterns.put("Question answers right", Pattern.compile("^[Aa]nswer:[\\s]{0,}(.*)\\.", Pattern.MULTILINE));
        patterns.put("Question answers right stem", Pattern.compile("^[Aa]nswer:[\\s]{0,}[A-zÀ-ÿ][,|\\.](.*)", Pattern.MULTILINE));
        patterns.put("Question answers right symbols", Pattern.compile("^[Aa]nswer:[\\s]{0,}([A-zÀ-ÿ])[,|\\.]", Pattern.MULTILINE));
        patterns.put("Question difficulty", Pattern.compile("^[Dd]ifficulty:[\\s]{0,}(.*)[\\.]{0,}", Pattern.MULTILINE));


        //EOF $(?![\r\n])
        //blank line ^$
        // named group add: (?<groupName>groupPattern)
        //patterns.put("Questions", "(^Question type(.*\\n)+?(^$|.*\\.$|^\\n|\\z|\\Z)+?)")
        /*patterns.put("Questions", "(^Question type(.*\\n)+?(^\\n|\\z|\\Z|[Aa]nswer.*\\.)+?)");
        patterns.put("Questions wo 'Question type'", "(^[0-9]{1,3}\\.(.*\\n)+?(^$|.*\\.$|^\\n|\\z|\\Z)+?)");
        patterns.put("Question fields by group", "(^[0-9]{1,3})\\.(.*)\\n+?(^[À-ÿA-z]{1}\\..*[\\n{1,2}]){1,9}[Aa]nswer:.*[\\n{1,2}][Dd]ifficulty:(.*)\\n");
        patterns.put("Question type", "^Question type: (.*)$");
        patterns.put("Question title", "^Question title: (.*)$");
        patterns.put("Question uuid", "^Question uuid: (.*)$");
        patterns.put("Question number", "(^[0-9]{1,3})\\.");
        patterns.put("Question stem", "^[0-9]{1,3}\\.(.*)\\n+?^[À-ÿA-z]{1}\\.");
        patterns.put("Question answers", "(^[À-ÿA-z]{1}\\..*)");
        patterns.put("Question answers symbols", "(^[À-ÿA-z]{1})\\.");
        patterns.put("Question answers stem", "^[À-ÿA-z]{1}\\.(.*)");
        patterns.put("Question answers right", "^[Aa]nswer:[\\s]{0,}(.*)\\.");
        patterns.put("Question answers right stem", "^[Aa]nswer:[\\s]{0,}[A-zÀ-ÿ][,|\\.](.*)");
        patterns.put("Question answers right symbols", "^[Aa]nswer:[\\s]{0,}([A-zÀ-ÿ])[,|\\.]");
        patterns.put("Question difficulty", "^[Dd]ifficulty:[\\s]{0,}(.*)[\\.]{0,}");*/

        return patterns;
    }
}