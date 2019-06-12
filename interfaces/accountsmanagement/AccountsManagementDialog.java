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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import utilities.Dialog;
import utilities.DialogPane;
import utilities.Utilities;
import utilities.binaryfilemanager.BinaryFileManager;
import worldclasses.Account;
import worldclasses.AdminAccount;

public class AccountsManagementDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private JPanel centerPanel;
    private AccountButton selectedAccountButton;

    private JButton registerAccountButton;
    private JButton removeButton;
    protected JButton editButton;
    private JButton showStatisticsButton;

    private AdminAccount adminAccount;
    private ArrayList<Object> accounts;

    /* CONSTRUCTORS _________________________________________________________ */
    public AccountsManagementDialog(AdminAccount adminAccount) {
        super(new JFrame(), true);

        this.adminAccount = adminAccount;
        this.accounts = new BinaryFileManager("accounts.dat").read();

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
        this.setSize(415, 520);
        this.setMinimumSize(new Dimension(380, 260));
        this.setLocationRelativeTo(null);
        this.setTitle("Administrador de Cuentas");

        // Set Up Components ---------------------------------------------------
        this.centerPanel = new JPanel(new GridBagLayout());

        this.registerAccountButton = new JButton("Registrar");
        this.removeButton = new JButton("Eliminar");
        this.editButton = new JButton("Editar");
        this.showStatisticsButton = new JButton("Estadisticas");

        adminPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        scrollPane = new JScrollPane(this.centerPanel);

        // ---------------------------------------------------------------------
        this.updateAccountButtons();

        adminPanel.add(new JLabel(Utilities.getImageIcon(this.adminAccount.getImage(), 60, 60)), BorderLayout.CENTER);
        adminPanel.add(new JLabel(this.adminAccount.getNickname(), JLabel.CENTER), BorderLayout.SOUTH);

        scrollPane.getVerticalScrollBar().setUnitIncrement(7);

        southPanel.add(this.editButton);
        southPanel.add(this.removeButton);

        southPanel.add(this.registerAccountButton);
        southPanel.add(this.showStatisticsButton);

        this.add(adminPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Frame Events --------------------------------------------------------
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Components Events ---------------------------------------------------
        this.registerAccountButton.addActionListener(ae -> {
            this.registerAccountAction();
        });

        this.editButton.addActionListener(ae -> {
            this.editAccountAction();
        });

        this.removeButton.addActionListener(ae -> {
            this.removeAccountAction();
        });

        this.showStatisticsButton.addActionListener(ae -> {
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

                this.selectedAccountButton.setAccount(account);
                new BinaryFileManager("accounts.dat").add(account);
            }

            this.setVisible(true);
        }
    }

    /* ______________________________________________________________________ */
    public void removeAccountAction() {
        if (this.selectedAccountButton != null) {
            Account account = this.selectedAccountButton.getAccount();
            int option = DialogPane.yesNoOption("Eliminar cuenta?");

            if (option == DialogPane.YES_OPTION) {
                removeAccount(account);
                this.accounts.remove(account);

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
        this.accounts.forEach(i -> {
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

        this.accounts = new BinaryFileManager("accounts.dat").read();

        for (int i = 0; i < this.accounts.size(); i++) {
            Account account = (Account) this.accounts.get(i);

            if (!account.getNickname().equals(this.adminAccount.getNickname())) {

                AccountButton accountButton = new AccountButton(account);
                accountButton.addActionListener(ae -> {
                    this.selectedAccountButton = accountButton;
                });

                buttonGroup.add(accountButton);
                this.centerPanel.add(accountButton, c);

                c.gridx++;
                if (c.gridx == 4) {
                    c.gridx = 0;
                    c.gridy++;
                }
            }
        }
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
    private void sortAccounts() {
        BinaryFileManager manager = new BinaryFileManager("accounts.dat");
        ArrayList<Account> accounts = new ArrayList<>();

        manager.read().forEach(i -> {
            accounts.add((Account) i);
        });
        accounts.sort((Account a, Account b) -> {
            return a instanceof AdminAccount ? 0 : 1;
        });
        accounts.sort((Account a, Account b) -> {
            return a.getNickname().compareTo(b.getNickname());
        });

        manager.clear();
        accounts.forEach(i -> {
            manager.add(i);
        });
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        BinaryFileManager manager = new BinaryFileManager("accounts.dat");
        new AccountsManagementDialog((AdminAccount) manager.read().get(0)).showDialog();
    }
}
