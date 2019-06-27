package interfaces.editaccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import tools.components.Border;
import tools.components.PasswordField;

public class PasswordPanel extends interfaces.registeraccount.PasswordPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JRadioButton changePasswordButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public PasswordPanel() {
        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel westPanel;
        JPanel centerPanel;
        JPanel eastPanel;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setBorder(new Border(new EmptyBorder(5, 5, 5, 5),
                new Border("Constraseña")));

        // Set up Components ---------------------------------------------------
        this.changePasswordButton = new JRadioButton("Cambiar contraseña", false);

        this.passwordField = new PasswordField();
        this.confirmPasswordField = new PasswordField();

        this.showPasswordButton = new JToggleButton(PasswordPanel.HIDE_ICON);
        this.messageLabel = new JLabel(" ", JLabel.RIGHT);

        this.passwordLabel = new JLabel("          Contraseña:", JLabel.RIGHT);
        this.confirmPasswordLabel = new JLabel("Confirmar:", JLabel.RIGHT);

        westPanel = new JPanel(new GridLayout(2, 1, 3, 3));
        centerPanel = new JPanel(new GridLayout(2, 1, 3, 3));
        eastPanel = new JPanel(new FlowLayout());

        // ---------------------------------------------------------------------
        this.passwordLabel.setEnabled(false);
        this.confirmPasswordLabel.setEnabled(false);

        this.passwordField.setEnabled(false);

        this.confirmPasswordField.setEnabled(false);

        this.showPasswordButton.setPreferredSize(new Dimension(24, 18));
        this.showPasswordButton.setEnabled(false);

        this.messageLabel.setForeground(Color.red);
        this.messageLabel.setEnabled(false);

        westPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        centerPanel.setBorder(new EmptyBorder(5, 5, 5, 2));
        // ---------------------------------------------------------------------
        westPanel.add(passwordLabel);
        westPanel.add(confirmPasswordLabel);

        centerPanel.add(this.passwordField);
        centerPanel.add(this.confirmPasswordField);

        eastPanel.add(this.showPasswordButton);

        this.add(this.changePasswordButton, BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(this.messageLabel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        this.changePasswordButton.addActionListener(ae -> {
            boolean selected = this.changePasswordButton.isSelected();
            
            this.passwordLabel.setEnabled(selected);
            this.confirmPasswordLabel.setEnabled(selected);
            this.passwordField.setEnabled(selected);
            this.confirmPasswordField.setEnabled(selected);
            this.showPasswordButton.setEnabled(selected);
            this.messageLabel.setEnabled(selected);
        });

        this.showPasswordButton.addActionListener(ae -> {
            boolean selected = this.showPasswordButton.isSelected();
            
            this.showPasswordButton.setIcon(selected ? PasswordPanel.SHOW_ICON : PasswordPanel.HIDE_ICON);
            this.passwordField.setPasswordVisible(selected);
            this.confirmPasswordField.setPasswordVisible(selected);
        });

        this.passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    confirmPasswordField.requestFocus();
                }
            }
        });
    }

    /* GETTERS ______________________________________________________________ */
    public String getMessage() {
        return this.messageLabel.getText();
    }

    /* ______________________________________________________________________ */
    public boolean isEditable() {
        return this.changePasswordButton.isSelected();
    }
}
