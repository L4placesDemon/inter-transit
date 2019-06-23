package interfaces.showaccount;

import interfaces.accountsmanagement.AccountsManagementDialog;
import interfaces.editaccount.EditAccountDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.Tools;
import tools.binaryfilemanager.BinaryFileManager;
import tools.components.Border;
import tools.components.DialogPane;

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
        this.setLayout(new BorderLayout());
        this.setSize(480, 450);
        this.setLocationRelativeTo(null);
        this.setTitle("Datos Usuario");
//        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        super.imageLabel = new JLabel(Tools.getImageIcon(this.getAccount().getImage(), 165, 165));

        this.adminPanel = new AdminPanel((AdminAccount) super.getAccount());

        super.removeButton = new JButton("Eliminar");
        this.themesManagementButton = new JButton("Temas");
        this.accountsManagementButton = new JButton("Cuentas");
        super.signoutButton = new JButton("Cerrar sesion");
        super.editButton = new JButton("Editar");

        mainPanel = new JPanel(new BorderLayout());
        super.buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        super.imageLabel.setBorder(new Border(10, 20, 10, 20));

        // ---------------------------------------------------------------------
        mainPanel.add(super.imageLabel, BorderLayout.CENTER);
        mainPanel.add(this.adminPanel, BorderLayout.SOUTH);

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
    public void accountsManagementAction() {
        this.setVisible(false);
        new AccountsManagementDialog((AdminAccount) getAccount()).showDialog();
        this.setVisible(true);
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new ShowAdminDialog(new AdminAccount(
                "Alejandro",
                "Admin",
                "password",
                "/images/profile/image-31.png"
        )).showTestDialog();
    }

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

            this.imageLabel.setIcon(Tools.getImageIcon(this.getAccount().getImage(), 165, 165));
            super.accountPanel.setUsername(this.getAccount().getUsername());
            super.accountPanel.setNickname(this.getAccount().getNickname());

            manager = new BinaryFileManager("accounts.dat");
            manager.add(this.getAccount());
        }
        this.setVisible(true);
    }
}
