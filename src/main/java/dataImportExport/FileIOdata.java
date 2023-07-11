package dataImportExport;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class FileIOdata {

    public static void main(String[] args) {
        printFilesFromFolder("D:/JavaProjects/CreateQuestionsTest/TechTask/Input");
    }

    public static void writeObjectToFile(String _path, Object _object){
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(_path))){
            objectOutputStream.writeObject(_object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> ArrayList<T> readObjectFromFile(String _path){
        ArrayList<T> objects;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(_path))) {
            objects = (ArrayList<T>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    public static void printFilesFromFolder(String folderPath){
        Path path = Paths.get(folderPath);
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path oneF, BasicFileAttributes attrs) {
                    System.out.println("FILE:" + oneF);
                    if (Files.exists(oneF)) {
                        System.out.println("EXISTS:" + oneF);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFile(String _path, String charSet) {
        String line;
        String ls = "\n"; //System.getProperty("line.separator");
        StringBuilder stringBuilder = new StringBuilder();
        //try (BufferedReader bufferedReader = new BufferedReader(new FileReader(_path))) {
        try (BufferedReader bufferedReader = new BufferedReader(
                                                                new InputStreamReader(
                                                                        Files.newInputStream(Path.of(_path)), charSet.toString()))) { //new FileInputStream(_path), charSet.toString()
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

