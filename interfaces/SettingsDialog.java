package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import tools.components.Dialog;
import tools.components.FontChooser;
import tools.components.ToggleSwitch;
import tools.components.ToggleSwitchListener;

public class SettingsDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    public static final String LIGHT_THEME = "light-theme";
    public static final String DARL_THEME = "dark-theme";

    private String theme;
    private Font selectedFont;

    private ToggleSwitch toggleSwitch;
    private JButton fontButton;
    private JButton backButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public SettingsDialog() {
        super();
        this.selectedFont = SettingsDialog.DEFAULT_FONT;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel centerPanel;
        JPanel southPanel;

        JPanel panel;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(250, 250));
        this.setTitle("Configuracion");
        this.setResizable(true);

        // Set up Components ---------------------------------------------------
        this.toggleSwitch = new ToggleSwitch();
        this.fontButton = new JButton("Fuente");
        this.backButton = new JButton("Cerrar");

        centerPanel = new JPanel(new GridLayout(2, 2, 35, 20));
        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel = new JPanel(new GridLayout());

        // ---------------------------------------------------------------------
        panel.setBorder(new EmptyBorder(10, 10, 0, 0));
        panel.setBackground(Color.yellow);

        // ---------------------------------------------------------------------
        panel.add(this.toggleSwitch);

        centerPanel.add(new JLabel("Tema Oscuro", JLabel.RIGHT));
        centerPanel.add(this.toggleSwitch);
        centerPanel.add(new JLabel("Fuente", JLabel.RIGHT));
        centerPanel.add(this.fontButton);

        southPanel.add(this.backButton);

        this.add(new JLabel("Configuracion", JLabel.CENTER), BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.toggleSwitch.addToggleSwitchListener(new ToggleSwitchListener() {
            @Override
            public void activate() {
                System.out.println("dark");
                darkThemeAction(getContentPane());
            }

            @Override
            public void deactivate() {
                System.out.println("light");
                lightThemeAction(getContentPane());
            }
        });

        this.fontButton.addActionListener(ae -> {
            this.fontAction();
        });

        this.backButton.addActionListener(ae -> {
            this.dispose();
        });
    }

    /* ______________________________________________________________________ */
    public void lightThemeAction(Component component) {
        Color background1 = Color.white;
        Color background2 = Color.white;
        Color background3 = new Color(45, 185, 255);
        Color background4 = null;
        Color foreground = Color.black;

        UIManager.put("OptionPane.background", background1);
        UIManager.put("OptionPane.messageFont", DEFAULT_FONT);
        UIManager.put("OptionPane.buttonFont", DEFAULT_FONT);

        UIManager.put("Panel.background", background1);

        UIManager.put("ToggleButton.background", background2);
        UIManager.put("ToggleButton.select", background3);
        UIManager.put("ToggleButton.focus", new Color(0, 0, 0, 0));

        UIManager.put("Label.foreground", foreground);
        UIManager.put("Label.font", DEFAULT_FONT);

        UIManager.put("Button.background", background1);
        UIManager.put("Button.foreground", foreground);
        UIManager.put("Button.font", DEFAULT_FONT);
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("Button.select", background3);

        UIManager.put("RadioButton.font", DEFAULT_FONT);
        UIManager.put("RadioButton.background", background1);

        UIManager.put("ScrollPane.background", background1);
        UIManager.put("ScrollBar.background", background1);

        UIManager.put("TextField.caretForeground", foreground);
        UIManager.put("TextField.background", background4);
        UIManager.put("TextField.foreground", foreground);

        UIManager.put("PasswordField.caretForeground", foreground);
        UIManager.put("PasswordField.background", background4);
        UIManager.put("PasswordField.foreground", foreground);

        SwingUtilities.updateComponentTreeUI(component);
        component.update(component.getGraphics());
    }

    /* ______________________________________________________________________ */
    public void darkThemeAction(Component component) {
        Color background1 = new Color(45, 45, 45);
        Color background2 = new Color(72, 72, 72);
        Color background3 = new Color(45, 185, 255);
        Color background4 = new Color(72, 72, 72);
        Color foreground = Color.white;

        UIManager.put("OptionPane.background", background1);
        UIManager.put("OptionPane.messageFont", DEFAULT_FONT);
        UIManager.put("OptionPane.buttonFont", DEFAULT_FONT);

        UIManager.put("Panel.background", background1);

        UIManager.put("ToggleButton.background", background2);
        UIManager.put("ToggleButton.select", background3);
        UIManager.put("ToggleButton.focus", new Color(0, 0, 0, 0));

        UIManager.put("Label.foreground", foreground);
        UIManager.put("Label.font", DEFAULT_FONT);

        UIManager.put("Button.background", background1);
        UIManager.put("Button.foreground", foreground);
        UIManager.put("Button.font", DEFAULT_FONT);
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("Button.select", background3);

        UIManager.put("RadioButton.font", DEFAULT_FONT);
        UIManager.put("RadioButton.background", background1);
        
        UIManager.put("ScrollPane.background", background1);
        UIManager.put("ScrollBar.background", background1);

        UIManager.put("TextField.border", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("TextField.caretForeground", foreground);
        UIManager.put("TextField.background", background4);
        UIManager.put("TextField.foreground", foreground);

        UIManager.put("PasswordField.border", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("PasswordField.caretForeground", foreground);
        UIManager.put("PasswordField.background", background4);
        UIManager.put("PasswordField.foreground", foreground);

        SwingUtilities.updateComponentTreeUI(component);
        component.update(component.getGraphics());
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

    /* ______________________________________________________________________ */
    public Font getSelectedFont() {
        return this.selectedFont;
    }

    /* ______________________________________________________________________ */
    public void setSelectedFont(Font selectedFont) {
        this.selectedFont = selectedFont;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new SettingsDialog().showTestDialog();
    }
}
