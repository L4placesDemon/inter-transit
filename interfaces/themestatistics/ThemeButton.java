package interfaces.themestatistics;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import tools.Tools;

import worldclasses.themes.Theme;

public class ThemeButton extends JButton {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -5958987373964342002L;

    private Theme theme;

    /* CONSTRUCTORS _________________________________________________________ */
    public ThemeButton(Theme theme) {
        this.theme = theme;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JLabel imageLabel;
        JLabel titleLabel;
        JLabel viewsLabel;

        // Set up Button -------------------------------------------------------
        this.setLayout(new GridLayout());

        // Set up Components ---------------------------------------------------
        imageLabel = new JLabel();

        titleLabel = new JLabel(this.theme.getTitle(), JLabel.CENTER);
        viewsLabel = new JLabel(this.theme.getViews() + " views", JLabel.CENTER);

        // ---------------------------------------------------------------------
        if (this.theme.getImage() != null) {
            imageLabel.setIcon(Tools.getImageIcon(this.theme.getImage()));
        }

        // ---------------------------------------------------------------------
        this.add(imageLabel);
        this.add(titleLabel);
        this.add(viewsLabel);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {

    }

    /* GETTERS ______________________________________________________________ */
    public Theme getTheme() {
        return theme;
    }

    /* SETTERS ______________________________________________________________ */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
