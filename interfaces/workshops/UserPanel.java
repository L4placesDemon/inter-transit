package interfaces.workshops;

import interfaces.showaccount.ShowAccountDialog;
import interfaces.signin.SigninDialog;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import utilities.Dialog;
import utilities.DialogPane;
import utilities.Utilities;
import worldclasses.Account;
import worldclasses.Theme;

public class UserPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;

    private JButton userButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public UserPanel(Account account) {
        this.account = account;

        System.out.println("From UserPanel " + this.account);
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
        imageIcon = Utilities.getImageIcon(imagePath, 30, 30);

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
            this.workshopsSigninAction();
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
        this.userButton.setIcon(Utilities.getImageIcon(imagePath, 80, 80));

        return result;
    }

    /* ______________________________________________________________________ */
    private void showWorkshopsFrame() {
        ArrayList<Theme> themes = new ArrayList<>();
        WorkshopsFrame workshopsFrame;

        this.setVisible(false);
        workshopsFrame = new WorkshopsFrame(this.account, themes);

        workshopsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(true);
            }
        });

        workshopsFrame.setVisible(true);
    }

    /* ______________________________________________________________________ */
    private void workshopsSigninAction() {
        String result;
        int option = DialogPane.yesNoCancelOption("title", "message");

        if (option == DialogPane.YES_OPTION) {
            result = this.userAction();
            if (result.equals("ok sign in")) {
                this.showWorkshopsFrame();
            }
        } else if (option == DialogPane.NO_OPTION) {
            this.showWorkshopsFrame();
        }
        System.out.println("From MainMenuFrame " + account);
    }
}
