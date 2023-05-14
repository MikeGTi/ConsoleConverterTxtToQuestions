package DataExportImport;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class IOdata {

    public static void writeObjectToFile(String _path, Object _object){
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(_path))){
            objectOutputStream.writeObject(_object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> ArrayList<T> readObjectFromFile(String _path){
        ArrayList<T> objects = new ArrayList<T>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(_path))) {
            objects = (ArrayList<T>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    public static String readFile(String _path) {

        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = "\n"; //System.getProperty("line.separator");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(_path))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(ls);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    public static void writeFile(String _path, String _txt){
        try (FileOutputStream fileOutputStream = new FileOutputStream(_path)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8)) {
                writer.write(_txt);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

