package interfaces.showaccount;

import interfaces.editaccount.EditAccountDialog;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import tools.Tools;
import tools.components.Border;
import tools.binaryfilemanager.BinaryFileManager;

import worldclasses.accounts.UserAccount;

public class ShowUserDialog extends ShowAccountDialog {

    /* CONSTRUCTORS _________________________________________________________ */
    public ShowUserDialog(UserAccount userAccount) {
        super(userAccount);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    protected void initComponents() {
        JPanel mainPanel;

        super.initComponents();

        // Set up Components ---------------------------------------------------
        super.accountPanel = new UserPanel((UserAccount) this.getAccount());

        mainPanel = new JPanel(new BorderLayout());

        // ---------------------------------------------------------------------
        super.imageLabel.setBorder(new Border(10, 20, 10, 20));

        // ---------------------------------------------------------------------
        mainPanel.add(super.imageLabel, BorderLayout.CENTER);
        mainPanel.add(super.accountPanel, BorderLayout.SOUTH);

        super.buttonsPanel.add(super.backButton);
        super.buttonsPanel.add(super.removeButton);
        super.buttonsPanel.add(super.signoutButton);
        super.buttonsPanel.add(super.editButton);

        super.add(mainPanel, BorderLayout.CENTER);
        super.add(super.buttonsPanel, BorderLayout.SOUTH);
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

            ((UserPanel) super.accountPanel).setLevel(((UserAccount) this.getAccount()).getLevel());
            ((UserPanel) super.accountPanel).setPoints(((UserAccount) this.getAccount()).getPoints());

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
                "profile/image-31"
        )).showTestDialog();
    }
}
