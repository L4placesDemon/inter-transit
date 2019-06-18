package interfaces.signin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import utilities.Border;
import utilities.PasswordField;
import utilities.TextField;
import utilities.Utilities;
import utilities.binaryfilemanager.BinaryFileManager;
import worldclasses.accounts.AdminAccount;

public class UserPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);
    private static final ImageIcon SHOW_ICON = Utilities.getImageIcon("/images/show.png", 15, 15);
    private static final ImageIcon HIDE_ICON = Utilities.getImageIcon("/images/hide.png", 15, 15);

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
        JLabel nicknameLabel;
        JLabel passwordLabel;

        JPanel westPanel;
        JPanel centerPanel;
        JPanel eastPanel;

        // Set up Panel --------------------------------------------------------
        this.setBackground(Color.white);
        this.setBorder(new Border(new EmptyBorder(20, 40, 15, 40),
                new Border("Usuario"), new EmptyBorder(10, 15, 5, 15)));
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
        this.nicknameField.setFont(UserPanel.DEFAULT_FONT);
        this.passwordField.setFont(UserPanel.DEFAULT_FONT);

        this.showPasswordButton.setBackground(Color.white);
        this.showPasswordButton.setPreferredSize(new Dimension(24, 16));

        this.messageLabel.setFont(UserPanel.DEFAULT_FONT);
        this.messageLabel.setForeground(Color.red);

        nicknameLabel.setFont(UserPanel.DEFAULT_FONT);
        passwordLabel.setFont(UserPanel.DEFAULT_FONT);

        westPanel.setBackground(Color.white);

        centerPanel.setBackground(Color.white);
        centerPanel.setBorder(new EmptyBorder(0, 20, 0, 10));

        eastPanel.setBackground(Color.white);

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
        char echoChar = this.passwordField.getEchoChar();

        this.showPasswordButton.addActionListener(ae -> {
            boolean selected = this.showPasswordButton.isSelected();

            this.showPasswordButton.setIcon(selected ? UserPanel.SHOW_ICON : UserPanel.HIDE_ICON);
            this.passwordField.setEchoChar(selected ? 0 : echoChar);
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
