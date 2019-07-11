package tools.components;

import javax.swing.JOptionPane;

public class DialogPane extends JOptionPane {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 286880484650781705L;

    /* METHODS ______________________________________________________________ */
    public static void showMessage(String title, Object message, int optionType) {
        showMessageDialog(null, message, title, optionType);
    }

    /* ______________________________________________________________________ */
    public static void showMessage(Object message, int optionType) {
        String title = optionType == ERROR
                ? "Error" : optionType == INFORMATION_MESSAGE
                        ? "Information" : optionType == WARNING_MESSAGE
                                ? "Warning" : optionType == QUESTION_MESSAGE
                                        ? "Question" : "";
        DialogPane.showMessage(title, message, optionType);
    }

    /* ______________________________________________________________________ */
    public static void showMessage(Object message) {
        DialogPane.showMessage(message, -1);
    }

    /* ______________________________________________________________________ */
    public static String showInput(String title, Object message) {
        return showInputDialog(null, message, title, PLAIN_MESSAGE);
    }

    /* ______________________________________________________________________ */
    public static String showSelectedInput(String title, Object message, Object text) {
        return (String) showInputDialog(
                null, message, title, PLAIN_MESSAGE, null, null, text
        );
    }

    /* ______________________________________________________________________ */
    public static int showOption(String title, Object message, int optionType) {
        return showOptionDialog(null,
                message, title, optionType, QUESTION_MESSAGE, null, null, 0);
    }

    /* ______________________________________________________________________ */
    public static int showOption(String title, String message) {
        return showOption(title, message, YES_NO_OPTION);
    }

    /* ______________________________________________________________________ */
    public static int showOption(
            String title, Object message, int optionType,
            int messageType, Object[] options, Object initialValue
    ) {
        return showOptionDialog(
                null, message, title, optionType,
                messageType, null, options, initialValue
        );
    }

    /* ______________________________________________________________________ */
    public static int showConfirm(Object message, String title, int optionType) {
        return showConfirmDialog(null, message, title, optionType, PLAIN_MESSAGE);
    }
}
