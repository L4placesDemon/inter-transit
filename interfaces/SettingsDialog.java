package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tools.components.Dialog;
import tools.components.FontChooser;
import tools.components.ToggleSwitch;
import tools.components.ToggleSwitchListener;
import worldclasses.Settings;

public class SettingsDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    public static final String LIGHT_THEME = "light-theme";
    public static final String DARL_THEME = "dark-theme";

    private String theme;
    private final boolean darkThemeSelected;
    private Font selectedFont;

    private ToggleSwitch toggleSwitch;
    private JButton fontButton;

    private JButton okButton;
    private JButton cancelButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public SettingsDialog(boolean darkThemeSelected) {

        this.darkThemeSelected = darkThemeSelected;
        this.selectedFont = Settings.DEFAULT_FONT;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        String _theme;

        JPanel centerPanel;
        JPanel southPanel;

        JPanel mainPanel;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(340, 200);
        this.setLocationRelativeTo(null);
        this.setTitle("Configuracion");
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        this.toggleSwitch = new ToggleSwitch(
                this.darkThemeSelected,
                new Color(24, 136, 255)
        );
        if (this.darkThemeSelected) {
            Settings.darkTheme();
        } else {
            Settings.lightTheme();
        }

        this.fontButton = new JButton("Cambiar Fuente");

        this.okButton = new JButton("Aceptar");
        this.cancelButton = new JButton("Cancelar");

        centerPanel = new JPanel(new GridLayout(2, 2, 40, 25));
        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        mainPanel = new JPanel(new BorderLayout());

        // ---------------------------------------------------------------------
        centerPanel.setBorder(new EmptyBorder(20, 0, 20, 20));

        // ---------------------------------------------------------------------
        centerPanel.add(new JLabel("Tema Oscuro", JLabel.RIGHT));
        centerPanel.add(this.toggleSwitch);
        centerPanel.add(new JLabel("Fuente", JLabel.RIGHT));
        centerPanel.add(this.fontButton);

        southPanel.add(this.okButton);
        southPanel.add(this.cancelButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {

        // Components Events ---------------------------------------------------
        this.toggleSwitch.addToggleSwitchListener(new ToggleSwitchListener() {
            @Override
            public void activate() {
                setTheme(Settings.DARK_THEME);
                dispose();
                okAction();
                setDialogResultValue(new SettingsDialog(true).showDialog());
            }

            @Override
            public void deactivate() {
                setTheme(Settings.LIGHT_THEME);
                dispose();
                setDialogResultValue(new SettingsDialog(false).showDialog());
            }
        });

        this.fontButton.addActionListener(ae -> {
            this.fontAction();
        });

        this.okButton.addActionListener(ae -> {
            if (this.darkThemeSelected) {
                this.setTheme(Settings.DARK_THEME);
            } else {
                this.setTheme(Settings.LIGHT_THEME);
            }
            this.okAction();
            this.dispose();
        });

        this.cancelButton.addActionListener(ae -> {
            if (this.darkThemeSelected) {
                this.setTheme(Settings.DARK_THEME);
            } else {
                this.setTheme(Settings.LIGHT_THEME);
            }
            this.cancelAction();
            this.dispose();
        });
    }

    /* ______________________________________________________________________ */
    private void fontAction() {
        FontChooser fontChooser;
        int result;

        fontChooser = new FontChooser();
        fontChooser.setSelectedFont(this.getFont());
        result = fontChooser.showDialog(null);

        if (result == FontChooser.OK_OPTION) {
            this.setFont(fontChooser.getSelectedFont());
        }
    }

    /* GETTERS ______________________________________________________________ */
    public String getTheme() {
        return theme;
    }

    /* ______________________________________________________________________ */
    public Font getSelectedFont() {
        return this.selectedFont;
    }

    /* SETTERS ______________________________________________________________ */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /* ______________________________________________________________________ */
    public void setSelectedFont(Font selectedFont) {
        this.selectedFont = selectedFont;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new SettingsDialog(true).showTestDialog();
    }
}
