package interfaces.workshops;

import interfaces.showaccount.ShowAccountDialog;
import interfaces.signin.SigninDialog;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.Tools;
import tools.components.Dialog;

import worldclasses.accounts.Account;

public class UserPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;

    private JButton userButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public UserPanel(Account account) {
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
            imagePath = "/images/profile/image-00.png";
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
        Dialog userDialog;

        userDialog = this.getAccount() == null
                ? new SigninDialog()
                : new ShowAccountDialog(this.getAccount());

        int option = userDialog.showDialog();
        String imagePath = "/images/profile/image-00.png";

        if (this.getAccount() == null) {
            if (option == SigninDialog.OK_OPTION) {
                this.setAccount(((SigninDialog) userDialog).getAccount());
                imagePath = this.getAccount().getImage();
                result = "ok sign in";
            } else {
                result = "cancel sign in";
            }
        } else {
            if (option == ShowAccountDialog.OK_OPTION) {
                this.setAccount(null);
                result = "ok show";
            } else {
                this.setAccount(((ShowAccountDialog) userDialog).getAccount());
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
