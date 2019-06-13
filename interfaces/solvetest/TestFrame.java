package interfaces.solvetest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import utilities.Border;
import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;

public class TestFrame extends JFrame {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);

    private Account account;

    private JLabel nicknameLabel;
    private JLabel pointsLabel;

    private JButton carTestButton;
    private JButton motorcicleTestButton;
    private JButton busTestButton;
    private JButton walkTestButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public TestFrame(Account account) {
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
        this.nicknameLabel.setFont(TestFrame.DEFAULT_FONT);
        this.pointsLabel.setBorder(new Border(0, 5, 0, 5));
        this.pointsLabel.setFont(TestFrame.DEFAULT_FONT);
        this.carTestButton.setFont(TestFrame.DEFAULT_FONT);
        this.motorcicleTestButton.setFont(TestFrame.DEFAULT_FONT);
        this.busTestButton.setFont(TestFrame.DEFAULT_FONT);
        this.walkTestButton.setFont(TestFrame.DEFAULT_FONT);

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
        // Frame Events --------------------------------------------------------
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Components Events ---------------------------------------------------
        this.carTestButton.addActionListener(ae -> {
            
        });
    }
}
