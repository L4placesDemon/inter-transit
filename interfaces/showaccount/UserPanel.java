package interfaces.showaccount;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import tools.Border;
import tools.TextField;
import worldclasses.accounts.Account;
import worldclasses.accounts.UserAccount;

public class UserPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;

    private TextField usernameField;
    private TextField nicknameField;
    private TextField levelField;
    private TextField pointsField;

    /* CONSTRUCTORS _________________________________________________________ */
    public UserPanel(Account account) {
        this.account = account;
        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JLabel usernameLabel;
        JLabel nicknameLabel;
        JLabel levelLabel;
        JLabel pointsLabel;

        JPanel leftPanel;
        JPanel rightPanel;

        // Set up Panel --------------------------------------------------------
        this.setBorder(new Border(new EmptyBorder(10, 20, 10, 20), new Border("Usuario")));
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.usernameField = new TextField(this.getAccount().getUsername());
        this.nicknameField = new TextField(this.getAccount().getNickname());

        this.levelField = new TextField();
        this.pointsField = new TextField();

        if (this.getAccount() instanceof UserAccount) {
            this.pointsField.setText(((UserAccount) this.getAccount()).getPoints() + "");
            this.levelField.setText(((UserAccount) this.getAccount()).getLevel() + "");
        }

        usernameLabel = new JLabel("Nombre:", JLabel.RIGHT);
        nicknameLabel = new JLabel("Apodo:", JLabel.RIGHT);
        levelLabel = new JLabel("Nivel:", JLabel.RIGHT);
        pointsLabel = new JLabel("Puntos:", JLabel.RIGHT);

        int rows = 2;
        if (this.getAccount() instanceof UserAccount) {
            rows = 4;
        }

        leftPanel = new JPanel(new GridLayout(rows, 1, 10, 7));
        rightPanel = new JPanel(new GridLayout(rows, 1, 10, 5));

        // ---------------------------------------------------------------------
        this.usernameField.setBorder(new Border(0, 5, 0, 5));
        this.usernameField.setEditable(false);

        this.nicknameField.setBorder(new Border(0, 5, 0, 5));
        this.nicknameField.setEditable(false);

        this.levelField.setBorder(new Border(0, 5, 0, 5));
        this.levelField.setEditable(false);

        this.pointsField.setBorder(new Border(0, 5, 0, 5));
        this.pointsField.setEditable(false);

        leftPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        rightPanel.setBorder(new EmptyBorder(10, 0, 20, 20));

        // ---------------------------------------------------------------------
        leftPanel.add(usernameLabel);
        leftPanel.add(nicknameLabel);

        rightPanel.add(this.usernameField);
        rightPanel.add(this.nicknameField);

        if (this.getAccount() instanceof UserAccount) {
            leftPanel.add(levelLabel);
            leftPanel.add(pointsLabel);

            rightPanel.add(this.levelField);
            rightPanel.add(this.pointsField);
        }

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

    /* ______________________________________________________________________ */
    public void setLevel(int level) {
        ((UserAccount) this.getAccount()).setLevel(level);
        this.levelField.setText(level + "");
    }

    /* ______________________________________________________________________ */
    public void setPoints(int points) {
        ((UserAccount) this.getAccount()).setPoints(points);
        this.pointsField.setText(points + "");
    }
}
