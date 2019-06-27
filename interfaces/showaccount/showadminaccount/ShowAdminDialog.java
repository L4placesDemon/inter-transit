package interfaces.showaccount.showadminaccount;

import interfaces.accountsmanagement.AccountsManagementDialog;
import interfaces.editaccount.EditAccountDialog;
import interfaces.showaccount.ShowAccountDialog;
import interfaces.themesmanagement.ThemesManagementDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.Tools;
import tools.binaryfilemanager.BinaryFileManager;

import worldclasses.accounts.AdminAccount;

public class ShowAdminDialog extends ShowAccountDialog {

    /* ATTRIBUTES ___________________________________________________________ */
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
        JPanel buttonsPanel;

        // Set up Frame --------------------------------------------------------
        super.initComponents();
        
        this.setSize(535, 360);
        this.setLocationRelativeTo(null);
        this.setTitle("Datos Administrador");

        // Set up Components ---------------------------------------------------
        super.accountPanel = new AdminPanel((AdminAccount) super.getAccount());
        this.imageLabel = new JLabel(Tools.getImageIcon(this.getAccount().getImage(), 180, 180));

        this.themesManagementButton = new JButton("Temas");
        this.accountsManagementButton = new JButton("Cuentas");

        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel  = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        mainPanel.add(super.imageLabel, BorderLayout.CENTER);
        mainPanel.add(super.accountPanel, BorderLayout.SOUTH);

        buttonsPanel.add(super.backButton);
        buttonsPanel.add(super.removeButton);
        buttonsPanel.add(this.themesManagementButton);
        buttonsPanel.add(this.accountsManagementButton);
        buttonsPanel.add(super.signoutButton);
        buttonsPanel.add(super.editButton);

        super.add(mainPanel, BorderLayout.CENTER);
        super.add(buttonsPanel, BorderLayout.SOUTH);
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
        new ThemesManagementDialog().showDialog();
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
    protected void editAction() {
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
