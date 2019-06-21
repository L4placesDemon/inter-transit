package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import tools.Dialog;
import tools.Tools;

public class AboutDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private JButton closeButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public AboutDialog() {
        super(new JFrame(), true);

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JLabel logoLabel;
        JLabel company_logoLabel;
        JPanel descriptionPanel;
        JPanel creditsPanel;

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
        logoLabel = new JLabel(Tools.getImageIcon("/images/logos/logo.png", 230, 230));
        company_logoLabel = new JLabel(Tools.getImageIcon("/images/logos/company_logo.png", 180, 180));

        descriptionPanel = new JPanel();
        creditsPanel = new JPanel();
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

        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.setBorder(new EtchedBorder());

        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));
        creditsPanel.setBorder(new EtchedBorder());

        labelsPanel.setBorder(new EmptyBorder(0, 20, 5, 20));

        // ---------------------------------------------------------------------
        descriptionPanel.add(programDescriptionLabel);
        creditsPanel.add(programCreditsLabel);

        logosPanel.add(logoLabel);
        logosPanel.add(company_logoLabel);

        labelsPanel.add(descriptionPanel);
        labelsPanel.add(creditsPanel);

        mainPanel.add(logosPanel);
        mainPanel.add(labelsPanel);

        buttonPanel.add(this.closeButton);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.closeButton.addActionListener(ae -> {
            this.dispose();
        });
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new AboutDialog().showTestDialog();
    }
}
