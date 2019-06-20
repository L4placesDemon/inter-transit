package interfaces.themestatistics;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import worldclasses.themes.Theme;

public class ThemeButton extends JButton {

    /* ATTRIBUTES ___________________________________________________________ */
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
        this.setLayout(new GridLayout());

        imageLabel = new JLabel();
        if (this.theme.getImage() != null) {
            imageLabel.setIcon(this.theme.getImage());
        }

        titleLabel = new JLabel(this.theme.getTitle(), JLabel.CENTER);
        viewsLabel = new JLabel(this.theme.getViews()+ " views", JLabel.CENTER);

        this.add(imageLabel);
        this.add(titleLabel);
        this.add(viewsLabel);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {

    }
}
