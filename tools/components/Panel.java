package tools.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class Panel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -2018109515452980767L;

    public static final int OK_OPTION = 0;
    public static final int CANCEL_OPTION = 1;

    private int resultValue = Dialog.CANCEL_OPTION;

    /* METHODS ______________________________________________________________ */
    public void okAction() {
        this.setResultValue(Dialog.OK_OPTION);
    }

    /* ______________________________________________________________________ */
    public void cancelAction() {
        this.setResultValue(Dialog.CANCEL_OPTION);
    }

    /* ______________________________________________________________________ */
    public int dispose() {
        return this.getResultValue();
    }

    /* ______________________________________________________________________ */
    public int showTestDialog() {
        Dialog dialog = new Dialog();

        dialog.setLayout(new BorderLayout());
        dialog.setSize(630, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setMinimumSize(new Dimension(380, getHeight() / 2));
//        dialog.setTitle();
        dialog.setResizable(false);

        dialog.add(this);

        return dialog.showTestDialog();
    }

    /* ______________________________________________________________________ */
    public abstract JButton getCloseButton();

    /* GETTERS ______________________________________________________________ */
    public int getResultValue() {
        return this.resultValue;
    }

    /* SETTERS ______________________________________________________________ */
    public void setResultValue(int resultValue) {
        this.resultValue = resultValue;
    }
}
