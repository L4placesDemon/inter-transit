package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import tools.filemanager.BinaryFileManager;

import tools.components.Dialog;
import tools.components.FontChooser;
import tools.components.ToggleSwitch;
import tools.components.ToggleSwitchListener;
import worldclasses.Settings;

public class SettingsDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -3025669317140773900L;

    public static final String LIGHT_THEME = "light-theme";
    public static final String DARL_THEME = "dark-theme";

    private Settings settings;

    private ToggleSwitch toggleSwitch;
    private JButton fontButton;

    private JButton okButton;
    private JButton cancelButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public SettingsDialog(Settings settings) {
        this.settings = settings;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
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
                this.getSettings().getTheme().equals(Settings.DARK_THEME),
                new Color(24, 136, 255)
        );
        if (this.getSettings().getTheme().equals(Settings.DARK_THEME)) {
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
                getSettings().setTheme(Settings.DARK_THEME);
                dispose();
                okAction();
                new BinaryFileManager(Settings.SETTINGS_PATH_FILE).write(
                        getSettings()
                );
                setDialogResultValue(new SettingsDialog(getSettings()).showDialog());
            }

            @Override
            public void deactivate() {
                getSettings().setTheme(Settings.LIGHT_THEME);
                dispose();
                new BinaryFileManager(Settings.SETTINGS_PATH_FILE).write(
                        getSettings()
                );
                setDialogResultValue(new SettingsDialog(getSettings()).showDialog());
            }
        });

        this.fontButton.addActionListener(ae -> {
            this.fontAction();
        });

        this.okButton.addActionListener(ae -> {
            this.dispose();
            this.okAction();
        });

        this.cancelButton.addActionListener(ae -> {
            this.dispose();
        });
    }

    /* ______________________________________________________________________ */
    private void fontAction() {
        FontChooser fontChooser;
        int result;

        fontChooser = new FontChooser(new String[]{"10", "11", "12", "13", "14"});
        fontChooser.setSelectedFont(this.getSettings().getFont());
        result = fontChooser.showDialog(null);

        if (result == FontChooser.OK_OPTION) {
            this.getSettings().setFont(fontChooser.getSelectedFont());
            dispose();
            okAction();

            new BinaryFileManager(Settings.SETTINGS_PATH_FILE).write(
                   this.getSettings()
            );
            setDialogResultValue(new SettingsDialog(this.getSettings()).showDialog());
        }
    }

    /* GETTERS ______________________________________________________________ */
    public Settings getSettings() {
        return this.settings;
    }

    /* SETTERS ______________________________________________________________ */
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        Settings settings = Settings.getCurrentSettings();
        new SettingsDialog(settings).showTestDialog();
    }
}
