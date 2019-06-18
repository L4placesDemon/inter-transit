package interfaces.workshops;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import worldclasses.themes.Theme;

public class ThemeButton extends JButton {

    /* ATTRIBUTES ___________________________________________________________ */
    private JLabel imageLabel;
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JLabel progressLabel;
    private JLabel valueLabel;

    private Theme theme;

    /* CONSTRUCTORS _________________________________________________________ */
    public ThemeButton(Theme theme) {
        this.theme = theme;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        this.setLayout(new GridLayout());

        this.imageLabel = new JLabel();
        if (this.theme.getImage() != null) {
            this.imageLabel.setIcon(this.theme.getImage());
        }

        this.titleLabel = new JLabel(this.theme.getTitle(), JLabel.CENTER);
        this.descriptionLabel = new JLabel(this.theme.getDescription(), JLabel.CENTER);
        this.progressLabel = new JLabel(this.theme.getProgress() + "%", JLabel.CENTER);
        this.valueLabel = new JLabel("$" + this.theme.getValue(), JLabel.CENTER);

        this.add(imageLabel);
        this.add(titleLabel);
        this.add(descriptionLabel);
        this.add(progressLabel);
        this.add(valueLabel);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {

    }
}
