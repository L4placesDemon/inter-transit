package interfaces.createtheme;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import tools.Tools;
import tools.components.TextArea;
import tools.components.TextField;

import worldclasses.themes.Tip;

public class TipEditor extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private Tip tip;

    private JLabel tipImageLabel;
    private JButton setTipImageButton;
    private TextField tipTitleField;
    private TextArea tipContentArea;

    /* CONSTRUCTORS _________________________________________________________ */
    public TipEditor(String title) {
        this.tip = new Tip(title, "");

        this.initComponents();
        this.initEvents();
    }

    /* ______________________________________________________________________ */
    public TipEditor() {
        this("");
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel centerPanel;
        JPanel westPanel;

        // ---------------------------------------------------------------------
        this.setLayout(new BorderLayout());

        // ---------------------------------------------------------------------
        this.tipImageLabel = new JLabel();
        this.setTipImageButton = new JButton(" Elegir Imagen ");

        this.tipTitleField = new TextField();
        this.tipContentArea = new TextArea();

        centerPanel = new JPanel(new BorderLayout());
        westPanel = new JPanel();

        // ---------------------------------------------------------------------
        this.tipImageLabel.setMaximumSize(new Dimension(120, 110));
        this.tipImageLabel.setPreferredSize(new Dimension(120, 110));

        this.tipTitleField.setHint("Titulo");
        this.tipTitleField.setText(this.getTip().getTitle());
//        this.tipTitleField.requestFocus();

        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setBorder(new EtchedBorder());

        // ---------------------------------------------------------------------
        centerPanel.add(this.tipTitleField, BorderLayout.NORTH);
        centerPanel.add(this.tipContentArea, BorderLayout.CENTER);

        westPanel.add(this.tipImageLabel);
        westPanel.add(this.setTipImageButton);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(westPanel, BorderLayout.WEST);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        this.setTipImageButton.addActionListener(ae -> {
            this.setImageAction();
        });
    }

    /* ______________________________________________________________________ */
    private void setImageAction() {
        JFileChooser fileChooser = new JFileChooser();
        int result;
        File image;

        fileChooser.setFileFilter(new FileNameExtensionFilter(
                "JPG, PNG & GIF", "jpg", "png", "gif"
        ));
        result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            image = fileChooser.getSelectedFile();
            this.tipImageLabel.setIcon(Tools.getAbsoluteImageIcon(
                    image.getAbsolutePath(),
                    this.tipImageLabel.getWidth(),
                    this.tipImageLabel.getHeight())
            );
        }
    }

    /* ______________________________________________________________________ */
    @Override
    public String toString() {
        return "TipEditor{tip=" + this.getTip() + "}";
    }

    /* GETTERS ______________________________________________________________ */
    public Tip getTip() {
        return this.tip;
    }

    /* ______________________________________________________________________ */
    public TextField getTipTitleField() {
        return this.tipTitleField;
    }

    /* SETTERS_______________________________________________________________ */
    public void setTip(Tip tip) {
        this.tip = tip;
    }
}
