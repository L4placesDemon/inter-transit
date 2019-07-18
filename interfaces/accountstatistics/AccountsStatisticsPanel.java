package interfaces.accountstatistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import tools.filemanager.BinaryFileManager;
import tools.components.Panel;
import worldclasses.Settings;

import worldclasses.accounts.Account;
import worldclasses.accounts.UserAccount;

public class AccountsStatisticsPanel extends Panel {

    /* ATTRIBUTTES __________________________________________________________ */
    private static final long serialVersionUID = -877346624852504076L;

    private ArrayList<Account> accounts;

    private StatisticsGraph levelStatisticsGraph;
    private StatisticsGraph pointsStatisticsGraph;
    private JButton backButton;

    /* CONSTRUCTORS__________________________________________________________ */
    public AccountsStatisticsPanel(ArrayList<Account> accounts) {

        this.accounts = accounts;

        this.initComponents();
        this.initEvents();
    }

    /* METHIODS _____________________________________________________________ */
    private void initComponents() {
        ArrayList<Integer> levels = new ArrayList<>();
        ArrayList<Integer> points = new ArrayList<>();

        JPanel centerPanel;
        JPanel southPanel;

        JPanel usersPanel;
        JPanel levelsPanel;
        JPanel pointsPanel;

        JScrollPane scrollPane;

        Color color = null;
        String theme = Settings.getCurrentSettings().getTheme();
        if (theme.equals(Settings.LIGHT_THEME)) {
            color = Color.black;
        } else if (theme.equals(Settings.DARK_THEME)) {
            color = Color.white;
        }

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
//        this.setTitle("Estadisticas");

        // Set up Components ---------------------------------------------------
        this.levelStatisticsGraph = new StatisticsGraph(color);
        this.pointsStatisticsGraph = new StatisticsGraph(color);

        this.backButton = new JButton("Volver");

        centerPanel = new JPanel(new GridLayout(1, 3));
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        usersPanel = new JPanel();
        levelsPanel = new JPanel(new BorderLayout());
        pointsPanel = new JPanel(new BorderLayout());

        scrollPane = new JScrollPane(usersPanel);

        // ---------------------------------------------------------------------
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));

        int i = 1;
        for (Account account : this.getAccounts()) {
            if (account instanceof UserAccount) {
                UserAccount userAccount = (UserAccount) account;

                levels.add((userAccount).getLevel());
                points.add((userAccount).getPoints());

                AccountPanel accountPanel = new AccountPanel(userAccount, i);
                accountPanel.setBorder(new EmptyBorder(3, 0, 3, 0));

                usersPanel.add(accountPanel);
                i++;
            }
        }

        this.levelStatisticsGraph.setValues(levels);
        this.pointsStatisticsGraph.setValues(points);

        scrollPane.getVerticalScrollBar().setUnitIncrement(7);
        scrollPane.setBorder(new EmptyBorder(0, 15, 0, 0));

        // ---------------------------------------------------------------------
        levelsPanel.add(new JLabel("Niveles", JLabel.CENTER), BorderLayout.NORTH);
        levelsPanel.add(this.levelStatisticsGraph, BorderLayout.CENTER);

        pointsPanel.add(new JLabel("Puntos", JLabel.CENTER), BorderLayout.NORTH);
        pointsPanel.add(this.pointsStatisticsGraph, BorderLayout.CENTER);

        centerPanel.add(scrollPane);
        centerPanel.add(levelsPanel);
        centerPanel.add(pointsPanel);

        southPanel.add(this.backButton);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.backButton.addActionListener(ae -> {
            this.dispose();
        });
    }

    /* GETTERS ______________________________________________________________ */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    /* ______________________________________________________________________ */
    @Override
    public JButton getCloseButton() {
        return this.backButton;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE).read().forEach(i -> {
            accounts.add((Account) i);
        });
        new AccountsStatisticsPanel(accounts).showTestDialog();
    }
}
