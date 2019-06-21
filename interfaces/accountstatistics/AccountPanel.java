package interfaces.accountstatistics;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.Tools;

import worldclasses.accounts.UserAccount;

public class AccountPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private UserAccount userAccount;
    private Integer index;

    /* CONSTRUCTORS _________________________________________________________ */
    public AccountPanel(UserAccount account, Integer index) {
        this.userAccount = account;
        this.index = index;

        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel leftPanel;
        JPanel rightPanel;

        JLabel imageLabel;
        JLabel nicknameLabel;
        JLabel levelLabel;
        JLabel pointsLabel;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // Set up Components --------------------------------------------------- 
        leftPanel = new JPanel(new FlowLayout());
        rightPanel = new JPanel(new GridLayout(2, 1));

        imageLabel = new JLabel(Tools.getImageIcon(this.getUserAccount().getImage(), 60, 60));
        nicknameLabel = new JLabel(this.getUserAccount().getNickname());
        levelLabel = new JLabel("Nivel " + this.getUserAccount().getLevel());
        pointsLabel = new JLabel(this.getUserAccount().getPoints() + " puntos");

        // ---------------------------------------------------------------------
        // ---------------------------------------------------------------------
        leftPanel.add(new JLabel(this.getIndex() + ""));
        leftPanel.add(imageLabel);
        leftPanel.add(nicknameLabel);

        rightPanel.add(levelLabel);
        rightPanel.add(pointsLabel);

        this.add(leftPanel);
        this.add(rightPanel);
    }

    /* GETTERS ______________________________________________________________ */
    public UserAccount getUserAccount() {
        return this.userAccount;
    }

    /* GETTERS ______________________________________________________________ */
    public Integer getIndex() {
        return this.index;
    }

    /* SETTERS ______________________________________________________________ */
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    /* SETTERS ______________________________________________________________ */
    public void setIndex(Integer index) {
        this.index = index;
    }
}
