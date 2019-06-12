package interfaces.workshops;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import worldclasses.Account;
import worldclasses.Theme;
import worldclasses.UserAccount;

public class WorkshopsFrame extends JFrame {

    private Account account;
    private ArrayList<Theme> themes;

    private JButton backButton;

    public WorkshopsFrame(Account account, ArrayList<Theme> themes) {
        this.account = account;
        this.themes = themes;

        this.initComponents();
        this.initEvents();
    }

    private void initComponents() {
        UserPanel northPanel;
        JPanel centerPanel;
        JPanel southPanel;

        // Set up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("Temas");

        // Set up Components ---------------------------------------------------
        this.backButton = new JButton("Volver");

        northPanel = new UserPanel(this.account);
        centerPanel = new JPanel();
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------------------------
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    private void initEvents() {
        // Frame Events --------------------------------------------------------
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Components Events ---------------------------------------------------
        this.backButton.addActionListener(ae -> {
            this.dispose();
        });
    }

    public Account getAccount() {
        return this.account;
    }

    public ArrayList<Theme> getThemes() {
        return this.themes;
    }

    public static void main(String[] args) {
        new WorkshopsFrame(new UserAccount(
                "Alejandro",
                "413J0c",
                "passwd",
                "/images/profile/image-31.png"),
                new ArrayList<>()
        ).setVisible(true);
    }
}
