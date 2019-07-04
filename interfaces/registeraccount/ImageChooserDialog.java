package interfaces.registeraccount;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import tools.Tools;
import tools.components.Border;
import tools.components.Dialog;

public class ImageChooserDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 7236526628398043759L;

    private String selectedIconPath;

    private JToggleButton[] buttons;
    private JButton cancelButton;
    private JButton chooseButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public ImageChooserDialog(String selectedIconPath) {

        this.selectedIconPath = selectedIconPath;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel imagesPanel;
        JPanel buttonsPanel;
        JScrollPane scrollPane;
        ButtonGroup buttonGroup;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(500, 425);
        this.setLocationRelativeTo(null);
        this.setTitle("Elegir Imagen");
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        this.buttons = new JToggleButton[50];
        this.cancelButton = new JButton("Cancelar");
        this.chooseButton = new JButton("Elegir");

        imagesPanel = new JPanel(new GridLayout(10, 5, 3, 3));
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        scrollPane = new JScrollPane(imagesPanel);
        buttonGroup = new ButtonGroup();

        // ---------------------------------------------------------------------
        for (int i = 0; i < 50; i++) {
            String index = (i + 1) + "";
            if (i < 9) {
                index = "0" + (i + 1);
            }
            String image = "profile/image-" + index;
            this.buttons[i] = this.setButton(image);
        }
        scrollPane.setBorder(new Border(new EmptyBorder(5, 5, 5, 5),
                new Border("Elige una imagen"), new EmptyBorder(5, 5, 5, 5)));

        scrollPane.getVerticalScrollBar().setUnitIncrement(7);

        // ---------------------------------------------------------------------
        for (int i = 0; i < 50; i++) {
            imagesPanel.add(this.buttons[i]);
            buttonGroup.add(this.buttons[i]);
        }
        buttonsPanel.add(this.cancelButton);
        buttonsPanel.add(this.chooseButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private JToggleButton setButton(String imagePath) {
        JToggleButton button = new JToggleButton();

        button.setName(imagePath);
        button.setBorder(null);
        button.setFocusable(false);

        button.setIcon(Tools.getImageIcon(imagePath, 70, 70));

        return button;
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        for (int i = 0; i < 50; i++) {
            this.buttons[i].addActionListener(ae -> {
                JToggleButton source = (JToggleButton) ae.getSource();
                this.setSelectedIconPath(source.getName());
            });
        }

        int length = this.getSelectedIconPath().length();
        char c1 = this.getSelectedIconPath().charAt(length - 1);
        char c2 = this.getSelectedIconPath().charAt(length - 2);
        int pos = Integer.parseInt(c2 + "" + c1) - 1;
        this.buttons[pos].doClick();

        this.cancelButton.addActionListener(ae -> {
            this.dispose();
        });

        this.chooseButton.addActionListener(ae -> {
            this.dispose();
            this.okAction();
        });
    }

    /* GETTERS ______________________________________________________________ */
    public String getSelectedIconPath() {
        return this.selectedIconPath;
    }

    /* SETTERS ______________________________________________________________ */
    public void setSelectedIconPath(String selectedIconPath) {
        this.selectedIconPath = selectedIconPath;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new ImageChooserDialog("profile/image-50").showTestDialog();
    }
}
