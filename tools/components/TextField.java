package tools.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import worldclasses.Settings;

public class TextField extends JTextField implements FocusListener {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -184964402771585973L;

    private UndoManager undoManager;
    private String hint;

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
        this.addFocusListener(this);
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

    /* ______________________________________________________________________ */
    @Override
    public void focusGained(FocusEvent fe) {
        if (getText().equals(getHint())) {
            this.setText("");
        }
        this.setFont(Settings.getCurrentSettings().getFont());

        if (Settings.getCurrentSettings().getTheme().equals(Settings.DARK_THEME)) {
            this.setForeground(Color.white);
        } else {
            this.setForeground(Color.black);
        }
    }

    /* ______________________________________________________________________ */
    @Override
    public void focusLost(FocusEvent fe) {
        if (this.getText().length() <= 0) {
            this.setHint(this.getHint());

        } else {
            this.setFont(Settings.getCurrentSettings().getFont());

            if (Settings.getCurrentSettings().getTheme().equals(Settings.DARK_THEME)) {
                this.setForeground(Color.white);
            } else {
                this.setForeground(Color.black);
            }
        }
    }

    /* GETTERS ______________________________________________________________ */
    public String getHint() {
        return this.hint;
    }

    /* SETTERS ______________________________________________________________ */
    public void setHint(String hint) {
        Font font = Settings.getCurrentSettings().getFont();
        this.hint = hint;

        this.setForeground(Color.lightGray);
        this.setFont(new Font(font.getFamily(), Font.ITALIC, font.getSize()));
        this.setText(hint);
    }
}
