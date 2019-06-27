package interfaces.themesmanagement;

import javax.swing.JPanel;
import tools.components.TextField;
import worldclasses.themes.Tip;

public class EditTipPanel extends JPanel {

    private Tip tip;
    
    private TextField tipNameField;
    
    public EditTipPanel() {
        this.initComponents();
        this.initEvents();
    }
    public EditTipPanel(Tip tip) {
        this();
        this.tip = tip;
    }

    private void initComponents() {

    }

    private void initEvents() {

    }
}
