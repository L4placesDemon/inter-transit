package interfaces.registeraccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utilities.Border;
import utilities.Utilities;

public class ImagePanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    protected JLabel imageLabel;
    protected JButton chooseImageButton;
    protected ImageChooserDialog imageChooserDialog;
    protected String image = "/images/profile/image-01.png";

    /* CONSTRUCTORS _________________________________________________________ */
    public ImagePanel() {
        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel buttonsPanel;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setBorder(new Border(new EmptyBorder(5, 5, 5, 5), new Border("Imagen")));

        // Set up Components ---------------------------------------------------
        this.imageLabel = new JLabel();
        this.chooseImageButton = new JButton("Elegir");

        buttonsPanel = new JPanel();

        // ---------------------------------------------------------------------
        this.imageLabel.setSize(115, 115);
        this.imageLabel.setHorizontalAlignment(JLabel.CENTER);
        this.imageLabel.setIcon(
                Utilities.getImageIcon(this.image, 115, 115));
        this.imageLabel.setBorder(new EmptyBorder(0, 70, 0, 70));

        // ---------------------------------------------------------------------
        buttonsPanel.add(this.chooseImageButton);

        this.add(this.imageLabel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        this.chooseImageButton.addActionListener(ae -> {
            this.imageChooserDialog = new ImageChooserDialog(this.image);
            int option = this.imageChooserDialog.showDialog();

            if (option == ImageChooserDialog.OK_OPTION) {
                this.image = this.imageChooserDialog.getSelectedIconPath();
                this.imageLabel.setIcon(Utilities.getImageIcon(this.image, 115, 115));
            }
        });
    }

    /* GETTERS ______________________________________________________________ */
    public String getImage() {
        return this.image;
    }

    /* SETTERS ______________________________________________________________ */
    public void setImage(String image) {
        this.image = image;
        this.imageLabel.setIcon(
                Utilities.getImageIcon(image, 115, 115));
    }
}
