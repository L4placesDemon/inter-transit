package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import tools.Tools;
import tools.components.Dialog;
import tools.components.TextArea;

import worldclasses.Settings;

public class AboutDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 7731079211910018196L;

    private JButton closeButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public AboutDialog() {
        try {
            this.initComponents();
            this.initEvents();
        } catch (IOException ex) {
        }
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() throws IOException {
        String theme;
        String logo = null;

        JPanel descriptionPanel;
        JPanel creditsPanel;

        JPanel logosPanel;
        JPanel centerPanel;
        JPanel labelsPanel;
        JPanel buttonsPanel;

        TextArea textArea;
        JEditorPane editorPane;

        Font font = Settings.getCurrentSettings().getFont();
        String path = Tools.class.getResource("/tools") + "";

        String color = "black";
        if (Settings.getCurrentSettings().getTheme().equals(Settings.DARK_THEME)) {
            color = "white";
        }

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
        this.closeButton = new JButton("Cerrar");

        descriptionPanel = new JPanel(new BorderLayout());
        creditsPanel = new JPanel();

        logosPanel = new JPanel(new GridLayout(1, 2));
        centerPanel = new JPanel(new GridLayout(2, 1));
        labelsPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        buttonsPanel = new JPanel(new FlowLayout());

        textArea = new TextArea("Inter-Transit está enfocado en "
                + "brindarle informacion al usuario acerca de la cultura "
                + "ciudadana en cuanto a movilidad vehicular. Permite realizar "
                + "diferentes cuestionarios para probar los conocimientos "
                + "adquiridos."
        );
        editorPane = new JEditorPane(
                "text/html",
                "<font face=" + font.getFamily() + " size=" + font.getSize() / 4 + " color= " + color + ">"
                + "SnowGryphon Software<br>"
                + "<b>Version:</b> Inter-Transit 7.3.2<br>"
                + "<b>System:</b> " + Tools.command("ver") + "<br>"
                + "<b>User Directory:</b> " + path.substring(10, path.indexOf("/tools")) + "</font>"
        );

        // ---------------------------------------------------------------------
        descriptionPanel.setBorder(new EtchedBorder());

        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));
        creditsPanel.setBorder(new EtchedBorder());

        labelsPanel.setBorder(new EmptyBorder(0, 20, 5, 20));

        textArea.setEditable(false);
        editorPane.setEditable(false);

        // ---------------------------------------------------------------------
        descriptionPanel.add(textArea);
        creditsPanel.add(editorPane);

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
