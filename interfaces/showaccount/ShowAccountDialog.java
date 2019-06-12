package interfaces.showaccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaces.accountsmanagement.AccountsManagementDialog;
import interfaces.editaccount.EditAccountDialog;

import utilities.Border;
import utilities.Dialog;
import utilities.DialogPane;
import utilities.Utilities;
import utilities.binaryfilemanager.BinaryFileManager;

import worldclasses.UserAccount;
import worldclasses.Account;
import worldclasses.AdminAccount;

public class ShowAccountDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;

    private JLabel imageLabel;
    private UserPanel userPanel;

    private JButton removeButton;
    private JButton accountsManagementButton;
    private JButton signoutButton;
    private JButton editButton;

    private AccountsManagementDialog accountsManagementDialog;
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
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        this.imageLabel = new JLabel(Utilities.getImageIcon(this.account.getImage(), 165, 165));

        this.userPanel = new UserPanel(this.account);

        this.removeButton = new JButton("Eliminar");
        this.accountsManagementButton = new JButton("Cuentas");
        this.signoutButton = new JButton("Cerrar sesion");
        this.editButton = new JButton("Editar");

        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new GridLayout(1, 4, 7, 7));

        // ---------------------------------------------------------------------
        this.imageLabel.setBorder(new Border(10, 20, 10, 20));
        this.imageLabel.setFont(Dialog.DEFAULT_FONT);

        this.removeButton.setFont(Dialog.DEFAULT_FONT);
        this.accountsManagementButton.setFont(Dialog.DEFAULT_FONT);
        this.signoutButton.setFont(Dialog.DEFAULT_FONT);
        this.editButton.setFont(Dialog.DEFAULT_FONT);

        mainPanel.setBackground(Color.white);

        buttonsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        buttonsPanel.setBackground(Color.white);

        // ---------------------------------------------------------------------
        mainPanel.add(this.imageLabel, BorderLayout.CENTER);
        mainPanel.add(this.userPanel, BorderLayout.SOUTH);

        buttonsPanel.add(this.removeButton);

        if (this.account instanceof AdminAccount) {
            buttonsPanel.add(this.accountsManagementButton);
        } else {
            buttonsPanel.add(new JLabel());
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
            this.dispose();
            this.ok();
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
            this.ok();
            removeAccount(this.account);
        }
    }

    /* ______________________________________________________________________ */
    public void accountsManagementAction() {
        this.setVisible(false);
        new AccountsManagementDialog((AdminAccount) account).showDialog();
        this.setVisible(true);
    }

    /* ______________________________________________________________________ */
    public void editAction() {
        this.setVisible(false);
        this.editAccountDialog = new EditAccountDialog(this.account);

        int state = this.editAccountDialog.showDialog();

        if (state == EditAccountDialog.OK_OPTION) {

            removeAccount(this.account);
            System.out.println("removed account: " + this.account);

            this.account = this.editAccountDialog.getAccount();
            System.out.println("Edited account: " + this.account);

            this.imageLabel.setIcon(Utilities.getImageIcon(this.account.getImage(), 165, 165));
            this.userPanel.setUsername(this.account.getUsername());
            this.userPanel.setNickname(this.account.getNickname());

            if (this.account instanceof UserAccount) {
                this.userPanel.setLevel(((UserAccount) this.account).getPoints());
                this.userPanel.setPoints(((UserAccount) this.account).getPoints());
            }

            BinaryFileManager manager = new BinaryFileManager("accounts.dat");
            manager.add(this.account);
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

    /* ______________________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new ShowAccountDialog(new AdminAccount(
                "Alejandro", "Admin", "password", "/images/profile/image-31.png"))
                .setVisible(true);
    }
}
