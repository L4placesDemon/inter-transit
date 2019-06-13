package interfaces.workshops;

import interfaces.showaccount.ShowAccountDialog;
import interfaces.signin.SigninDialog;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import utilities.Dialog;
import utilities.Utilities;
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

        if (account != null) {
            imagePath = this.account.getImage();
            nickname = this.account.getNickname();
        } else {
            imagePath = "/images/profile/image-00.png";
            nickname = "Iniciar Sesion";
        }
        imageIcon = Utilities.getImageIcon(imagePath, 40, 40);

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

        userDialog = this.account == null
                ? new SigninDialog()
                : new ShowAccountDialog(this.account);

        int option = userDialog.showDialog();
        String imagePath = "/images/profile/image-00.png";

        if (this.account == null) {
            if (option == SigninDialog.OK_OPTION) {
                this.account = ((SigninDialog) userDialog).getAccount();
                imagePath = this.account.getImage();
                result = "ok sign in";
            } else {
                result = "cancel sign in";
            }
        } else {
            if (option == ShowAccountDialog.OK_OPTION) {
                this.account = null;
                result = "ok show";
            } else {
                this.account = ((ShowAccountDialog) userDialog).getAccount();
                imagePath = this.account.getImage();
                result = "cancel show";
            }
        }
        this.userButton.setIcon(Utilities.getImageIcon(imagePath, 40, 40));

        return result;
    }
}
