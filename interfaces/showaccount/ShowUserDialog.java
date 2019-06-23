package interfaces.showaccount;

import interfaces.editaccount.EditAccountDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.Tools;
import tools.components.Border;
import tools.components.DialogPane;
import tools.binaryfilemanager.BinaryFileManager;

import worldclasses.accounts.Account;
import worldclasses.accounts.UserAccount;

public class ShowUserDialog extends ShowAccountDialog {

    /* ATTRIBUTES ___________________________________________________________ */
    protected Account account;

    protected JLabel imageLabel;
    private UserPanel userPanel;

    protected JPanel buttonsPanel;

    protected JButton removeButton;
    protected JButton signoutButton;
    protected JButton editButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public ShowUserDialog(UserAccount userAccount) {
        super(userAccount);
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
        this.imageLabel = new JLabel(Tools.getImageIcon(this.getAccount().getImage(), 165, 165));

        this.userPanel = new UserPanel((UserAccount) this.getAccount());

        this.removeButton = new JButton("Eliminar");
        this.signoutButton = new JButton("Cerrar sesion");
        this.editButton = new JButton("Editar");

        mainPanel = new JPanel(new BorderLayout());
        this.buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        this.imageLabel.setBorder(new Border(10, 20, 10, 20));

        // ---------------------------------------------------------------------
        mainPanel.add(this.imageLabel, BorderLayout.CENTER);
        mainPanel.add(this.userPanel, BorderLayout.SOUTH);

        this.buttonsPanel.add(this.removeButton);

        this.buttonsPanel.add(this.signoutButton);
        this.buttonsPanel.add(this.editButton);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(this.buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    @Override
    protected void initEvents() {
        // Components Events ---------------------------------------------------
        this.removeButton.addActionListener(ae -> {
            this.removeAction();
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
            this.userPanel.setUsername(this.getAccount().getUsername());
            this.userPanel.setNickname(this.getAccount().getNickname());

            if (this.getAccount() instanceof UserAccount) {
                this.userPanel.setLevel(((UserAccount) this.getAccount()).getLevel());
                this.userPanel.setPoints(((UserAccount) this.getAccount()).getPoints());
            }

            manager = new BinaryFileManager("accounts.dat");
            manager.add(this.getAccount());
        }
        this.setVisible(true);
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new ShowUserDialog(new UserAccount(
                "Alejandro",
                "Admin",
                "password",
                "/images/profile/image-31.png"
        )).showTestDialog();
    }
}
