package tools.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PlainFileManager {

    /* ATTRIBUTTES __________________________________________________________ */
    private File file;

    /* CONSTRUCTORS _________________________________________________________ */
    public PlainFileManager(File file) {
        this.file = file;
    }

    /* ______________________________________________________________________ */
    public PlainFileManager(String filePath) {
        this(new File(filePath));
    }

    /* METHODS ______________________________________________________________ */
    public String read() {
        String text = "";
        String line;
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(this.file));
            line = bufferedReader.readLine();

            while (line != null) {
                text += line;
                line = bufferedReader.readLine();

                if (line != null) {
                    text += '\n';
                }
            }
            bufferedReader.close();

        } catch (IOException e) {
        }
        return text;
    }

    /* ______________________________________________________________________ */
    public void write(String text) {
        try {
            FileWriter fileWriter;

            if (!this.file.exists()) {
                this.file.createNewFile();
            }

            fileWriter = new FileWriter(this.file);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /* ______________________________________________________________________ */
//    private void append(String text) {
//        try {
//            FileWriter fileWriter;
//
//            if (!this.file.exists()) {
//                this.write(text);
//            } else {
//                fileWriter = new FileWriter(this.file, true);
//                fileWriter.write(text);
//                fileWriter.close();
//            }
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }
}
