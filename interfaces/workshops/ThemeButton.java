package interfaces.workshops;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import utilities.Utilities;

public class ThemeButton extends JButton {

    /* ATTRIBUTES ___________________________________________________________ */
    private JLabel imageLabel;
    private JLabel titleLabel;
    private JLabel descriptionLabel;

    private String title;
    private String description;
    private ImageIcon image;

    /* CONSTRUCTORS _________________________________________________________ */
    public ThemeButton(String title, String description, String imagePath) {
        this.image = Utilities.getImageIcon(imagePath, 50, 50);
    }

    /* ______________________________________________________________________ */
    public ThemeButton(String title, String description) {
        this.title = title;
        this.description = description;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        this.setLayout(new GridLayout(1, 3));

        this.imageLabel = new JLabel();
        this.titleLabel = new JLabel(this.title);
        this.descriptionLabel = new JLabel(this.description);

        if (this.image != null) {
            this.imageLabel.setIcon(this.image);
        }

        this.add(imageLabel);
        this.add(titleLabel);
        this.add(descriptionLabel);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {

    }
}
