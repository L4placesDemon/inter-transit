package tools.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class TextField extends JTextField {

    /* ATTRIBUTES ___________________________________________________________ */
    private UndoManager undoManager;

    /* CONSTRUCTORS _________________________________________________________ */
    public TextField(String string) {
        super(string);

        this.initEvents();
    }

    /* ______________________________________________________________________ */
    public TextField() {
        this("");
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
}
