package tools.components;

import javax.swing.JOptionPane;

public class DialogPane extends JOptionPane{

    /* ATTRIBUTES ___________________________________________________________ */
    public final static int PLAIN = JOptionPane.PLAIN_MESSAGE;
    public final static int ERROR = JOptionPane.ERROR_MESSAGE;
    public final static int INFORMATION = JOptionPane.INFORMATION_MESSAGE;
    public final static int WARNING = JOptionPane.WARNING_MESSAGE;
    public final static int QUESTION = JOptionPane.QUESTION_MESSAGE;

    /* METHODS ______________________________________________________________ */
    public static void show(String message) {
        show(message, -1);
    }

    /* ______________________________________________________________________ */
    public static void show(String message, int type) {
        String title = type == ERROR ? "Error" : type == INFORMATION ? "Information"
                : type == WARNING ? "Warning" : type == QUESTION ? "Question" : "";
        showMessageDialog(null, message, title, type);
    }

    /* ______________________________________________________________________ */
    public static void show(String title, String message, int type) {
        showMessageDialog(null, message, title, type);
    }

    /* ______________________________________________________________________ */
    public static int yesNoOption(String message) {
        return showOptionDialog(null,
                message, "Option", YES_NO_OPTION, QUESTION, null, null, 0);
    }

    /* ______________________________________________________________________ */
    public static int yesNoCancelOption(String title, String message) {
        return showOptionDialog(null,
                message, title, YES_NO_CANCEL_OPTION, QUESTION, null, null, 0);
    }

    /* ______________________________________________________________________ */
    public static String input(String title, String message) {
        return showInputDialog(null, message, title, PLAIN);
    }

    /* ______________________________________________________________________ */
    public static String selectedInput(String title, String message, Object text) {
        return (String) showInputDialog(null, message, title, PLAIN, null, null, text);
    }
}
