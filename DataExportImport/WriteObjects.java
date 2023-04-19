package DataExportImport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObjects {
    public static void main(String[] args) throws FileNotFoundException {
        //Question que1 = new TestData().GetQuestionObject();
        Question[] questions = {new TestData().GetQuestionObject(), new TestData().GetQuestionObject(), new TestData().GetQuestionObject()};

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("out\\questions.bin"))){
            /*oos.writeInt(questions.length);
            for (Question que1 : questions) {
                oos.writeObject(que1);
            }*/
            oos.writeObject(questions);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

