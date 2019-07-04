package interfaces.signin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import tools.Tools;
import tools.components.Border;
import tools.components.PasswordField;
import tools.components.TextField;

import worldclasses.Settings;

public class UserPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 5040342533939752738L;
    private static final ImageIcon SHOW_ICON = Tools.getImageIcon("show", 15, 15);
    private static final ImageIcon HIDE_ICON = Tools.getImageIcon("hide", 15, 15);

    private TextField nicknameField;
    private PasswordField passwordField;

    protected JToggleButton showPasswordButton;
    private JLabel messageLabel;

    /* CONSTRUCTORS _________________________________________________________ */
    public UserPanel() {
        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        Color color = null;
        String theme;

        JLabel nicknameLabel;
        JLabel passwordLabel;

        JPanel westPanel;
        JPanel centerPanel;
        JPanel eastPanel;

        theme = Settings.getCurrentSettings().getTheme();
        if (theme.equals(Settings.LIGHT_THEME)) {
            color = Color.red;
        } else if (theme.equals(Settings.DARK_THEME)) {
            color = new Color(69, 162, 255);
        }

        // Set up Panel --------------------------------------------------------
        this.setBorder(new Border(
                new EmptyBorder(0, 10, 0, 10),
                new Border("Usuario"),
                new EmptyBorder(10, 10, 5, 10)
        ));
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.nicknameField = new TextField();
        this.passwordField = new PasswordField();

        this.showPasswordButton = new JToggleButton(UserPanel.HIDE_ICON, false);
        this.messageLabel = new JLabel(" ", JLabel.RIGHT);

        nicknameLabel = new JLabel("Apodo:", JLabel.RIGHT);
        passwordLabel = new JLabel("Contraseña:", JLabel.RIGHT);

        westPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        eastPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        // ---------------------------------------------------------------------
        this.showPasswordButton.setPreferredSize(new Dimension(24, 16));
        this.messageLabel.setForeground(color);

        centerPanel.setBorder(new EmptyBorder(0, 20, 0, 10));

        // ---------------------------------------------------------------------
        westPanel.add(nicknameLabel);
        westPanel.add(passwordLabel);

        centerPanel.add(this.nicknameField);
        centerPanel.add(this.passwordField);

        eastPanel.add(new JLabel());
        eastPanel.add(this.showPasswordButton);

        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(this.messageLabel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.showPasswordButton.addActionListener(ae -> {
            boolean selected = this.showPasswordButton.isSelected();

            this.showPasswordButton.setIcon(selected ? UserPanel.SHOW_ICON : UserPanel.HIDE_ICON);
            this.passwordField.setPasswordVisible(selected);
        });

        this.nicknameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                boolean isAdmin = nicknameField.getText().toLowerCase().contains("admin");

                showPasswordButton.setVisible(true);
                if (isAdmin) {
                    passwordField.setPasswordVisible(false);
                    showPasswordButton.setVisible(false);
                }

                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    passwordField.requestFocus();
                }
            }
        });
    }
    /* ______________________________________________________________________ */
    public void clear() {
        this.nicknameField.setText("");
        this.passwordField.setText("");
        this.updateUI();
    }

    /* GETTERS ______________________________________________________________ */
    public String getNickname() {
        return this.nicknameField.getText();
    }

    /* ______________________________________________________________________ */
    public PasswordField getPasswordField() {
        return this.passwordField;
    }

    /* ______________________________________________________________________ */
    public String getPassword() {
        return this.passwordField.getText();
    }

    /* SETTERS ______________________________________________________________ */
    public void setMessage(String message) {
        this.messageLabel.setText(message);
    }
}
