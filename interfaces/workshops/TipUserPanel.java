package interfaces.workshops;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import worldclasses.themes.Theme;
import worldclasses.themes.Tip;

public class TipUserPanel extends TipPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -143376112754821630L;

    /* CONSTRUCTORS _________________________________________________________ */
    public TipUserPanel(Theme theme, Tip tip) {
        super(theme, tip);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    protected void initComponents() {
        JPanel themePanel;
        JPanel tipPanel;

        JScrollPane scrollPane;

        // Set up Panel --------------------------------------------------------
        super.initComponents();

        // Set up Components ---------------------------------------------------
        themePanel = new JPanel(new BorderLayout());
        tipPanel = new JPanel(new BorderLayout());

        scrollPane = new JScrollPane(super.tipContentTextArea);

        // ---------------------------------------------------------------------
        super.themeTitleTextField.setEditable(false);
        super.themeDescriptionTextArea.setEditable(false);
        super.themeValueTextField.setEditable(false);

        super.tipTitleTextField.setEditable(false);
        super.tipContentTextArea.setEditable(false);

        // ---------------------------------------------------------------------
        themePanel.add(super.themeTitleTextField, BorderLayout.NORTH);
        themePanel.add(super.themeDescriptionTextArea, BorderLayout.CENTER);
        themePanel.add(super.themeValueTextField, BorderLayout.SOUTH);

        tipPanel.add(super.tipTitleTextField, BorderLayout.NORTH);
        tipPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(themePanel, BorderLayout.NORTH);
        this.add(tipPanel, BorderLayout.CENTER);
    }
}
