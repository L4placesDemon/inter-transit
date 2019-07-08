package interfaces.createtheme;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.components.Dialog;
import tools.components.TextArea;
import tools.components.TextField;

import worldclasses.themes.Theme;

public class CreateThemeDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private Theme theme;

    private JLabel themeImageLabel;
    private JButton setThemeImageButton;

    private TextField themeTitleField;
    private TextField themeValueField;
    private TextArea themeDescriptionArea;

    private JLabel tipImageLabel;
    private JButton setTipImageButton;

    private TextField tipTitleField;
    private TextArea tipContentArea;

    private JButton finishButton;
    private JButton cancelButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public CreateThemeDialog() {
        this.initComponents();
        this.initEvents();
    }

    /* ______________________________________________________________________ */
    private void initComponents() {
        JPanel themeImagePanel;
        JPanel themeSouthPanel;
        JPanel themePanel;
        JPanel themeEastPanel;

        JPanel tipImagePanel;
        JPanel tipSouthPanel;
        JPanel tipPanel;
        JPanel tipEastPanel;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("Crear Tema");

        // Set up Components ---------------------------------------------------
        this.themeImageLabel = new JLabel();
        this.setThemeImageButton = new JButton("Elegir");
        this.themeValueField = new TextField();

        this.themeTitleField = new TextField();
        this.themeDescriptionArea = new TextArea();

        // ---------------------------------------------------------------------
        this.tipImageLabel = new JLabel();
        this.setTipImageButton = new JButton("Elegir");

        this.tipTitleField = new TextField();
        this.tipContentArea = new TextArea();

        this.finishButton = new JButton("Finalizar");
        this.cancelButton = new JButton("Cancelar");

        themeImagePanel = new JPanel(new BorderLayout());
        themeSouthPanel = new JPanel(new GridLayout(2, 1));
        themePanel = new JPanel(new BorderLayout());
        themeEastPanel = new JPanel(new BorderLayout());

        tipImagePanel = new JPanel(new BorderLayout());
        tipPanel = new JPanel(new BorderLayout());
        tipEastPanel = new JPanel(new BorderLayout());

        // ---------------------------------------------------------------------
        this.themeTitleField.setHint("Titulo del Tema");
        this.themeValueField.setHint("Valor del Tema");

        this.tipTitleField.setHint("Titulo del Tip");

        // ---------------------------------------------------------------------
        themeSouthPanel.add(this.setThemeImageButton);
        themeSouthPanel.add(this.themeValueField);

        themeImagePanel.add(this.themeImageLabel, BorderLayout.CENTER);
        themeImagePanel.add(themeSouthPanel, BorderLayout.SOUTH);

        themeEastPanel.add(this.themeTitleField, BorderLayout.NORTH);
        themeEastPanel.add(this.themeDescriptionArea, BorderLayout.CENTER);

        themePanel.add(themeImagePanel, BorderLayout.WEST);
        themePanel.add(themeEastPanel, BorderLayout.CENTER);

        // ---------------------------------------------------------------------
        tipImagePanel.add(this.tipImageLabel, BorderLayout.CENTER);
        tipImagePanel.add(this.setTipImageButton, BorderLayout.SOUTH);

        tipEastPanel.add(this.tipTitleField, BorderLayout.NORTH);
        tipEastPanel.add(this.tipContentArea, BorderLayout.CENTER);

        tipPanel.add(tipImagePanel, BorderLayout.WEST);
        tipPanel.add(tipEastPanel, BorderLayout.CENTER);

        this.add(themePanel, BorderLayout.NORTH);
        this.add(tipPanel, BorderLayout.CENTER);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {

    }

    /* GETTERS ______________________________________________________________ */
    public Theme getTheme() {
        return this.theme;
    }

    /* SETTERS ______________________________________________________________ */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new CreateThemeDialog().showTestDialog();
    }
}
