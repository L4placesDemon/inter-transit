package interfaces.showaccount.showuseraccount;

import interfaces.showaccount.AccountPanel;

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
    private static final long serialVersionUID = 3866442993474129223L;

    private TextField levelField;
    private TextField pointsField;

    /* CONSTRUCTORS _________________________________________________________ */
    public UserPanel(UserAccount userAccount) {
        super(userAccount);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    protected void initComponents() {
        JPanel leftPanel;
        JPanel rightPanel;

        // Set up Panel --------------------------------------------------------
        super.initComponents();
        this.setBorder(new Border(new EmptyBorder(0, 20, 0, 20), new Border("Usuario")));

        // Set up Components ---------------------------------------------------
        this.levelField = new TextField();
        this.pointsField = new TextField();

        leftPanel = new JPanel(new GridLayout(4, 1, 10, 7));
        rightPanel = new JPanel(new GridLayout(4, 1, 10, 5));

        // ---------------------------------------------------------------------
        this.levelField.setText(((UserAccount) super.getAccount()).getLevel() + "");
        this.levelField.setEditable(false);

        this.pointsField.setText(((UserAccount) super.getAccount()).getPoints() + "");
        this.pointsField.setEditable(false);

        leftPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        rightPanel.setBorder(new EmptyBorder(10, 0, 20, 20));

        // ---------------------------------------------------------------------
        leftPanel.add(new JLabel("Nombre:", JLabel.RIGHT));
        leftPanel.add(new JLabel("Apodo:", JLabel.RIGHT));
        leftPanel.add(new JLabel("Nivel:", JLabel.RIGHT));
        leftPanel.add(new JLabel("Puntos:", JLabel.RIGHT));

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
