package interfaces.accountstatistics;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utilities.Utilities;
import worldclasses.UserAccount;

public class AccountPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private UserAccount userAccount;

    /* CONSTRUCTORS _________________________________________________________ */
    public AccountPanel(UserAccount account) {
        this.userAccount = account;
        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel panel;
        JPanel _panel;

        JLabel imageLabel;
        JLabel nicknameLabel;
        JLabel levelLabel;
        JLabel pointsLabel;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // Set up Components --------------------------------------------------- 
        panel = new JPanel(new GridLayout(1, 2));
        _panel = new JPanel(new GridLayout(2, 1));

        imageLabel = new JLabel(Utilities.getImageIcon(this.userAccount.getImage(), 70, 70));
        nicknameLabel = new JLabel(this.userAccount.getNickname());
        levelLabel = new JLabel("Level: " + this.userAccount.getLevel());
        pointsLabel = new JLabel("Points: " + this.userAccount.getPoints());

        // ---------------------------------------------------------------------
        // ---------------------------------------------------------------------
        panel.add(imageLabel);
        panel.add(nicknameLabel);

        _panel.add(levelLabel);
        _panel.add(pointsLabel);

        this.add(panel);
        this.add(_panel);
    }

    /* GETTERS ______________________________________________________________ */
    public UserAccount getAccount() {
        return this.userAccount;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
