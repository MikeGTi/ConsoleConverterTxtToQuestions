package DataExportImport;

import java.lang.reflect.Field;
import java.lang.Class;

public class Printer {
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
                // print value as html
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
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
        new IOdata().writeFile(_path,_txt);
    }
}
