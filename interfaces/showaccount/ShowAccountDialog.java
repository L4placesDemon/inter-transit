package interfaces.showaccount;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.binaryfilemanager.BinaryFileManager;
import tools.components.Dialog;
import tools.components.DialogPane;

import worldclasses.accounts.Account;

public abstract class ShowAccountDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    protected Account account;

    protected JLabel imageLabel;
    protected AccountPanel accountPanel;

    protected JPanel buttonsPanel;

    protected JButton removeButton;
    protected JButton signoutButton;
    protected JButton editButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public ShowAccountDialog(Account account) {
        super();
        this.account = account;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    protected abstract void initComponents();

    /* ______________________________________________________________________ */
    protected abstract void initEvents();

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
    public abstract void editAction();

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
}
