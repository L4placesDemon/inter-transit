package interfaces.workshops;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import tools.components.TextArea;
import tools.components.TextField;

import worldclasses.themes.Theme;
import worldclasses.themes.Tip;

public abstract class TipPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -4899131835181592905L;

    protected Theme theme;
    protected Tip tip;

    protected TextField titleThemeTextField;
    protected TextArea descriptionThemeTextArea;
    protected TextField valueThemeTextField;

    protected TextField titleTipTextField;
    protected TextArea contentTipTextArea;

    /* CONSTRUCTORS _________________________________________________________ */
    public TipPanel(Theme theme, Tip tip) {
        this.theme = theme;
        this.tip = tip;

        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    protected void initComponents() {
        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.titleThemeTextField = new TextField(this.getTheme().getTitle());
        this.descriptionThemeTextArea = new TextArea(this.getTheme().getDescription());
        this.valueThemeTextField = new TextField(this.getTheme().getValue() + "");

        this.titleTipTextField = new TextField(this.getTip().getTitle());
        this.contentTipTextArea = new TextArea(this.getTip().getDescription());

        // ---------------------------------------------------------------------
        this.contentTipTextArea.setLineWrap(true);
        this.contentTipTextArea.setWrapStyleWord(true);
    }

    /* GETTERS_______________________________________________________________ */
    public Tip getTip() {
        return tip;
    }

    /* ______________________________________________________________________ */
    public Theme getTheme() {
        return theme;
    }

    /* ______________________________________________________________________ */
    public TextField getTitleThemeTextField() {
        return this.titleThemeTextField;
    }

    /* ______________________________________________________________________ */
    public TextArea getDescriptionThemeTextArea() {
        return this.descriptionThemeTextArea;
    }

    /* ______________________________________________________________________ */
    public TextField getValueThemeTextField() {
        return this.valueThemeTextField;
    }

    /* ______________________________________________________________________ */
    public TextField getTitleTipTextField() {
        return this.titleTipTextField;
    }

    /* ______________________________________________________________________ */
    public TextArea getContentTipTextArea() {
        return this.contentTipTextArea;
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
