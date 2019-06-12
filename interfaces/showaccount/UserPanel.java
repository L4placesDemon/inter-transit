package interfaces.showaccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utilities.Border;
import utilities.TextField;
import worldclasses.Account;
import worldclasses.UserAccount;

public class UserPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);

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
        this.setBackground(Color.white);
        this.setBorder(new Border(new EmptyBorder(10, 20, 10, 20),
                new Border("Usuario")));
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.usernameField = new TextField(this.account.getUsername());
        this.usernameField.setBackground(Color.white);

        this.nicknameField = new TextField(this.account.getNickname());
        this.nicknameField.setBackground(Color.white);

        this.levelField = new TextField();
        this.levelField.setBackground(Color.white);

        this.pointsField = new TextField();
        this.pointsField.setBackground(Color.white);

        if (this.account instanceof UserAccount) {
            this.pointsField.setText(((UserAccount) this.account).getPoints() + "");
            this.levelField.setText(((UserAccount) this.account).getLevel() + "");
        }

        usernameLabel = new JLabel("Nombre:", JLabel.RIGHT);
        nicknameLabel = new JLabel("Apodo:", JLabel.RIGHT);
        levelLabel = new JLabel("Nivel:", JLabel.RIGHT);
        pointsLabel = new JLabel("Puntos:", JLabel.RIGHT);

        int rows = 2;
        if (this.account instanceof UserAccount) {
            rows = 4;
        }

        leftPanel = new JPanel(new GridLayout(rows, 1, 10, 7));
        leftPanel.setBackground(Color.white);
        rightPanel = new JPanel(new GridLayout(rows, 1, 10, 5));
        rightPanel.setBackground(Color.white);

        // ---------------------------------------------------------------------
        this.usernameField.setBorder(new Border(0, 5, 0, 5));
        this.usernameField.setFont(UserPanel.DEFAULT_FONT);
        this.usernameField.setEditable(false);

        this.nicknameField.setBorder(new Border(0, 5, 0, 5));
        this.nicknameField.setFont(UserPanel.DEFAULT_FONT);
        this.nicknameField.setEditable(false);

        this.levelField.setBorder(new Border(0, 5, 0, 5));
        this.levelField.setFont(UserPanel.DEFAULT_FONT);
        this.levelField.setEditable(false);

        this.pointsField.setBorder(new Border(0, 5, 0, 5));
        this.pointsField.setFont(UserPanel.DEFAULT_FONT);
        this.pointsField.setEditable(false);

        usernameLabel.setFont(UserPanel.DEFAULT_FONT);
        nicknameLabel.setFont(UserPanel.DEFAULT_FONT);
        levelLabel.setFont(UserPanel.DEFAULT_FONT);
        pointsLabel.setFont(UserPanel.DEFAULT_FONT);

        leftPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        rightPanel.setBorder(new EmptyBorder(10, 0, 20, 20));

        // ---------------------------------------------------------------------
        leftPanel.add(usernameLabel);
        leftPanel.add(nicknameLabel);

        rightPanel.add(this.usernameField);
        rightPanel.add(this.nicknameField);

        if (this.account instanceof UserAccount) {
            leftPanel.add(levelLabel);
            leftPanel.add(pointsLabel);
            
            rightPanel.add(this.levelField);
            rightPanel.add(this.pointsField);
        }

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);
    }

    /* SETTERS ______________________________________________________________ */
    public void setUsername(String username) {
        this.account.setUsername(username);
        this.usernameField.setText(username);
    }

    /* ______________________________________________________________________ */
    public void setNickname(String nickname) {
        this.account.setNickname(nickname);
        this.nicknameField.setText(nickname);
    }

    /* ______________________________________________________________________ */
    public void setLevel(int level) {
        ((UserAccount) this.account).setLevel(level);
        this.levelField.setText(level + "");
    }

    /* ______________________________________________________________________ */
    public void setPoints(int points) {
        ((UserAccount) this.account).setPoints(points);
        this.pointsField.setText(points + "");
    }
}
