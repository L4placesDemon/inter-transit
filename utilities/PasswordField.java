package utilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class PasswordField extends JPasswordField {

    /* ATTRIBUTES ___________________________________________________________ */
    private UndoManager undoManager;

    /* CONSTRUCTORS _________________________________________________________ */
    public PasswordField() {
        initEvents();
    }

    /* ______________________________________________________________________ */
    public PasswordField(boolean visible) {
        initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initEvents() {
        this.undoManager = new UndoManager();
        this.getDocument().addUndoableEditListener(undoManager);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.isControlDown()) {
                    try {
                        if (ke.getKeyCode() == KeyEvent.VK_Z) {
                            undoManager.undo();
                        } else if (ke.getKeyCode() == KeyEvent.VK_Y) {
                            undoManager.redo();
                        }
                    } catch (CannotRedoException | CannotUndoException e) {
                    }
                }
            }
        });
    }

    /* GETTERS ______________________________________________________________ */
    @Override
    public String getText() {
        String password = "";
        for (char c : super.getPassword()) {
            password += c;
        }
        return password;
    }

    /* SETTERS ______________________________________________________________ */
    public void setPasswordVisible(boolean visible) {
        this.setEchoChar((char) 0);
    }
}
