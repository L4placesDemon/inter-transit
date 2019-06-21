package interfaces.solvetest;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import tools.Border;

import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;

public class TestFrame extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;

    private JLabel nicknameLabel;
    private JLabel pointsLabel;

    private JButton carTestButton;
    private JButton motorcicleTestButton;
    private JButton busTestButton;
    private JButton walkTestButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public TestFrame(Account account) {
        super(new JFrame(), true);
        this.account = account;

        this.initComponents();
        this.initEvents();
    }

    /* ______________________________________________________________________ */
    public TestFrame() {
        this(new AdminAccount("Alejo", "Admin1", "PASSWD"));
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel userPanel;
        JPanel buttonsPanel;

        // Set up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(300, 400));
        this.setLocationRelativeTo(null);
        this.setTitle("TestFrame");

        // Set up Components ---------------------------------------------------
        this.nicknameLabel = new JLabel("nickname");
        this.pointsLabel = new JLabel("points");

        this.carTestButton = new JButton("Car Test");
        this.motorcicleTestButton = new JButton("Motorcicle Test");
        this.busTestButton = new JButton("Bus Test");
        this.walkTestButton = new JButton("Walk Test");

        userPanel = new JPanel(new GridLayout(1, 3));
        buttonsPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        // ---------------------------------------------------------------------
        this.nicknameLabel.setBorder(new Border(0, 5, 0, 5));
        this.pointsLabel.setBorder(new Border(0, 5, 0, 5));

        userPanel.setBorder(new Border(new EmptyBorder(10, 10, 5, 10), new EtchedBorder(), new EmptyBorder(7, 0, 7, 0)));
        buttonsPanel.setBorder(new Border(5, 10, 10, 10));

        // ---------------------------------------------------------------------
        userPanel.add(this.nicknameLabel);
        userPanel.add(new JLabel());
        userPanel.add(this.pointsLabel);

        buttonsPanel.add(this.carTestButton);
        buttonsPanel.add(this.motorcicleTestButton);
        buttonsPanel.add(this.busTestButton);
        buttonsPanel.add(this.walkTestButton);

        this.add(userPanel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.carTestButton.addActionListener(ae -> {

        });
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
