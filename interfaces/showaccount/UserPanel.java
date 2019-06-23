package interfaces.showaccount;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tools.components.Border;
import tools.components.TextField;

import worldclasses.accounts.UserAccount;

public class UserPanel extends AccountPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private TextField levelField;
    private TextField pointsField;

    /* CONSTRUCTORS _________________________________________________________ */
    public UserPanel(UserAccount userAccount) {
        super(userAccount);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    protected void initComponents() {
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
        super.usernameField = new TextField(super.getAccount().getUsername());
        super.nicknameField = new TextField(super.getAccount().getNickname());

        this.levelField = new TextField();
        this.pointsField = new TextField();
        this.pointsField.setText(((UserAccount) super.getAccount()).getPoints() + "");
        this.levelField.setText(((UserAccount) super.getAccount()).getLevel() + "");

        usernameLabel = new JLabel("Nombre:", JLabel.RIGHT);
        nicknameLabel = new JLabel("Apodo:", JLabel.RIGHT);
        levelLabel = new JLabel("Nivel:", JLabel.RIGHT);
        pointsLabel = new JLabel("Puntos:", JLabel.RIGHT);

        leftPanel = new JPanel(new GridLayout(4, 1, 10, 7));
        rightPanel = new JPanel(new GridLayout(4, 1, 10, 5));

        // ---------------------------------------------------------------------
        super.usernameField.setBorder(new Border(0, 5, 0, 5));
        super.usernameField.setEditable(false);

        super.nicknameField.setBorder(new Border(0, 5, 0, 5));
        super.nicknameField.setEditable(false);

        this.levelField.setBorder(new Border(0, 5, 0, 5));
        this.levelField.setEditable(false);

        this.pointsField.setBorder(new Border(0, 5, 0, 5));
        this.pointsField.setEditable(false);

        leftPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        rightPanel.setBorder(new EmptyBorder(10, 0, 20, 20));

        // ---------------------------------------------------------------------
        leftPanel.add(usernameLabel);
        leftPanel.add(nicknameLabel);
        leftPanel.add(levelLabel);
        leftPanel.add(pointsLabel);

        rightPanel.add(super.usernameField);
        rightPanel.add(super.nicknameField);
        rightPanel.add(this.levelField);
        rightPanel.add(this.pointsField);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);
    }

    /* SETTERS ______________________________________________________________ */
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
