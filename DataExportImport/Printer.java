package DataExportImport;

import java.lang.reflect.Field;
import java.lang.Class;

public class Printer {
    /*public static void main(String[] args) {
        Printer printer = new Printer("");
        //QuestionMultipleChoice question = (QuestionMultipleChoice) new QuestionFactory().newQuestion(QuestionTypes.MultipleChoice);
        QuestionMultipleChoice question2 = new QuestionDataForTesting().getQuestionObject(new QuestionMultipleChoice());
        printer.printAsHtml(question2);
    }*/

    private String context;

    public Printer(String _context) {
        this.context = _context;
    }

    private void printAsHtml(Object _object){
        Class aClass = _object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field: fields) {
            try {
                Object value = field.get(_object);
                System.out.println(value.toString());
                // print value as html
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String fieldsToString(Object object) {
        StringBuilder stringBuilder = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        stringBuilder.append( object.getClass().getName() );
        stringBuilder.append( " Object {" );
        stringBuilder.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Class<?> current = object.getClass();
        /*while(current.getSuperclass()!=null){ // we don't want to process Object.class
            // do something with current's fields
            current = current.getSuperclass();
        }*/
        Field[] fields = current.getClass().getDeclaredFields();
        //Field[] fields = this.getClass().getDeclaredFields();
        //Field[] fields = this.getClass().getFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            stringBuilder.append("  ");
            try {
                //field.setAccessible(true);
                stringBuilder.append( field.getName() );
                stringBuilder.append(": ");
                //requires access to private field:
                stringBuilder.append( field.get(object) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            stringBuilder.append(newLine);
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public void printLn() {
        System.out.println(context);
    }

    public void print() {
        System.out.print(context);
    }

    public void printAsTxtFile(String _path, String _txt) {
        /*String[] lines = context.split("\n");
        for (String line : lines) {
            System.out.println(line);
        }*/
        new FileIOdata().writeFile(_path,_txt);
    }
}
