package tools.binaryfilemanager;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BinaryFileManager {

    /* ATTRIBUTTES __________________________________________________________ */
    private final String filePath;

    /* CONSTRUCTORS _________________________________________________________ */
    public BinaryFileManager(String filePath) {
        this.filePath = filePath;
    }

    /* METHODS ______________________________________________________________ */
    private void write(Object object) {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(this.filePath);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(object);

        } catch (FileNotFoundException ex) {
            System.err.println("Write Error: *File " + this.filePath + " don't found*");
        } catch (IOException ex) {
            System.err.println("Write Error: *" + ex + "*");
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }

    /* ______________________________________________________________________ */
    public void add(Object object) {
        ObjectAppendStream objectAppendStream = null;
        FileOutputStream fileOutputStream = null;
        File file = new File(this.filePath);

        if (!file.exists()) {
            this.write(object);
            return;
        }

        try {
            fileOutputStream = new FileOutputStream(this.filePath, true);
            objectAppendStream = new ObjectAppendStream(fileOutputStream);

            objectAppendStream.writeObject(object);

        } catch (FileNotFoundException ex) {
            System.err.println("Add Error: *File " + this.filePath + " don't found*");
        } catch (IOException ex) {
            System.err.println("Add Error: *" + ex + "*");
        } finally {
            try {
                if (objectAppendStream != null) {
                    objectAppendStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(BinaryFileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* ______________________________________________________________________ */
    public ArrayList<Object> read() {
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;

        ArrayList<Object> objects = new ArrayList<>();

        try {
            fileInputStream = new FileInputStream(this.filePath);
            objectInputStream = new ObjectInputStream(fileInputStream);

            while (true) {
                objects.add(objectInputStream.readObject());
            }

        } catch (EOFException ex) {
            //System.err.println("Read Error: *" + ex + "*");
        } catch (FileNotFoundException ex) {
            System.err.println("Read Error: *File " + this.filePath + " don't found*");
        } catch (IOException ex) {
            //System.err.println("Read Error: *" + ex + "*");
        } catch (ClassNotFoundException ex) {
            System.err.println("Read Error: *Class don't found*");
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
            }
        }

        return objects;
    }

    public void clear() {
        File file = new File("accounts.dat");
        file.delete();
    }
}
