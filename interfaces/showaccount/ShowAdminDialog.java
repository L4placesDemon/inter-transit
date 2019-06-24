package interfaces.showaccount;

import interfaces.accountsmanagement.AccountsManagementDialog;
import interfaces.editaccount.EditAccountDialog;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.Tools;
import tools.binaryfilemanager.BinaryFileManager;
import tools.components.Border;

import worldclasses.accounts.AdminAccount;

public class ShowAdminDialog extends ShowAccountDialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private AdminPanel adminPanel;

    private JButton themesManagementButton;
    private JButton accountsManagementButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public ShowAdminDialog(AdminAccount adminAccount) {
        super(adminAccount);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    protected void initComponents() {
        JPanel mainPanel;

        // Set up Frame --------------------------------------------------------
        this.setTitle("Datos Administrador");
        super.initComponents();

        // Set up Components ---------------------------------------------------
        super.accountPanel = new AdminPanel((AdminAccount) super.getAccount());
        this.themesManagementButton = new JButton("Temas");
        this.accountsManagementButton = new JButton("Cuentas");

        mainPanel = new JPanel(new BorderLayout());

        // ---------------------------------------------------------------------
        super.imageLabel.setBorder(new Border(10, 20, 10, 20));

        // ---------------------------------------------------------------------
        mainPanel.add(super.imageLabel, BorderLayout.CENTER);
        mainPanel.add(super.accountPanel, BorderLayout.SOUTH);

        super.buttonsPanel.add(super.backButton);
        super.buttonsPanel.add(super.removeButton);
        super.buttonsPanel.add(this.themesManagementButton);
        super.buttonsPanel.add(this.accountsManagementButton);
        super.buttonsPanel.add(super.signoutButton);
        super.buttonsPanel.add(super.editButton);

        super.add(mainPanel, BorderLayout.CENTER);
        super.add(super.buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    @Override
    protected void initEvents() {
        // Components Events ---------------------------------------------------
        super.initEvents();

        this.themesManagementButton.addActionListener(ae -> {
            this.themesManagementAction();
        });

        this.accountsManagementButton.addActionListener(ae -> {
            this.accountsManagementAction();
        });
    }

    /* ______________________________________________________________________ */
    public void themesManagementAction() {
        this.setVisible(false);
//        new ThemesManagementDialog((AdminAccount) getAccount()).showDialog();
        this.setVisible(true);
    }

    /* ______________________________________________________________________ */
    public void accountsManagementAction() {
        this.setVisible(false);
        new AccountsManagementDialog((AdminAccount) getAccount()).showDialog();
        this.setVisible(true);
    }

    /* ______________________________________________________________________ */
    @Override
    public void editAction() {
        EditAccountDialog editAccountDialog;
        BinaryFileManager manager;

        this.setVisible(false);
        editAccountDialog = new EditAccountDialog(this.getAccount());

        int state = editAccountDialog.showDialog();

        if (state == EditAccountDialog.OK_OPTION) {
            removeAccount(this.getAccount());
            System.out.println("removed account: " + this.getAccount());

            this.setAccount(editAccountDialog.getAccount());
            System.out.println("Edited account: " + this.getAccount());

            super.imageLabel.setIcon(Tools.getImageIcon(this.getAccount().getImage(), 165, 165));
            super.accountPanel.setUsername(this.getAccount().getUsername());
            super.accountPanel.setNickname(this.getAccount().getNickname());

            manager = new BinaryFileManager("accounts.dat");
            manager.add(this.getAccount());
        }
        this.setVisible(true);
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new ShowAdminDialog(new AdminAccount(
                "Alejandro",
                "Admin",
                "password",
                "profile/image-31"
        )).showTestDialog();
    }
}
