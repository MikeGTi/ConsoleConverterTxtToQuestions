package BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserQuestion {

    private String text;

    public ParserQuestion(String text) {
        this.text = text;
    }

    public ArrayList<? extends Question> getList(){
        return getQuestionsObjectsList(GetQuestionsTextBlocks());
    }

    private String[] GetQuestionsTextBlocks(){
        String[] questionTextBlocks =
                GetMatches(Pattern.compile(getRegexPatternsForQuestions().get("Questions"), Pattern.MULTILINE), this.text, 1).toArray(new String[0]);

        if (questionTextBlocks.length == 0) {
            questionTextBlocks =
                GetMatches(Pattern.compile(getRegexPatternsForQuestions().get("Questions wo 'Question type'"), Pattern.MULTILINE), this.text, 1).toArray(new String[0]);
        }

        return questionTextBlocks;
    }

    private static ArrayList<? extends Question> getQuestionsObjectsList(String[] questionTextBlocks) {
        ArrayList<Question> result = new ArrayList<>();
        HashMap<String, String> quePatterns = getRegexPatternsForQuestions();
        QuestionFactory queFactory = new QuestionFactory();

        for (String queItm: questionTextBlocks) {
            Question que = queFactory.newQuestion(
                                                    QuestionTypes.valueOf(
                                                        (GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question type"),Pattern.MULTILINE),queItm,1),0, "Multiple Choice")).replaceAll("\\s+","")));
            //set fields
            que.setTitle(GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question title"),Pattern.MULTILINE),queItm,1),0,""));
            que.setUUID(GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question uuid"),Pattern.MULTILINE),queItm,1),0,""));

            que.setNumber(Integer.parseInt(GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question number"),Pattern.MULTILINE),queItm,1),0,"0")));

            que.setStem(GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question stem"),Pattern.MULTILINE),queItm,1),0,""));
            que.setAnswers(GetHashMap(GetMatches(Pattern.compile(quePatterns.get("Question answers symbols"),Pattern.MULTILINE),queItm,1),
                                      GetMatches(Pattern.compile(quePatterns.get("Question answers stem"),Pattern.MULTILINE),queItm,1)));
            que.setRightAnswers(getCharacterArray(GetMatches(Pattern.compile(quePatterns.get("Question answers right"),Pattern.MULTILINE),queItm,1).get(0)));
            que.setDifficulty(Difficulty.valueOf(GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question difficulty"),Pattern.MULTILINE),queItm,1),0,"Easy").toUpperCase()));

            result.add(que);
        }
        return result;
    }

    private static Character[] getCharacterArray(String string){
        String string1 = string.replaceAll("[!\\D] ","");
        //string1 = Arrays.toString(string1.split(","));
        Character[] charObjectArray = string1.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        return charObjectArray;
    }

    private static <K, V> HashMap<K, V> GetHashMap(ArrayList<K> l1, ArrayList<V> l2){
        HashMap<K, V> res = new HashMap<K, V>();
        Iterator<K> key = l1.iterator();
        Iterator<V> value = l2.iterator();
        while (key.hasNext() || value.hasNext()) res.put(key.next(), value.next());
        return res;
    }

    private static ArrayList<String> GetMatches(Pattern _pattern, String _txt, int _groupNumber){
        Matcher matcher = _pattern.matcher(_txt);
        ArrayList<String> matches = new ArrayList<String>();
        while (matcher.find()) {
            try {
                matches.add(matcher.group(_groupNumber));
            } catch (IndexOutOfBoundsException e) {
                matches.add("");
            }
        }
        return matches;
    }

    private static String GetMatch(ArrayList<String> _matches, int _index, String _defaultValue) {
        String res;
        try{
            res = _matches.get(_index);
        }catch(IndexOutOfBoundsException e) {
            res = _defaultValue;
            //System.err.println("Match not found");
        }
        return res;
    }

    private static HashMap<String, String> getRegexPatternsForQuestions() {
        final HashMap<String, String> patterns = new HashMap<>();

        //EOF $(?![\r\n])
        //blank line ^$
        // named group add: (?<groupName>groupPattern)
        //patterns.put("Questions", "(^Question type(.*\\n)+?(^$|.*\\.$|^\\n|\\z|\\Z)+?)")
        patterns.put("Questions", "(^Question type(.*\\n)+?(^\\n|\\z|\\Z|[Aa]nswer.*\\.)+?)");
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
        patterns.put("Question difficulty", "^[Dd]ifficulty:[\\s]{0,}(.*)[\\.]{0,}");

        return patterns;
    }
}