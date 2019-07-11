package interfaces.accountsmanagement;

import interfaces.accountstatistics.AccountStatisticsDialog;
import interfaces.editaccount.EditAccountDialog;
import interfaces.registeraccount.RegisterAccountDialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tools.Tools;
import tools.filemanager.BinaryFileManager;
import tools.components.Dialog;
import tools.components.DialogPane;

import worldclasses.Settings;
import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;

public class AccountsManagementDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -2193683787062161978L;

    private AdminAccount adminAccount;
    private ArrayList<Object> accounts;

    private JPanel centerPanel;
    private AccountButton selectedAccountButton;

    private JButton backButton;
    private JButton editButton;
    private JButton removeButton;
    private JButton registerButton;
    private JButton statisticsButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public AccountsManagementDialog(AdminAccount adminAccount) {

        this.adminAccount = adminAccount;
        this.accounts = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE).read();

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel adminPanel;
        JPanel southPanel;
        JScrollPane scrollPane;

        // Set Up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(520, 500);
        this.setMinimumSize(new Dimension(380, 260));
        this.setLocationRelativeTo(null);
        this.setTitle("Administrador de Cuentas");

        // Set Up Components ---------------------------------------------------
        this.centerPanel = new JPanel(new GridBagLayout());

        this.backButton = new JButton("Volver");
        this.editButton = new JButton("Editar");
        this.removeButton = new JButton("Eliminar");
        this.registerButton = new JButton("Registrar");
        this.statisticsButton = new JButton("Estadisticas");

        adminPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        scrollPane = new JScrollPane(this.centerPanel);

        // ---------------------------------------------------------------------
        this.updateAccountButtons();

        adminPanel.add(new JLabel(Tools.getImageIcon(this.getAdminAccount().getImage(), 60, 60)),
                BorderLayout.CENTER
        );
        adminPanel.add(
                new JLabel(this.getAdminAccount().getNickname(), JLabel.CENTER),
                BorderLayout.SOUTH
        );

        scrollPane.getVerticalScrollBar().setUnitIncrement(7);

        southPanel.add(this.backButton);
        southPanel.add(this.editButton);
        southPanel.add(this.removeButton);
        southPanel.add(this.registerButton);
        southPanel.add(this.statisticsButton);

        this.add(adminPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.backButton.addActionListener(ae -> {
            this.dispose();
        });

        this.registerButton.addActionListener(ae -> {
            this.registerAccountAction();
        });

        this.editButton.addActionListener(ae -> {
            this.editAccountAction();
        });

        this.removeButton.addActionListener(ae -> {
            this.removeAccountAction();
        });

        this.statisticsButton.addActionListener(ae -> {
            this.showStatisticsAction();
        });
    }

    /* ______________________________________________________________________ */
    private void registerAccountAction() {
        this.setVisible(false);
        RegisterAccountDialog registerAccountDialog = new RegisterAccountDialog();
        registerAccountDialog.showDialog();

        this.centerPanel.removeAll();
        this.updateAccountButtons();
        this.centerPanel.updateUI();
        this.setVisible(true);
    }

    /* ______________________________________________________________________ */
    private void editAccountAction() {
        if (this.selectedAccountButton != null) {
            Account account = this.selectedAccountButton.getAccount();
            EditAccountDialog editAccountDialog = new EditAccountDialog(account);

            this.setVisible(false);
            int state = editAccountDialog.showDialog();

            if (state == EditAccountDialog.OK_OPTION) {

                System.out.println("removed account: " + account);
                removeAccount(account);

                account = editAccountDialog.getAccount();
                System.out.println("Edited account: " + account);

                this.selectedAccountButton.updateAccount(account);
                new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE).add(account);
            }

            this.setVisible(true);
        }
    }

    /* ______________________________________________________________________ */
    public void removeAccountAction() {
        if (this.selectedAccountButton != null) {
            Account account = this.selectedAccountButton.getAccount();
            int option = DialogPane.showOption(
                    "Eliminar Cuenta", "Deesa eliminar definitivamente la cuenta?"
            );

            if (option == DialogPane.YES_OPTION) {
                removeAccount(account);
                this.getAccounts().remove(account);

                this.centerPanel.removeAll();
                this.updateAccountButtons();
                this.centerPanel.updateUI();
            }
        }
    }

    /* ______________________________________________________________________ */
    private void showStatisticsAction() {
        this.setVisible(false);

        ArrayList<Account> _accounts = new ArrayList<>();
        this.getAccounts().forEach(i -> {
            _accounts.add((Account) i);
        });
        new AccountStatisticsDialog(_accounts).showDialog();

        this.setVisible(true);
    }

    /* ______________________________________________________________________ */
    public void updateAccountButtons() {
        ButtonGroup buttonGroup = new ButtonGroup();
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        sortAccounts();

        this.setAccounts(new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE).read());

        for (int i = 0; i < this.getAccounts().size(); i++) {
            Account account = (Account) this.getAccounts().get(i);

            if (!account.getNickname().equals(this.getAdminAccount().getNickname())) {

                AccountButton accountButton = new AccountButton(account);
                accountButton.addActionListener(ae -> {
                    this.selectedAccountButton = accountButton;
                });

                buttonGroup.add(accountButton);
                this.centerPanel.add(accountButton, c);

                c.gridx++;
                if (c.gridx == 5) {
                    c.gridx = 0;
                    c.gridy++;
                }
            }
        }
    }

    /* ______________________________________________________________________ */
    public void removeAccount(Account account) {
        BinaryFileManager manager = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE);
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
    private void sortAccounts() {
        BinaryFileManager manager = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE);
        ArrayList<Account> _accounts = new ArrayList<>();

        manager.read().forEach(i -> {
            _accounts.add((Account) i);
        });
        _accounts.sort((Account a, Account b) -> {
            return a instanceof AdminAccount ? 0 : 1;
        });
        _accounts.sort((Account a, Account b) -> {
            return a.getNickname().compareTo(b.getNickname());
        });

        manager.clear();
        _accounts.forEach(i -> {
            manager.add(i);
        });
    }

    /* GETTERS ______________________________________________________________ */
    public AdminAccount getAdminAccount() {
        return adminAccount;
    }

    /* ______________________________________________________________________ */
    public ArrayList<Object> getAccounts() {
        return accounts;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAdminAccount(AdminAccount adminAccount) {
        this.adminAccount = adminAccount;
    }

    /* ______________________________________________________________________ */
    public void setAccounts(ArrayList<Object> accounts) {
        this.accounts = accounts;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        BinaryFileManager manager = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE);
        new AccountsManagementDialog((AdminAccount) manager.read().get(0)).showTestDialog();
    }
}
