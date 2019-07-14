package interfaces.workshops;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tools.components.TextArea;
import tools.components.TextField;

import worldclasses.themes.Theme;
import worldclasses.themes.Tip;

public class TipPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -4899131835181592905L;

    protected Theme theme;
    protected Tip tip;

    protected JLabel themeImageLabel;
    protected TextField themeTitleTextField;
    protected TextField themeValueTextField;
    protected TextArea themeDescriptionTextArea;

    protected JLabel tipImageLabel;
    protected TextField tipTitleTextField;
    protected TextArea tipContentTextArea;

    /* CONSTRUCTORS _________________________________________________________ */
    public TipPanel(Theme theme, Tip tip) {
        this.theme = theme;
        this.tip = tip;

        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    protected void initComponents() {
        JPanel themeNorthPanel;
        JPanel themePanel;
        JPanel tipPanel;

        JScrollPane themeScrollPane;
        JScrollPane tipScrollPane;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.themeTitleTextField = new TextField(this.getTheme().getTitle());
        this.themeDescriptionTextArea = new TextArea(this.getTheme().getDescription());
        this.themeValueTextField = new TextField(this.getTheme().getValue() + "");

        this.tipTitleTextField = new TextField(this.getTip().getTitle());
        this.tipContentTextArea = new TextArea(this.getTip().getDescription());

        themeNorthPanel = new JPanel(new BorderLayout());
        themePanel = new JPanel(new BorderLayout());
        tipPanel = new JPanel(new BorderLayout());

        themeScrollPane = new JScrollPane(this.themeDescriptionTextArea);
        tipScrollPane = new JScrollPane(this.tipContentTextArea);

        this.themeTitleTextField.setEditable(false);
        this.themeDescriptionTextArea.setEditable(false);
        this.themeValueTextField.setEditable(false);

        this.tipTitleTextField.setEditable(false);
        this.tipContentTextArea.setEditable(false);

        // ---------------------------------------------------------------------
        themeNorthPanel.add(this.themeTitleTextField, BorderLayout.CENTER);
        themeNorthPanel.add(this.themeValueTextField, BorderLayout.EAST);

        themePanel.add(themeNorthPanel, BorderLayout.CENTER);
        themePanel.add(this.themeImageLabel, BorderLayout.WEST);
        themePanel.add(themeScrollPane, BorderLayout.CENTER);

        tipPanel.add(this.tipTitleTextField, BorderLayout.CENTER);
        tipPanel.add(this.tipImageLabel, BorderLayout.WEST);
        tipPanel.add(tipScrollPane, BorderLayout.CENTER);

        this.add(themePanel, BorderLayout.NORTH);
        this.add(tipPanel, BorderLayout.NORTH);
    }

    /* GETTERS_______________________________________________________________ */
    public Tip getTip() {
        return this.tip;
    }

    /* ______________________________________________________________________ */
    public Theme getTheme() {
        return this.theme;
    }

    /* ______________________________________________________________________ */
    public TextField getTitleThemeTextField() {
        return this.themeTitleTextField;
    }

    /* ______________________________________________________________________ */
    public TextArea getDescriptionThemeTextArea() {
        return this.themeDescriptionTextArea;
    }

    /* ______________________________________________________________________ */
    public TextField getValueThemeTextField() {
        return this.themeValueTextField;
    }

    /* ______________________________________________________________________ */
    public TextField getTitleTipTextField() {
        return this.tipTitleTextField;
    }

    /* ______________________________________________________________________ */
    public TextArea getContentTipTextArea() {
        return this.tipContentTextArea;
    }

    /* SETTERS_______________________________________________________________ */
    public void setTip(Tip tip) {
        this.tip = tip;
    }

    /* ______________________________________________________________________ */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
