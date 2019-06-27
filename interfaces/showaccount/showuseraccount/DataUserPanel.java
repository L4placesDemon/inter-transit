package interfaces.showaccount.showuseraccount;

import javax.swing.JPanel;

import worldclasses.accounts.UserAccount;

public class DataUserPanel extends JPanel {

    private UserAccount userAccount;

    public DataUserPanel(UserAccount userAccount) {
        this.userAccount = userAccount;
        
        this.initComponents();
    }

    private void initComponents() {
        
    }
    
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
