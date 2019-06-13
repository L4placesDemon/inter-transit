package interfaces.accountstatistics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import utilities.Dialog;
import worldclasses.accounts.UserAccount;

public class StatisticsViewerDialog extends Dialog {

    /* ATTRIBUTTES __________________________________________________________ */
    private ArrayList<Object> objects;
    JPanel mainPanel;

    // Table and Graphic
    private JTable table;
    private StatisticsGraph statisticsGraph;

    /* CONSTRUCTORS _________________________________________________________ */
    public StatisticsViewerDialog() {
        super(new JFrame(), true);
        this.objects = new ArrayList<>();

        initComponents();
        initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        // Set Up Frame --------------------------------------------------------
        setLayout(new BorderLayout());
        setSize(320, 450);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(310, 300));
        setTitle("Statistics");

        // Set Up Components ---------------------------------------------------
        mainPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel();

        // Set Up Table --------------------------------------------------------
        // Can not edit cells
        // Can not select multiple rows
        // Can not reorder columns
        // Can not resize columns
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        // Set Up Graphic ------------------------------------------------------
        table.setModel(new DefaultTableModel(new String[]{"indice", "Apodo", "Nivel", "Puntos"}, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        statisticsGraph = new StatisticsGraph();

        // Set Up Panels -------------------------------------------------------
        //citiesPanel.setBackground(Color.white);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.NORTH);
        add(statisticsGraph, BorderLayout.CENTER);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Resize citiesPanel at move(maximize or minimize) and resize window --
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                mainPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() * 0.4)));
            }

            @Override
            public void componentMoved(ComponentEvent ce) {
                mainPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() * 0.4)));
            }
        });
    }

    /* ______________________________________________________________________ */
    public void addObjectAction(Object object) {
        String cityIndex = table.getRowCount() + 1 + "";
        UserAccount account = (UserAccount) object;

        // Add new Object to arrayList ------------------------------------------- 
        objects.add(object);

        // Set and add a new row to table --------------------------------------
        String[] rowObject = {cityIndex, account.getNickname(), account.getPoints() + ""};
        getCitiesTableModel().addRow(rowObject);

        statisticsGraph.repaint();
        requestFocus();
    }

    /* ______________________________________________________________________ */
    public DefaultTableModel getCitiesTableModel() {
        return (DefaultTableModel) table.getModel();
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new StatisticsViewerDialog().showDialog();
    }
}
