package DataExportImport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

import static DataExportImport.IOdata.readFile;

public class ParserQuestion {
    public static  ArrayList<? extends Question> parseFromFile(String _path){
        return GetQuestionsObjectsList(GetQuestionsTxtBlocks(readFile(_path)));
    }

    public static  ArrayList<? extends Question> parseFromTxt(String _txt){
        return GetQuestionsObjectsList(GetQuestionsTxtBlocks(_txt));
    }

    private static String[] GetQuestionsTxtBlocks(String _txt){
        String[] questionsStr = GetMatches(Pattern.compile(GetRegexPatternsForQuestions().get("Questions"), Pattern.MULTILINE), _txt, 1).toArray(new String[0]);
        if (questionsStr.length == 0) {
            questionsStr = GetMatches(Pattern.compile(GetRegexPatternsForQuestions().get("Questions wo 'Question type'"), Pattern.MULTILINE), _txt, 1).toArray(new String[0]);
        }
        return questionsStr;
    }

    private static ArrayList<? extends Question> GetQuestionsObjectsList(String[] questionsTxt) {
        ArrayList<Question> result = new ArrayList<>();
        HashMap<String, String> quePatterns = GetRegexPatternsForQuestions();
        DataExportImport.QuestionFactory queFactory = new QuestionFactory();

        for (String queItm: questionsTxt) {
            Question que = queFactory.CreateQuestion(
                                                    QuestionTypes.valueOf(
                                                        (GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question type"),Pattern.MULTILINE),queItm,1),0, "Multiple Choice")).replaceAll("\\s+","")));

            que.setTitle(GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question title"),Pattern.MULTILINE),queItm,1),0,""));
            que.setUUID(GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question uuid"),Pattern.MULTILINE),queItm,1),0,""));

            que.setNumber(Integer.parseInt(GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question number"),Pattern.MULTILINE),queItm,1),0,"0")));

            que.setStem(GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question stem"),Pattern.MULTILINE),queItm,1),0,""));
            que.setAnswers(GetHashMap(GetMatches(Pattern.compile(quePatterns.get("Question answers symbols"),Pattern.MULTILINE),queItm,1),
                                      GetMatches(Pattern.compile(quePatterns.get("Question answers stem"),Pattern.MULTILINE),queItm,1)));
            que.setRightAnswers(GetHashMap(GetMatches(Pattern.compile(quePatterns.get("Question answers right symbols"),Pattern.MULTILINE),queItm,1),
                                           GetMatches(Pattern.compile(quePatterns.get("Question answers right stem"),Pattern.MULTILINE),queItm,1)));
            que.setDifficulty(Difficulty.valueOf(GetMatch(GetMatches(Pattern.compile(quePatterns.get("Question difficulty"),Pattern.MULTILINE),queItm,1),0,"Easy").toUpperCase()));

            result.add(que);
        }
        return result;
    }

    private static <K, V> HashMap<K, V> GetHashMap(ArrayList<K> l1, ArrayList<V> l2){
        HashMap<K, V> res = new HashMap<K, V>();
        Iterator<K> key = l1.iterator();
        Iterator<V> value = l2.iterator();
        while (key.hasNext() || value.hasNext()) res.put(key.next(), value.next());
        return res;
    }

    private static ArrayList<String> GetMatches(Pattern _pattern, String _txt, int _groupNumber){
        var matcher1 = _pattern.matcher(_txt);
        var matches = new ArrayList<String>();
        while (matcher1.find()) {
            try {
                matches.add(matcher1.group(_groupNumber));
            } catch (IndexOutOfBoundsException e) {
                matches.add("");
            }
        }
        return matches;
    }

    private static String GetMatch(ArrayList<String> _matches, int _index, String _defaultValue) {
        String res = _defaultValue;
        try{
            res = _matches.get(_index);
        }catch(IndexOutOfBoundsException e) {
            //System.err.println("Match not found");
        }
        return res;
    }

    private static HashMap<String, String> GetRegexPatternsForQuestions() {
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
        patterns.put("Question answers right", "^[Aa]nswer:(.*)");
        patterns.put("Question answers right symbols", "^[Aa]nswer:.*([À-ÿA-z]{1})\\.");
        patterns.put("Question answers right stem", "^[Aa]nswer:.*[À-ÿA-z]{1}\\.(.*)");
        patterns.put("Question difficulty", "^[Dd]ifficulty:(.*)");

        return patterns;
    }
}