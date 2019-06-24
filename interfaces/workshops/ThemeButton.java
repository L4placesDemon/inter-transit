package interfaces.workshops;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import worldclasses.themes.Theme;

public class ThemeButton extends JButton {

    /* ATTRIBUTES ___________________________________________________________ */
    private Theme theme;

    private JLabel imageLabel;
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JLabel progressLabel;
    private JLabel valueLabel;
    private JLabel viewsLabel;

    /* CONSTRUCTORS _________________________________________________________ */
    public ThemeButton(Theme theme) {
        this.theme = theme;

        this.initComponents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        this.setLayout(new GridLayout());

        this.imageLabel = new JLabel();
        if (this.getTheme().getImage() != null) {
            this.imageLabel.setIcon(this.getTheme().getImage());
        }

        this.titleLabel = new JLabel(this.getTheme().getTitle(), JLabel.CENTER);
        this.descriptionLabel = new JLabel(this.getTheme().getDescription(), JLabel.CENTER);
        this.progressLabel = new JLabel(this.getTheme().getProgress() + "%", JLabel.CENTER);
        this.valueLabel = new JLabel("$" + this.getTheme().getValue(), JLabel.CENTER);
        this.viewsLabel = new JLabel(this.getTheme().getViews() + "", JLabel.CENTER);

        this.add(imageLabel);
        this.add(titleLabel);
        this.add(descriptionLabel);
        this.add(progressLabel);
        this.add(valueLabel);
        this.add(viewsLabel);
    }

    /* GETTERS ______________________________________________________________ */
    public Theme getTheme() {
        return this.theme;
    }

    /* SETTERS ______________________________________________________________ */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
