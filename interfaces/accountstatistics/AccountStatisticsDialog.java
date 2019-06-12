package interfaces.accountstatistics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import utilities.Dialog;
import utilities.binaryfilemanager.BinaryFileManager;
import worldclasses.Account;
import worldclasses.UserAccount;

public class AccountStatisticsDialog extends Dialog {

    /* ATTRIBUTTES __________________________________________________________ */
    private JButton backButton;

    private ArrayList<Account> accounts;

    /* CONSTRUCTORS__________________________________________________________ */
    public AccountStatisticsDialog(ArrayList<Account> accounts) {
        super(new JFrame(), true);
        this.accounts = accounts;

        this.initComponents();
        this.initEvents();
    }

    /* ______________________________________________________________________ */
    private void initComponents() {
        JPanel centerPanel;
        JPanel southPanel;
        JScrollPane scrollPane;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(380, 500);
        this.setMinimumSize(new Dimension(380, 260));
        this.setLocationRelativeTo(null);
        this.setTitle("Estadisticas");

        // Set up Components ---------------------------------------------------
        this.backButton = new JButton("Volver");

        centerPanel = new JPanel();
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        scrollPane = new JScrollPane(centerPanel);

        // ---------------------------------------------------------------------
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        scrollPane.getVerticalScrollBar().setUnitIncrement(7);

        // ---------------------------------------------------------------------
        this.accounts.forEach(i -> {
            if (i instanceof UserAccount) {
                AccountPanel accountPanel = new AccountPanel((UserAccount) i);
                accountPanel.setBorder(new EmptyBorder(3, 0, 3, 0));

                centerPanel.add(accountPanel);
            }
        });

        southPanel.add(this.backButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        this.backButton.addActionListener(ae -> {
            this.dispose();
        });
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        new BinaryFileManager("accounts.dat").read().forEach(i -> {
            accounts.add((Account) i);
        });
        new AccountStatisticsDialog(accounts).showDialog();
    }
}
