package interfaces.showaccount.showuseraccount;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaces.editaccount.EditAccountDialog;
import interfaces.showaccount.ShowAccountDialog;
import tools.Tools;
import tools.filemanager.BinaryFileManager;
import worldclasses.Settings;
import worldclasses.accounts.UserAccount;

public class ShowUserDialog extends ShowAccountDialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -1324033568735688963L;

    private JButton showReportButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public ShowUserDialog(UserAccount userAccount) {
        super(userAccount);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    protected void initComponents() {
        JPanel mainPanel;
        JPanel buttonsPanel;

        // Set up Dialog -------------------------------------------------------
        super.initComponents();

        this.setMinimumSize(new Dimension(451, 360));
        this.setLocationRelativeTo(null);
        this.setTitle("Datos Usuario");

        // Set up Components ---------------------------------------------------
        super.accountPanel = new UserPanel((UserAccount) this.getAccount());
        this.imageLabel = new JLabel(Tools.getImageIcon(this.getAccount().getImage(), 120, 120));

        this.showReportButton = new JButton("Reporte");

        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        mainPanel.add(super.imageLabel, BorderLayout.CENTER);
        mainPanel.add(super.accountPanel, BorderLayout.SOUTH);

        buttonsPanel.add(super.backButton);
        buttonsPanel.add(this.showReportButton);
        buttonsPanel.add(super.removeButton);
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

        this.showReportButton.addActionListener(ae -> {
            this.showReportAction();
        });
    }

    /* ______________________________________________________________________ */
    private void showReportAction() {

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

            super.imageLabel.setIcon(Tools.getImageIcon(this.getAccount().getImage(), 120, 120));
            super.accountPanel.setUsername(this.getAccount().getUsername());
            super.accountPanel.setNickname(this.getAccount().getNickname());

            manager = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE);
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
                "profile/image-31"
        )).showTestDialog();
    }
}
