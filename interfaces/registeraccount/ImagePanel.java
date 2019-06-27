package interfaces.registeraccount;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tools.Tools;
import tools.components.Border;

public class ImagePanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    protected String imagePath = "profile/image-01";
    
    protected JLabel imageLabel;
    protected ImageChooserDialog imageChooserDialog;
    protected JButton chooseImageButton;

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
        this.imageLabel.setIcon(Tools.getImageIcon(this.getImagePath(), 115, 115));
        this.imageLabel.setBorder(new EmptyBorder(0, 70, 0, 70));

        // ---------------------------------------------------------------------
        buttonsPanel.add(this.chooseImageButton);

        this.add(this.imageLabel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        this.chooseImageButton.addActionListener(ae -> {
            this.imageChooserDialog = new ImageChooserDialog(this.getImagePath());
            int option = this.imageChooserDialog.showDialog();

            if (option == ImageChooserDialog.OK_OPTION) {
                this.setImagePath(this.imageChooserDialog.getSelectedIconPath());
                this.imageLabel.setIcon(Tools.getImageIcon(this.getImagePath(), 115, 115));
            }
        });
    }

    /* GETTERS ______________________________________________________________ */
    public String getImagePath() {
        return this.imagePath;
    }

    /* SETTERS ______________________________________________________________ */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        this.imageLabel.setIcon(Tools.getImageIcon(this.getImagePath(), 115, 115));
    }
}
