package interfaces.editaccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import tools.Border;
import tools.TextField;

public class UserPanel extends interfaces.registeraccount.UserPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private JLabel usernameLabel;
    private JLabel nicknameLabel;

    private JRadioButton changePasswordButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public UserPanel(String userName, String nickname) {
        super.setUsername(userName);
        super.setNickname(nickname);

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel leftPanel;
        JPanel rightPanel;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setBorder(new Border(new EmptyBorder(5, 5, 5, 5), new Border("Usuario")));

        // Set up Components ---------------------------------------------------
        this.changePasswordButton = new JRadioButton("Cambiar datos usuario", false);

        this.usernameField = new TextField();
        this.nicknameField = new TextField();
        this.messageLabel = new JLabel(" ", JLabel.RIGHT);

        this.usernameLabel = new JLabel("Nombres:", JLabel.RIGHT);
        this.nicknameLabel = new JLabel("Apodo:", JLabel.RIGHT);

        leftPanel = new JPanel(new GridLayout(2, 1, 3, 3));
        rightPanel = new JPanel(new GridLayout(2, 1, 3, 3));

        // ---------------------------------------------------------------------
        this.usernameLabel.setEnabled(false);
        this.nicknameLabel.setEnabled(false);

        this.usernameField.setEnabled(false);
        this.nicknameField.setEnabled(false);

        this.messageLabel.setForeground(Color.red);
        this.messageLabel.setEnabled(false);

        leftPanel.setBorder(new EmptyBorder(5, 43, 5, 20));
        rightPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // ---------------------------------------------------------------------
        leftPanel.add(this.usernameLabel);
        leftPanel.add(this.nicknameLabel);

        rightPanel.add(this.usernameField);
        rightPanel.add(this.nicknameField);
        rightPanel.add(this.messageLabel);

        this.add(this.changePasswordButton, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);
        this.add(this.messageLabel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {

        this.changePasswordButton.addActionListener(ae -> {
            boolean selected = this.changePasswordButton.isSelected();
            this.usernameLabel.setEnabled(selected);
            this.nicknameLabel.setEnabled(selected);
            this.usernameField.setEnabled(selected);
            this.nicknameField.setEnabled(selected);
            this.messageLabel.setEnabled(selected);
        });

        this.usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    nicknameField.requestFocus();
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
