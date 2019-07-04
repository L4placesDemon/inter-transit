package interfaces.workshops;

import java.awt.BorderLayout;
import javax.swing.JPanel;

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

        // Set up Panel --------------------------------------------------------
        super.initComponents();

        // Set up Components ---------------------------------------------------
        themePanel = new JPanel(new BorderLayout());
        tipPanel = new JPanel(new BorderLayout());

        // ---------------------------------------------------------------------
        super.titleThemeTextField.setEditable(false);
        super.descriptionThemeTextArea.setEditable(false);
        super.valueThemeTextField.setEditable(false);

        super.titleTipTextField.setEditable(false);
        super.contentTipTextArea.setEditable(false);

        // ---------------------------------------------------------------------
        themePanel.add(super.titleThemeTextField, BorderLayout.NORTH);
        themePanel.add(super.descriptionThemeTextArea, BorderLayout.CENTER);
        themePanel.add(super.valueThemeTextField, BorderLayout.SOUTH);

        tipPanel.add(super.titleTipTextField, BorderLayout.NORTH);
        tipPanel.add(super.contentTipTextArea, BorderLayout.CENTER);

        this.add(themePanel, BorderLayout.NORTH);
        this.add(tipPanel, BorderLayout.CENTER);
    }
}
