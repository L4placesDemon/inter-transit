package interfaces.workshops;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import tools.Tools;

import tools.components.TextArea;
import tools.components.TextField;

import worldclasses.themes.Theme;
import worldclasses.themes.Tip;

public class FilePanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -4899131835181592905L;

    private Theme theme;
    private Tip tip;

    private JLabel themeImageLabel;
    private TextField themeTitleField;
    private TextField themeValueField;
    private TextArea themeDescriptionArea;

    private JLabel tipImageLabel;
    private TextField tipTitleField;
    private TextArea tipContentArea;

    /* CONSTRUCTORS _________________________________________________________ */
    public FilePanel(Theme theme, Tip tip) {
        this.theme = theme;
        this.tip = tip;

        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel themeNorthPanel;
        JPanel themePanel;

        JPanel tipImagePanel;
        JPanel tipPanel;

        JScrollPane themeScrollPane;
        JScrollPane tipScrollPane;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.themeImageLabel = new JLabel();
        this.themeTitleField = new TextField(this.getTheme().getTitle());
        this.themeDescriptionArea = new TextArea(this.getTheme().getDescription());
        this.themeValueField = new TextField(this.getTheme().getValue() + "");

        this.tipImageLabel = new JLabel();
        this.tipTitleField = new TextField(this.getTip().getTitle());
        this.tipContentArea = new TextArea(this.getTip().getDescription());

        themeNorthPanel = new JPanel(new BorderLayout());
        themePanel = new JPanel(new BorderLayout());

        tipImagePanel = new JPanel();
        tipPanel = new JPanel(new BorderLayout());

        themeScrollPane = new JScrollPane(this.themeDescriptionArea);
        tipScrollPane = new JScrollPane(this.tipContentArea);

        // ---------------------------------------------------------------------
        this.themeTitleField.setEditable(false);
        this.themeDescriptionArea.setEditable(false);
        this.themeValueField.setEditable(false);

        this.tipTitleField.setEditable(false);
        this.tipContentArea.setEditable(false);

        this.themeImageLabel.setIcon(Tools.getAbsoluteImageIcon(
                this.getTheme().getImage(),
                120, 120
        ));
        this.tipImageLabel.setIcon(Tools.getAbsoluteImageIcon(
                this.getTip().getImage(),
                120, 120
        ));

        tipImagePanel.setLayout(new BoxLayout(tipImagePanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------------------------
        themeNorthPanel.add(this.themeTitleField, BorderLayout.CENTER);
        themeNorthPanel.add(this.themeValueField, BorderLayout.EAST);

        themePanel.add(themeNorthPanel, BorderLayout.NORTH);
        themePanel.add(this.themeImageLabel, BorderLayout.WEST);
        themePanel.add(themeScrollPane, BorderLayout.CENTER);

        tipImagePanel.add(this.tipImageLabel);

        tipPanel.add(this.tipTitleField, BorderLayout.NORTH);
        tipPanel.add(tipImagePanel, BorderLayout.WEST);
        tipPanel.add(tipScrollPane, BorderLayout.CENTER);

        this.add(themePanel, BorderLayout.NORTH);
        this.add(tipPanel, BorderLayout.CENTER);
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
        return this.themeTitleField;
    }

    /* ______________________________________________________________________ */
    public TextArea getDescriptionThemeTextArea() {
        return this.themeDescriptionArea;
    }

    /* ______________________________________________________________________ */
    public TextField getValueThemeTextField() {
        return this.themeValueField;
    }

    /* ______________________________________________________________________ */
    public TextField getTitleTipTextField() {
        return this.tipTitleField;
    }

    /* ______________________________________________________________________ */
    public TextArea getContentTipTextArea() {
        return this.tipContentArea;
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
