package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import tools.Tools;
import tools.components.Dialog;
import worldclasses.Settings;

public class AboutDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 7731079211910018196L;

    private JButton closeButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public AboutDialog() {

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        String theme;
        String logo = null;

        JPanel descriptionPanel;
        JPanel creditsPanel;

        JPanel logosPanel;
        JPanel centerPanel;
        JPanel labelsPanel;
        JPanel buttonsPanel;

        theme = Settings.getCurrentSettings().getTheme();
        if (theme.equals(Settings.LIGHT_THEME)) {
            logo = Settings.LIGHT_LOGO;
        } else if (theme.equals(Settings.DARK_THEME)) {
            logo = Settings.DARK_LOGO;
        }

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        this.setTitle("Informacion");
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        descriptionPanel = new JPanel();
        creditsPanel = new JPanel();
        this.closeButton = new JButton("Cerrar");

        logosPanel = new JPanel(new GridLayout(1, 2));
        centerPanel = new JPanel(new GridLayout(2, 1));
        labelsPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        buttonsPanel = new JPanel(new FlowLayout());

        // ---------------------------------------------------------------------
        logosPanel.setBorder(new EmptyBorder(0, 13, 0, 0));

        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.setBorder(new EtchedBorder());

        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));
        creditsPanel.setBorder(new EtchedBorder());

        labelsPanel.setBorder(new EmptyBorder(0, 20, 5, 20));

        // ---------------------------------------------------------------------
        descriptionPanel.add(new JLabel(
                "Inter-Transit\n"
                + "El programa estara enfocado en brindarle informacion al\n"
                + "usuario acerca de la cultura ciudadana en cuanto a\n"
                + "movilidad vehicular.\n"
                + "Ademas de realizar diferentes cuestionarios acerca de los\n"
                + "temas vistos.\n"
        ));
        creditsPanel.add(new JLabel(
                "Snow Gryphon Software\n"
                + "\n"
        ));

        logosPanel.add(new JLabel(Tools.getImageIcon(logo, 213, 213)));
        logosPanel.add(new JLabel(Tools.getImageIcon("logos/company_logo", 180, 180)));

        labelsPanel.add(descriptionPanel);
        labelsPanel.add(creditsPanel);

        centerPanel.add(logosPanel);
        centerPanel.add(labelsPanel);

        buttonsPanel.add(this.closeButton);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
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
