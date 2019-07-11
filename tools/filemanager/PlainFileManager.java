package tools.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class PlainFileManager {

    /* ATTRIBUTTES __________________________________________________________ */
    private String pathFile;

    /* CONSTRUCTORS _________________________________________________________ */
    public PlainFileManager(String pathFile) {
        this.pathFile = pathFile;
    }

    /* METHODS ______________________________________________________________ */
    public String read() {
        String text = "";
        String line;
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(new File(this.pathFile)));
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
            File file = new File(this.pathFile);
            FileWriter fileWriter;

            if (!file.exists()) {
                file.createNewFile();
            }

            fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /* ______________________________________________________________________ */
    private void append(String text) {
        try {
            File file = new File(this.pathFile);
            FileWriter fileWriter;

            if (!file.exists()) {
                this.write(text);
            } else {
                fileWriter = new FileWriter(file, true);
                fileWriter.write(text);
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /* ______________________________________________________________________ */
    public void moveTo(String newPathFile) {
        try {
            Files.move(
                    Paths.get(this.pathFile),
                    Paths.get(newPathFile),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException ex) {
            System.out.println("Can not move " + this.pathFile + " to " + newPathFile);
        }
    }
}
