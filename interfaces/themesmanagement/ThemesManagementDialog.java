package interfaces.themesmanagement;

import interfaces.themestatistics.ThemesStatisticsDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tools.components.Dialog;
import worldclasses.themes.Theme;

public class ThemesManagementDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private ArrayList<Theme> themes;

    private JButton themesStatisticsButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public ThemesManagementDialog(ArrayList<Theme> themes) {
        super();
        this.themes = themes;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel centerPanel;
        JPanel southPanel;

        JPanel themesPanel;

        JPanel leftPanel;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);
        this.setMinimumSize(new Dimension(250, 250));
        this.setTitle("Administrador de Temas");
        this.setResizable(true);

        // Set up Components ---------------------------------------------------
        this.themesStatisticsButton = new JButton("Temas");

        centerPanel = new JPanel();
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        themesPanel = new JPanel();

        leftPanel = new JPanel(new BorderLayout());

        // ---------------------------------------------------------------------
        themesPanel.setLayout(new BoxLayout(themesPanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------------------------
        leftPanel.add(new JLabel("Temas", JLabel.CENTER), BorderLayout.NORTH);
        leftPanel.add(themesPanel, BorderLayout.CENTER);

        southPanel.add(this.themesStatisticsButton);

        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.themesStatisticsButton.addActionListener(ae -> {
            new ThemesStatisticsDialog().showDialog();
        });
    }

    /* GETTERS ______________________________________________________________ */
    public ArrayList<Theme> getThemes() {
        return this.themes;
    }

    /* SETTERS ______________________________________________________________ */
    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        ArrayList<Theme> themes = new ArrayList<>();
//        new BinaryFileManager("accounts.dat").read().forEach(i -> {
//            themes.add((Theme) i);
//        });
        new ThemesManagementDialog(themes).showTestDialog();
    }
}
