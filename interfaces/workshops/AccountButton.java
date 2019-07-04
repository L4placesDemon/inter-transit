package interfaces.workshops;

import interfaces.showaccount.ShowAccountDialog;
import interfaces.showaccount.showadminaccount.ShowAdminDialog;
import interfaces.showaccount.showuseraccount.ShowUserDialog;
import interfaces.signin.SigninDialog;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import tools.Tools;
import tools.components.Dialog;

import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;
import worldclasses.accounts.UserAccount;

public class AccountButton extends JButton {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 2748146415352358528L;

    private Account account;

    /* CONSTRUCTORS _________________________________________________________ */
    public AccountButton(Account account) {
        this.account = account;

        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        String imagePath;
        String nickname;
        ImageIcon imageIcon;

        if (this.getAccount() != null) {
            imagePath = this.getAccount().getImage();
            nickname = this.getAccount().getNickname();
        } else {
            imagePath = "profile/image-00";
            nickname = "Iniciar Sesion";
        }
        imageIcon = Tools.getImageIcon(imagePath, 40, 40);

        this.setIcon(imageIcon);
        this.setText(nickname);
    }

    /* METHODS ______________________________________________________________ */
    public boolean accountAction() {
        Dialog accountDialog;

        accountDialog = this.getAccount() == null
                ? new SigninDialog()
                : this.getAccount() instanceof UserAccount
                ? new ShowUserDialog((UserAccount) this.getAccount())
                : new ShowAdminDialog((AdminAccount) this.getAccount());

        int option = accountDialog.showDialog();
        String imagePath = "profile/image-00";
        String nickname = "Iniciar Sesion";

        if (this.getAccount() == null) {
            if (option == SigninDialog.OK_OPTION) {
                this.setAccount(((SigninDialog) accountDialog).getAccount());
                imagePath = this.getAccount().getImage();
                nickname = this.getAccount().getNickname();
            }
        } else {
            if (option == ShowUserDialog.OK_OPTION) {
                this.setAccount(null);
            } else {
                this.setAccount(((ShowAccountDialog) accountDialog).getAccount());
                imagePath = this.getAccount().getImage();
                nickname = this.getAccount().getNickname();
            }
        }
        this.setIcon(Tools.getImageIcon(imagePath, 40, 40));
        this.setText(nickname);

        return this.getAccount() != null;
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }
}
