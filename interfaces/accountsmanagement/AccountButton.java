package interfaces.accountsmanagement;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import tools.Tools;

import worldclasses.accounts.Account;

public class AccountButton extends JToggleButton {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;

    private JLabel imageLabel;
    private JLabel nicknameLabel;

    /* CONSTRUCTORS _________________________________________________________ */
    public AccountButton(Account account) {
        this.account = account;

        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        // Set up Button --------------------------------------------------------
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set up Components ---------------------------------------------------
        this.imageLabel = new JLabel(Tools.getImageIcon(this.getAccount().getImage(), 60, 60));
        this.nicknameLabel = new JLabel(this.getAccount().getNickname());

        // ---------------------------------------------------------------------
        // ---------------------------------------------------------------------
        this.add(this.imageLabel, BorderLayout.CENTER);
        this.add(this.nicknameLabel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    public void updateAccount(Account account) {
        this.setAccount(account);
        this.imageLabel.setIcon(Tools.getImageIcon(this.account.getImage(), 60, 60));
        this.nicknameLabel.setText(this.account.getNickname());
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* SETTERS ______________________________________________________________ */
    public final void setAccount(Account account) {
        this.account = account;
    }
}
