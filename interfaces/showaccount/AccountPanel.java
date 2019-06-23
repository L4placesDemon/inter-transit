package interfaces.showaccount;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tools.components.Border;
import tools.components.TextField;

import worldclasses.accounts.Account;

public abstract class AccountPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    protected Account account;

    protected TextField usernameField;
    protected TextField nicknameField;

    /* CONSTRUCTORS _________________________________________________________ */
    public AccountPanel(Account account) {
        this.account = account;

        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    protected void initComponents() {
        JLabel usernameLabel;
        JLabel nicknameLabel;

        JPanel leftPanel;
        JPanel rightPanel;

        // Set up Panel --------------------------------------------------------
        this.setBorder(new Border(new EmptyBorder(10, 20, 10, 20), new Border("Usuario")));
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.usernameField = new TextField(this.getAccount().getUsername());
        this.nicknameField = new TextField(this.getAccount().getNickname());

        usernameLabel = new JLabel("Nombre:", JLabel.RIGHT);
        nicknameLabel = new JLabel("Apodo:", JLabel.RIGHT);

        leftPanel = new JPanel(new GridLayout(2, 1, 10, 7));
        rightPanel = new JPanel(new GridLayout(2, 1, 10, 5));

        // ---------------------------------------------------------------------
        this.usernameField.setBorder(new Border(0, 5, 0, 5));
        this.usernameField.setEditable(false);

        this.nicknameField.setBorder(new Border(0, 5, 0, 5));
        this.nicknameField.setEditable(false);

        leftPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        rightPanel.setBorder(new EmptyBorder(10, 0, 20, 20));

        // ---------------------------------------------------------------------
        leftPanel.add(usernameLabel);
        leftPanel.add(nicknameLabel);

        rightPanel.add(this.usernameField);
        rightPanel.add(this.nicknameField);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }

    /* ______________________________________________________________________ */
    public void setUsername(String username) {
        this.getAccount().setUsername(username);
        this.usernameField.setText(username);
    }

    /* ______________________________________________________________________ */
    public void setNickname(String nickname) {
        this.getAccount().setNickname(nickname);
        this.nicknameField.setText(nickname);
    }
}
