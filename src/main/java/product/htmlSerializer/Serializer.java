package product.htmlSerializer;

public class Serializer {
    public static void main(String[] args) {

        TestObject testObject = new TestObject("Mike",2,'A');
        ObjectToHTML objectToHTML = new ObjectToHTML(testObject.getHtmlTagFieldMap());
        System.out.println(objectToHTML.convertToHTML(testObject));

        /*Question testQuestion = new QuestionDataForTesting().initQuestionObject(new QuestionMultipleChoice());
        ObjectToHTML objectToHTML = new ObjectToHTML(testQuestion.getHtmlTagFieldMap());
        System.out.println(objectToHTML.convertToHTML(testQuestion));*/
    }

}
