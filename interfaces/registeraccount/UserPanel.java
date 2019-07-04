package interfaces.registeraccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tools.components.Border;
import tools.components.TextField;

public class UserPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 6372631086088811729L;

    protected TextField usernameField;
    protected TextField nicknameField;
    protected JLabel messageLabel;

    /* CONSTRUCTORS _________________________________________________________ */
    public UserPanel() {
        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JLabel usernameLabel;
        JLabel nicknameLabel;

        JPanel leftPanel;
        JPanel rightPanel;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setBorder(new Border(new EmptyBorder(5, 5, 5, 5), new Border("Usuario")));

        // Set up Components ---------------------------------------------------
        this.usernameField = new TextField();
        this.nicknameField = new TextField();
        this.messageLabel = new JLabel(" ", JLabel.RIGHT);

        usernameLabel = new JLabel("Nombres:", JLabel.RIGHT);
        nicknameLabel = new JLabel("Apodo:", JLabel.RIGHT);

        leftPanel = new JPanel(new GridLayout(2, 1, 3, 3));
        rightPanel = new JPanel(new GridLayout(2, 1, 3, 3));

        // ---------------------------------------------------------------------
        this.messageLabel.setForeground(Color.red);

        leftPanel.setBorder(new EmptyBorder(5, 43, 5, 20));
        rightPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // ---------------------------------------------------------------------
        leftPanel.add(usernameLabel);
        leftPanel.add(nicknameLabel);

        rightPanel.add(this.usernameField);
        rightPanel.add(this.nicknameField);
        rightPanel.add(this.messageLabel);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);
        this.add(this.messageLabel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
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
    public String getUsername() {
        return this.usernameField.getText();
    }

    /* ______________________________________________________________________ */
    public String getNickname() {
        return this.nicknameField.getText();
    }

    /* ______________________________________________________________________ */
    public TextField getNicknameField() {
        return this.nicknameField;
    }

    /* SETTERS ______________________________________________________________ */
    public void setUsername(String username) {
        this.usernameField.setText(username);
    }

    /* ______________________________________________________________________ */
    public void setNickname(String nickname) {
        this.nicknameField.setText(nickname);
    }

    /* ______________________________________________________________________ */
    public void setMessage(String message) {
        this.messageLabel.setText(message);
    }
}
