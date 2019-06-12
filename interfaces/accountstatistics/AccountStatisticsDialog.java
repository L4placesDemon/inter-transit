package interfaces.accountstatistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import utilities.Dialog;
import utilities.binaryfilemanager.BinaryFileManager;
import worldclasses.Account;
import worldclasses.UserAccount;

public class AccountStatisticsDialog extends Dialog {

    /* ATTRIBUTTES __________________________________________________________ */
    private final ArrayList<Account> accounts;

    private JTable table;
    private StatisticsGraph levelStatisticsGraph;
    private StatisticsGraph pointsStatisticsGraph;
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
        String[] headers = {"Indice", "Apodo", "Nivel", "Puntos"};
        ArrayList<Integer> levels = new ArrayList<>();
        ArrayList<Integer> points = new ArrayList<>();

        JScrollPane scrollPane;
        JPanel centerPanel;
        JPanel southPanel;
        JPanel levelsPanel;
        JPanel pointsPanel;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(1000, 500);
        this.setMinimumSize(new Dimension(900, 400));
        this.setLocationRelativeTo(null);
        this.setTitle("Estadisticas");

        // Set up Components ---------------------------------------------------
        this.table = new JTable();

        this.levelStatisticsGraph = new StatisticsGraph();
        this.pointsStatisticsGraph = new StatisticsGraph();

        this.backButton = new JButton("Volver");

        scrollPane = new JScrollPane(table);
        centerPanel = new JPanel(new GridLayout(1, 3));
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        levelsPanel = new JPanel(new BorderLayout());
        pointsPanel = new JPanel(new BorderLayout());

        // ---------------------------------------------------------------------
        table.setModel(new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setBackground(Color.white);

        for (Account account : accounts) {
            if (account instanceof UserAccount) {
                levels.add(((UserAccount) account).getLevel());
                points.add(((UserAccount) account).getPoints());
                this.addAccountToTable((UserAccount) account);
            }
        }
        this.levelStatisticsGraph.setValues(levels);
        this.pointsStatisticsGraph.setValues(points);

        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.getVerticalScrollBar().setUnitIncrement(7);

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
    public void addAccountToTable(UserAccount account) {
        String cityIndex = table.getRowCount() + 1 + "";

        String[] rowObject = {
            cityIndex,
            account.getNickname(),
            account.getLevel() + "",
            account.getPoints() + ""
        };

        ((DefaultTableModel) table.getModel()).addRow(rowObject);
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
