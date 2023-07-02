package dataImportExport;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

/**
 * @author carper
 * https://forum.ixbt.com/topic.cgi?id=26:42233
 *
 */
public class RtfToTxt {

    /**
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws IOException {
        convertToTxt(args[0], args[1]);
    }

    public static void convertToTxt(final String pathToRtf, final String pathToTxt) throws IOException  {

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(pathToTxt), "cp866");
        FileInputStream fileInputStream = new FileInputStream(pathToRtf);
        RTFEditorKit rtf = new RTFEditorKit();
        Document doc = new DefaultStyledDocument();

        try {

            rtf.read(new BufferedReader(new InputStreamReader(fileInputStream)), doc, 0);
            String plainTextString = doc.getText(0, doc.getLength()); //always UTF-16
            outputStreamWriter.write(new String(plainTextString.getBytes("ISO-8859-1"), "cp1251"));

        } catch (IOException | BadLocationException e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
            outputStreamWriter.close();
        }
    }

}
