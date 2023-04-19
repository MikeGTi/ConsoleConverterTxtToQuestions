package DataExportImport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserQuestion {

    public static void main(String[] args) {
        TestData test = new TestData("D:\\JavaProjects\\CreateQuestionsTest\\Task\\Input\\билеты_тест.txt");
        //Question que1 = test.GetQuestionObject();
        String que2 = test.GetQuestionsString();
        //System.out.println(que2);

        //Question[] questions = new Question[3];
        //System.out.println(questions.length);

        HashMap<String, String> regexRulesForQuestions = GetRegexRulesForQuestions();

        /*Pattern pattern1 = Pattern.compile(GetRegexRulesForQuestions().get("Questions"), Pattern.MULTILINE);
        Matcher matcher1 = pattern1.matcher(que2);*/

        /*ArrayList<String> matches = new ArrayList<String>();
        while (matcher1.find()) {
            matches.add(matcher1.group());
        }
        System.out.println(matches);*/

        //test 1,2,3
        //String key = "Questions";
        //String key = "Question numbers";
        //String key = "Question stem";
        //String key = "Question answers";
        //String key = "Question answer symbols";
        /*String key = "Question fields by group";
        System.out.println(key + ": " +
                ConvertStringToInt(GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(key),
                                Pattern.MULTILINE),
                        que2,
                        0)));*/

        /*//All
        String[] key = regexRulesForQuestions.keySet().toArray(new String[0]);
        for (String queItm:key) {
            System.out.println(queItm + ": " +
                    ConvertStringToInt(GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(queItm),
                                                    Pattern.MULTILINE),
                            que2,
                            1)));
        }*/

        //for init one question
        String[] questions = GetMatches(Pattern.compile(GetRegexRulesForQuestions().get("Questions"), Pattern.MULTILINE), que2, 1).toArray(new String[0]);
        //System.out.println(questions);

        System.out.println(GetQuestionsList(questions));
    }

    private static ArrayList<Question> GetQuestionsList(String[] questionsTxt) {
        ArrayList<Question> result = new ArrayList<Question>();
        //Max
        //QuestionType _queType, int _queNumber, String _queTitle, String _stem, HashMap<String, String> _answers, HashMap<String, String> _rightAnswers, Difficulty _difficulty
        String[] questionPartPatterns = {"Question number","Question stem",
                                         "Question answers symbols","Question answers stem",
                                         "Question answers right symbols","Question answers right stem",
                                         "Question difficulty"};
        for (String queItm: questionsTxt) {
            result.add(new Question(
                    QuestionType.MultipleChoice,
                    Integer.parseInt(GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[0]),Pattern.MULTILINE),queItm,1).get(0)),
                    "Question " + GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[0]),Pattern.MULTILINE),queItm,1).get(0),
                    GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[1]),Pattern.MULTILINE),queItm,1).get(0),
                    GetHashMapFromTwoList(GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[2]),Pattern.MULTILINE),queItm,1),
                                          GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[3]),Pattern.MULTILINE),queItm,1)),
                    GetHashMapFromTwoList(GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[4]),Pattern.MULTILINE),queItm,1),
                                          GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[5]),Pattern.MULTILINE),queItm,1)),
                    Difficulty.valueOf(GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[6]),Pattern.MULTILINE),queItm,1).get(0))));
        }

        //Min
        /*String[] questionPartPatterns = {"Question number","Question stem","Question answers symbols","Question answers stem",
                                         "Question answers right symbols","Question answers right stem","Question difficulty"};

        for (String queItm: questionsTxt) {
            result.add(new Question(
                    Integer.parseInt(GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[0]),Pattern.MULTILINE),queItm,1).get(0)),
                    GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[1]),Pattern.MULTILINE),queItm,1).get(0),
                    GetHashMapFromTwoList(GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[2]),Pattern.MULTILINE),queItm,1),
                                          GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[3]),Pattern.MULTILINE),queItm,1)),
                    GetHashMapFromTwoList(GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[4]),Pattern.MULTILINE),queItm,1),
                                          GetMatches(Pattern.compile(GetRegexRulesForQuestions().get(questionPartPatterns[5]),Pattern.MULTILINE),queItm,1))));
        }*/
        return result;
    }

    private static HashMap<String, String> GetHashMapFromTwoList(ArrayList<String> l1,ArrayList<String>l2){
        HashMap<String, String> res = new HashMap<String, String>();
        for (int i = 0; i < l1.size(); i++) {
            res.put(l1.get(i), l2.get(i));
        }
        return res;
    }

    private static ArrayList ConvertStringToInt(ArrayList<String> _list){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (String stringValue : _list) {
            try {
                result.add(Integer.parseInt(stringValue.trim()));
            } catch (NumberFormatException e) {
                result.add(Integer.valueOf("0"));
                //result.add(Integer.valueOf(""));
                //System.out.println("Could not parse " + nfe);
                //Log.w("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
            }
        }
        return result;
    }

    private static ArrayList<String> GetMatches(Pattern _pattern, String txt, int groupNumber){
        Matcher matcher1 = _pattern.matcher(txt);
        ArrayList<String> matches = new ArrayList<String>();
        while (matcher1.find()) {
            try {
                matches.add(matcher1.group(groupNumber));
            } catch (IndexOutOfBoundsException e) {
                matches.add("");
            }
        }
        return matches;
    }

    private static HashMap<String, String> GetRegexRulesForQuestions() {
        HashMap<String, String> regexRules = new HashMap<String, String>();

        //EOF $(?![\r\n])
        //blank line ^$
        // named group add: (?<groupName>groupPattern)
        regexRules.put("Questions", "(^[0-9]{1,3}\\.(.*\\n)+?(^$|.*\\.$|^\\n|\\z|\\Z)+?)");
        regexRules.put("Question fields by group", "(^[0-9]{1,3})\\.(.*\\n)+?(^[А-яA-z]{1}\\..*[\\n{1,2}]){1,9}[Aa]nswer:.*[\\n{1,2}][Dd]ifficulty:(.*)\\n");
        regexRules.put("Question number", "(^[0-9]{1,3})\\.");
        regexRules.put("Question stem", "^[0-9]{1,3}\\.(.*\\n)+?^[А-яA-z]{1}\\.");
        regexRules.put("Question answers", "(^[А-яA-z]{1}\\..*)");
        regexRules.put("Question answers symbols", "(^[А-яA-z]{1})\\.");
        regexRules.put("Question answers stem", "^[А-яA-z]{1}\\.(.*)");
        regexRules.put("Question answers right", "^[Aa]nswer:(.*)");
        regexRules.put("Question answers right symbols", "^[Aa]nswer:.*([А-яA-z]{1})\\.");
        regexRules.put("Question answers right stem", "^[Aa]nswer:.*[А-яA-z]{1}\\.(.*)");
        regexRules.put("Question difficulty", "^[Dd]ifficulty:(.*)");

        return regexRules;
    }
}