package interfaces.accountstatistics;

import interfaces.themestatistics.ThemesStatisticsDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import utilities.Dialog;
import utilities.binaryfilemanager.BinaryFileManager;
import worldclasses.accounts.Account;
import worldclasses.accounts.UserAccount;

public class AccountStatisticsDialog extends Dialog {

    /* ATTRIBUTTES __________________________________________________________ */
    private final ArrayList<Account> accounts;

    private StatisticsGraph levelStatisticsGraph;
    private StatisticsGraph pointsStatisticsGraph;
    private JButton themesStatisticsButton;
    private JButton backButton;

    /* CONSTRUCTORS__________________________________________________________ */
    public AccountStatisticsDialog(ArrayList<Account> accounts) {
        super(new JFrame(), true);
        this.accounts = accounts;

        this.initComponents();
        this.initEvents();
    }

    /* ______________________________________________________________________ */
    private void initComponents() {
        ArrayList<Integer> levels = new ArrayList<>();
        ArrayList<Integer> points = new ArrayList<>();

        JPanel centerPanel;
        JPanel southPanel;

        JPanel usersPanel;
        JPanel levelsPanel;
        JPanel pointsPanel;

        JScrollPane scrollPane;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(900, 500);
        this.setMinimumSize(new Dimension(800, 400));
        this.setLocationRelativeTo(null);
        this.setTitle("Estadisticas");

        // Set up Components ---------------------------------------------------
        this.levelStatisticsGraph = new StatisticsGraph();
        this.pointsStatisticsGraph = new StatisticsGraph();

        this.themesStatisticsButton = new JButton("Temas");
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
        for (Account account : accounts) {
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
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setBorder(new EmptyBorder(0, 15, 0, 0));

        // ---------------------------------------------------------------------
        levelsPanel.add(new JLabel("Niveles", JLabel.CENTER), BorderLayout.NORTH);
        levelsPanel.add(this.levelStatisticsGraph, BorderLayout.CENTER);

        pointsPanel.add(new JLabel("Puntos", JLabel.CENTER), BorderLayout.NORTH);
        pointsPanel.add(this.pointsStatisticsGraph, BorderLayout.CENTER);

        centerPanel.add(scrollPane);
        centerPanel.add(levelsPanel);
        centerPanel.add(pointsPanel);

        southPanel.add(this.themesStatisticsButton);
        southPanel.add(this.backButton);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        this.themesStatisticsButton.addActionListener(ae -> {
            new ThemesStatisticsDialog().showDialog();
        });

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
