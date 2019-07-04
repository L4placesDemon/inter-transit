package interfaces.workshops;

import interfaces.showaccount.showadminaccount.ShowAdminDialog;
import interfaces.showaccount.showuseraccount.ShowUserDialog;
import interfaces.signin.SigninDialog;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.Tools;
import tools.components.Dialog;

import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;
import worldclasses.accounts.UserAccount;

public class AccountPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 2748146415352358528L;

    private Account account;

    private JButton userButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public AccountPanel(Account account) {
        this.account = account;

        this.initComponents();
        this.initEvents();
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

        // Set up Panel --------------------------------------------------------
        this.setLayout(new FlowLayout());

        // Set up Components ---------------------------------------------------
        this.userButton = new JButton();

        // ---------------------------------------------------------------------
        this.userButton.setIcon(imageIcon);
        this.userButton.setText(nickname);

        // ---------------------------------------------------------------------
        this.add(userButton);
    }

    /* METHODS ______________________________________________________________ */
    private void initEvents() {
        this.userButton.addActionListener(ae -> {
            this.userAction();
        });
    }

    /* ______________________________________________________________________ */
    private String userAction() {
        String result;
        Dialog accountDialog;

        accountDialog = this.getAccount() == null
                ? new SigninDialog()
                : this.getAccount() instanceof UserAccount
                ? new ShowUserDialog((UserAccount) this.getAccount())
                : new ShowAdminDialog((AdminAccount) this.getAccount());

        int option = accountDialog.showDialog();
        String imagePath = "profile/image-00";

        if (this.getAccount() == null) {
            if (option == SigninDialog.OK_OPTION) {
                this.setAccount(((SigninDialog) accountDialog).getAccount());
                imagePath = this.getAccount().getImage();
                result = "ok sign in";
            } else {
                result = "cancel sign in";
            }
        } else {
            if (option == ShowUserDialog.OK_OPTION) {
                this.setAccount(null);
                result = "ok show";
            } else {
                this.setAccount(((ShowUserDialog) accountDialog).getAccount());
                imagePath = this.getAccount().getImage();
                result = "cancel show";
            }
        }
        this.userButton.setIcon(Tools.getImageIcon(imagePath, 40, 40));

        return result;
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
