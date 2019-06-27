package interfaces.themesmanagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import tools.components.TextField;

import worldclasses.themes.Theme;

public class EditThemePanel extends JPanel {

    private Theme theme;

    private TextField themeNameField;
    private JTextArea textArea;
    
    
    private JButton cancelButton;
    private JButton finishButton;

    public EditThemePanel(Theme theme) {
        this();
        this.theme = theme;
    }

    public EditThemePanel() {
        this.initComponents();
        this.initEvents();
    }

    private void initComponents() {
        JPanel buttonsPanel;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.cancelButton = new JButton();
        this.finishButton = new JButton();

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        
        
        // ---------------------------------------------------------------------
        buttonsPanel.add(this.cancelButton);
        buttonsPanel.add(this.finishButton);
        
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void initEvents() {

    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
