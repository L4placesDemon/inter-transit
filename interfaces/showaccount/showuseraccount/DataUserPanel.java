package interfaces.showaccount.showuseraccount;

import javax.swing.JPanel;

import worldclasses.accounts.UserAccount;

public class DataUserPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 6132507588946289222L;
    private UserAccount userAccount;

    /* CONSTRUCTORS _________________________________________________________ */
    public DataUserPanel(UserAccount userAccount) {
        this.userAccount = userAccount;

        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {

    }

    /* GETTERS ______________________________________________________________ */
    public UserAccount getUserAccount() {
        return userAccount;
    }

    /* SETTERS ______________________________________________________________ */
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
