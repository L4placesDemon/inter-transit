package tools;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import javax.swing.ImageIcon;

public interface Tools {

    /* ATTRIBUTES ___________________________________________________________ */
    public final static String DEFAULT_IMAGE_PATH = "/images/";

    /* METHODS ______________________________________________________________ */
    public static void output(String string) {
        Calendar calendar = Calendar.getInstance();
        System.out.println("[" + calendar.get(Calendar.HOUR_OF_DAY)
                + ":" + calendar.get(Calendar.MINUTE)
                + ":" + calendar.get(Calendar.SECOND)
                + ":" + calendar.get(Calendar.MILLISECOND)
                + "] " + string);
    }

    /* ______________________________________________________________________ */
    public static ImageIcon getIcon(String iconPath) { // Image
        return iconPath != null
                ? new ImageIcon(Tools.class.getResource(
                        Tools.DEFAULT_IMAGE_PATH
                        + iconPath
                        + ".png"
                ))
                : null;
    }

    public static ImageIcon getAbsoluteIcon(String iconPath) { // /Package/Image.ext
        return iconPath != null
                ? new ImageIcon(iconPath)
                : null;
    }

    /* ______________________________________________________________________ */
    public static Image getImage(String imagePath) {
        return Tools.getIcon(imagePath).getImage();
    }

    /* ______________________________________________________________________ */
    public static Image getAbsoluteImage(String imagePath) {
        return Tools.getAbsoluteIcon(imagePath).getImage();
    }

    /* ______________________________________________________________________ */
    public static Image getImage(String imagePath, int width, int height) {
        return Tools.getImage(imagePath).getScaledInstance(
                width,
                height,
                Image.SCALE_SMOOTH
        );
    }

    /* ______________________________________________________________________ */
    public static Image getAbsoluteImage(String imagePath, int width, int height) {
        return Tools.getAbsoluteImage(imagePath).getScaledInstance(
                width,
                height,
                Image.SCALE_SMOOTH
        );
    }

    /* ______________________________________________________________________ */
    public static ImageIcon getImageIcon(String imagePath, int width, int height) {
        return new ImageIcon(Tools.getImage(imagePath, width, height));
    }

    /* ______________________________________________________________________ */
    public static ImageIcon getAbsoluteImageIcon(String imagePath, int width, int height) {
        return new ImageIcon(Tools.getAbsoluteImage(imagePath, width, height));
    }

    /* ______________________________________________________________________ */
    public static ImageIcon getImageIcon(String imagePath) {
        return new ImageIcon(Tools.getImage(imagePath));
    }

    /* ______________________________________________________________________ */
    public static ImageIcon getAbsoluteImageIcon(String imagePath) {
        return new ImageIcon(Tools.getAbsoluteImage(imagePath));
    }

    /* ______________________________________________________________________ */
    public static String getResource(String source) {
        return Tools.class.getResource(source).getFile();
    }

    /* ______________________________________________________________________ */
    public static void setClipboard(String string) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                new StringSelection(string),
                (Clipboard clpbrd, Transferable t) -> {
                });
    }

    /* ______________________________________________________________________ */
    public static String getClipboard() {
        String result = "";
        Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);

        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException ex) {
                System.out.println(ex);
            }
        }
        return result;
    }

    /* ______________________________________________________________________ */
    public static String command(String command) throws IOException {
        String text = "";

        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
        builder.redirectErrorStream(true);
        Process p = builder.start();

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = r.readLine();

        while (line != null) {
            text += line;
            line = r.readLine();

            if (line != null) {
                line += '\n';
            }
        }
        return text;
    }
}
