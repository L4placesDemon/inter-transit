package interfaces.workshops;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import worldclasses.themes.Theme;
import worldclasses.themes.Tip;

public class TipAdminPanel extends TipPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 7442377731649517531L;

    private JButton saveButton;
    private JButton restoreButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public TipAdminPanel(Theme theme, Tip tip) {
        super(theme, tip);

        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    @Override
    protected void initComponents() {
        JPanel themePanel;
        JPanel tipPanel;
        JPanel buttonsPanel;

        // Set up Panel --------------------------------------------------------
        super.initComponents();

        // Set up Components ---------------------------------------------------
        themePanel = new JPanel(new BorderLayout());
        tipPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // ---------------------------------------------------------------------
        // ---------------------------------------------------------------------
        themePanel.add(super.titleThemeTextField, BorderLayout.NORTH);
        themePanel.add(super.descriptionThemeTextArea, BorderLayout.CENTER);
        themePanel.add(super.valueThemeTextField, BorderLayout.SOUTH);

        tipPanel.add(super.titleTipTextField, BorderLayout.NORTH);
        tipPanel.add(super.contentTipTextArea, BorderLayout.CENTER);

        buttonsPanel.add(this.restoreButton);
        buttonsPanel.add(this.saveButton);

        this.add(themePanel, BorderLayout.NORTH);
        this.add(tipPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.saveButton.addActionListener(ae -> {
        });

        this.restoreButton.addActionListener(ae -> {
        });
    }
}
