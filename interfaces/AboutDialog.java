package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import utilities.Utilities;

public class AboutDialog extends JDialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private JLabel logoLabel;
    private JLabel company_logoLabel;

    private JPanel descriptionPanel;
    private JPanel creditsPanel;

    private JButton closeButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public AboutDialog() {
        super(new JFrame(), true);

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JLabel programDescriptionLabel;
        JLabel programCreditsLabel;

        JPanel logosPanel;
        JPanel mainPanel;
        JPanel labelsPanel;
        JPanel buttonPanel;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        this.setTitle("Informacion");
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        this.logoLabel = new JLabel(
                Utilities.getImageIcon("/images/logos/logo.png", 230, 230));
        this.company_logoLabel = new JLabel(
                Utilities.getImageIcon("/images/logos/company_logo.png", 180, 180));

        this.descriptionPanel = new JPanel();
        this.creditsPanel = new JPanel();
        this.closeButton = new JButton("Cerrar");

        programDescriptionLabel = new JLabel(
                "InterTransit\n"
                + "");
        programCreditsLabel = new JLabel(
                "Snow Gryphon Software\n"
                + "");

        logosPanel = new JPanel(new GridLayout(1, 2));
        mainPanel = new JPanel(new GridLayout(2, 1));
        labelsPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        buttonPanel = new JPanel(new FlowLayout());

        // ---------------------------------------------------------------------
        logosPanel.setBorder(new EmptyBorder(0, 13, 0, 0));

        this.descriptionPanel.setLayout(new BoxLayout(this.descriptionPanel, BoxLayout.Y_AXIS));
        this.descriptionPanel.setBorder(new EtchedBorder());
//        this.descriptionPanel.setBackground(Color.white);

        this.creditsPanel.setLayout(new BoxLayout(this.creditsPanel, BoxLayout.Y_AXIS));
        this.creditsPanel.setBorder(new EtchedBorder());
//        this.creditsPanel.setBackground(Color.white);

//        logosPanel.setBackground(Color.white);

//        mainPanel.setBackground(Color.white);

        labelsPanel.setBorder(new EmptyBorder(0, 20, 5, 20));
//        labelsPanel.setBackground(Color.white);

//        buttonPanel.setBackground(Color.white);

        // ---------------------------------------------------------------------
        this.descriptionPanel.add(programDescriptionLabel);
        this.creditsPanel.add(programCreditsLabel);

        logosPanel.add(this.logoLabel);
        logosPanel.add(this.company_logoLabel);

        labelsPanel.add(this.descriptionPanel);
        labelsPanel.add(this.creditsPanel);

        mainPanel.add(logosPanel);
        mainPanel.add(labelsPanel);

        buttonPanel.add(this.closeButton);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Dialog Events -------------------------------------------------------
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Components Events ---------------------------------------------------
        this.closeButton.addActionListener(ae -> {
            this.dispose();
        });
    }

    /* ______________________________________________________________________ */
    public void showDialog() {
        this.setVisible(true);
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new AboutDialog().setVisible(true);
    }
}
