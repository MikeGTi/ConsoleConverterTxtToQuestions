package DataExportImport;

import java.io.*;
import java.util.Arrays;

public class ReadObjects {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("out\\questions.bin"))) {
            /*FileInputStream fis = new FileInputStream("out\\questions.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);*/

            /*Question[] questions = new Question[ois.readInt()];
            for (int i = 0; i < questions.length; i++) {
                questions[i]=(Question)ois.readObject();
            }*/
            Question[] questions = (Question[])ois.readObject();
            System.out.println(Arrays.toString(questions));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
