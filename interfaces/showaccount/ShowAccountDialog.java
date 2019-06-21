package interfaces.showaccount;

import interfaces.accountsmanagement.AccountsManagementDialog;
import interfaces.editaccount.EditAccountDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.Tools;
import tools.components.Border;
import tools.components.Dialog;
import tools.components.DialogPane;
import tools.binaryfilemanager.BinaryFileManager;

import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;
import worldclasses.accounts.UserAccount;

public class ShowAccountDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;

    private JLabel imageLabel;
    private UserPanel userPanel;

    private JButton removeButton;
    private JButton accountsManagementButton;
    private JButton signoutButton;
    private JButton editButton;

    private EditAccountDialog editAccountDialog;

    /* CONSTRUCTORS _________________________________________________________ */
    public ShowAccountDialog(Account account) {
        super(new JFrame(), true);
        this.account = account;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel mainPanel;
        JPanel buttonsPanel;

        // Set up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(480, 450);
        this.setLocationRelativeTo(null);
        this.setTitle("Datos Usuario");
//        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        this.imageLabel = new JLabel(Tools.getImageIcon(this.getAccount().getImage(), 165, 165));

        this.userPanel = new UserPanel(this.getAccount());

        this.removeButton = new JButton("Eliminar");
        this.accountsManagementButton = new JButton("Cuentas");
        this.signoutButton = new JButton("Cerrar sesion");
        this.editButton = new JButton("Editar");

        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        this.imageLabel.setBorder(new Border(10, 20, 10, 20));

        // ---------------------------------------------------------------------
        mainPanel.add(this.imageLabel, BorderLayout.CENTER);
        mainPanel.add(this.userPanel, BorderLayout.SOUTH);

        buttonsPanel.add(this.removeButton);

        if (this.getAccount() instanceof AdminAccount) {
            buttonsPanel.add(this.accountsManagementButton);
        }
        buttonsPanel.add(this.signoutButton);
        buttonsPanel.add(this.editButton);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.removeButton.addActionListener(ae -> {
            this.removeAction();
        });

        this.accountsManagementButton.addActionListener(ae -> {
            this.accountsManagementAction();
        });

        this.signoutButton.addActionListener(ae -> {
            int option = DialogPane.yesNoOption("Cerrar Sesion?");
            if (option == DialogPane.YES_OPTION) {
                this.dispose();
                this.okAction();
            }
        });

        this.editButton.addActionListener(ae -> {
            this.editAction();
        });
    }

    /* ______________________________________________________________________ */
    public void removeAction() {
        int option = DialogPane.yesNoOption("Eliminar cuenta?");

        if (option == DialogPane.YES_OPTION) {
            this.dispose();
            this.okAction();
            removeAccount(this.getAccount());
        }
    }

    /* ______________________________________________________________________ */
    public void accountsManagementAction() {
        this.setVisible(false);
        new AccountsManagementDialog((AdminAccount) getAccount()).showDialog();
        this.setVisible(true);
    }

    /* ______________________________________________________________________ */
    public void editAction() {
        this.setVisible(false);
        this.editAccountDialog = new EditAccountDialog(this.getAccount());

        int state = this.editAccountDialog.showDialog();

        if (state == EditAccountDialog.OK_OPTION) {

            removeAccount(this.getAccount());
            System.out.println("removed account: " + this.getAccount());

            this.setAccount(this.editAccountDialog.getAccount());
            System.out.println("Edited account: " + this.getAccount());

            this.imageLabel.setIcon(Tools.getImageIcon(this.getAccount().getImage(), 165, 165));
            this.userPanel.setUsername(this.getAccount().getUsername());
            this.userPanel.setNickname(this.getAccount().getNickname());

            if (this.getAccount() instanceof UserAccount) {
                this.userPanel.setLevel(((UserAccount) this.getAccount()).getLevel());
                this.userPanel.setPoints(((UserAccount) this.getAccount()).getPoints());
            }

            BinaryFileManager manager = new BinaryFileManager("accounts.dat");
            manager.add(this.getAccount());
        }
        this.setVisible(true);
    }

    /* ______________________________________________________________________ */
    public void removeAccount(Account account) {
        BinaryFileManager manager = new BinaryFileManager("accounts.dat");
        ArrayList<Object> objects = manager.read();
        manager.clear();

        for (Object object : objects) {
            Account _account = (Account) object;
            if (!_account.getNickname().equals(account.getNickname())) {
                manager.add(_account);
            }
        }
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new ShowAccountDialog(new AdminAccount(
                "Alejandro",
                "Admin",
                "password",
                "/images/profile/image-31.png"
        )).showTestDialog();
    }
}
