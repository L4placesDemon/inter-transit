package tools.components;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;

public abstract class Dialog extends JDialog {

    /* ATTRIBUTES ___________________________________________________________ */
    public static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);
    public static final int OK_OPTION = 0;
    public static final int CANCEL_OPTION = 1;

    private int dialogResultValue = Dialog.CANCEL_OPTION;

    /* CONSTRUCTORS _________________________________________________________ */
    public Dialog() {
        super(new JFrame(), true);
        
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initEvents() {
        // Dialog Events --------------------------------------------------------
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                cancelAction();
            }
        });
    }

    /* ______________________________________________________________________ */
    public int showDialog() {
        this.setVisible(true);
        return this.getDialogResultValue();
    }
    
    /* ______________________________________________________________________ */
    public int showTestDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        return this.showDialog();
    }

    /* ______________________________________________________________________ */
    public void okAction() {
        this.setDialogResultValue(Dialog.OK_OPTION);
    }

    /* ______________________________________________________________________ */
    public void cancelAction() {
        this.setDialogResultValue(Dialog.CANCEL_OPTION);
    }

    /* GETTERS ______________________________________________________________ */
    public int getDialogResultValue() {
        return this.dialogResultValue;
    }

    /* SETTERS ______________________________________________________________ */
    public void setDialogResultValue(int dialogResultValue) {
        this.dialogResultValue = dialogResultValue;
    }
}
