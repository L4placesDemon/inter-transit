package interfaces.accountsmanagement;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import utilities.Utilities;
import worldclasses.Account;

public class AccountButton extends JToggleButton {

    /* ATTRIBUTES ___________________________________________________________ */
    protected JLabel imageLabel;
    protected JLabel nicknameLabel;

    protected Account account;

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
        this.imageLabel = new JLabel(Utilities.getImageIcon(this.account.getImage(), 60, 60));
        this.nicknameLabel = new JLabel(this.account.getNickname());

        // ---------------------------------------------------------------------
        this.add(this.imageLabel, BorderLayout.CENTER);
        this.add(this.nicknameLabel, BorderLayout.SOUTH);
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
        this.imageLabel.setIcon(Utilities.getImageIcon(this.account.getImage(), 60, 60));
        this.nicknameLabel.setText(this.account.getNickname());
    }
}
