package utilities;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.ImageIcon;

public interface Utilities {

    /* ______________________________________________________________________ */
    public static void output(String string) {
        Calendar calendar = Calendar.getInstance();
        System.out.println("[" + calendar.get(Calendar.HOUR_OF_DAY)
                + ":" + calendar.get(Calendar.MINUTE)
                + ":" + calendar.get(Calendar.SECOND)
                + ":" + calendar.get(Calendar.MILLISECOND)
                + "] " + string);
    }

    /* ______________________________________________________________________ */
    public static ImageIcon getIcon(String iconPath) { // /Package/Image.ext
        return iconPath != null ? new ImageIcon(Utilities.class.getResource(iconPath)) : null;
    }

    /* ______________________________________________________________________ */
    public static Image getImage(String imagePath) { // /Package/Image.ext
        return getIcon(imagePath).getImage();
    }

    /* ______________________________________________________________________ */
//    public static Image getImage(String imagePath, JComponent component) {
//        return getImage(imagePath).getScaledInstance(
//                component.getWidth(), component.getHeight(), Image.SCALE_SMOOTH);
//    }

    /* ______________________________________________________________________ */
    public static Image getImage(String imagePath, int width, int height) {
        return getImage(imagePath).getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    /* ______________________________________________________________________ */
//    public static ImageIcon getImageIcon(String imagePath) {
//        return new ImageIcon(getImage(imagePath));
//    }

    /* ______________________________________________________________________ */
//    public static ImageIcon getImageIcon(String imagePath, JComponent component) {
//        return new ImageIcon(getImage(imagePath, component));
//    }

    /* ______________________________________________________________________ */
    public static ImageIcon getImageIcon(String imagePath, int width, int height) {
        return new ImageIcon(getImage(imagePath, width, height));
    }

    /* ______________________________________________________________________ */
//    public static ImageIcon getImageIcon(Image image, int width, int height) {
//        return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
//    }

    /* ______________________________________________________________________ */
//    public static void setClipboard(String string) {
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
//                new StringSelection(string),
//                (Clipboard clpbrd, Transferable t) -> {
//                });
//    }

    /* ______________________________________________________________________ */
//    public static String getClipboard() {
//        String result = "";
//        Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
//
//        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
//            try {
//                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
//            } catch (UnsupportedFlavorException | IOException ex) {
//                System.out.println(ex);
//            }
//        }
//        return result;
//    }
    /* ______________________________________________________________________ */
    public static String getFileText(File file) {
        String text = "";
        String line;
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            line = bufferedReader.readLine();

            while (line != null) {
                text += line;
                line = bufferedReader.readLine();

                if (line != null) {
                    text += "\n";
                }
            }
            bufferedReader.close();

        } catch (IOException e) {
        }
        return text;
    }

    /* ______________________________________________________________________ */
    public static void command(String command) {
        
    }
}
