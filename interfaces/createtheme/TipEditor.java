package interfaces.createtheme;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

public class TipEditor extends ThemeEditor {

    /* ATTRIBUTES ___________________________________________________________ */
    private JButton imageButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public TipEditor(Tip tip) {
        super(tip);
    }

    /* ______________________________________________________________________ */
    public TipEditor() {
        this(new Tip("", ""));
    }

    /* METHODS ______________________________________________________________ */
    @Override
    protected void initComponents() {
        JPanel centerPanel;
        JPanel westPanel;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        super.imageLabel = new JLabel();
        this.imageButton = new JButton(" Elegir Imagen ");

        super.titleField = new TextField();
        super.descriptionArea = new TextArea();

        centerPanel = new JPanel(new BorderLayout());
        westPanel = new JPanel();

        // ---------------------------------------------------------------------
        super.imageLabel.setMaximumSize(new Dimension(120, 110));
        super.imageLabel.setPreferredSize(new Dimension(120, 110));

        super.titleField.setHint("Titulo");
        super.titleField.setText(this.getTip().getTitle());

        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setBorder(new EtchedBorder());

        // ---------------------------------------------------------------------
        centerPanel.add(super.titleField, BorderLayout.NORTH);
        centerPanel.add(super.descriptionArea, BorderLayout.CENTER);

        westPanel.add(super.imageLabel);
        westPanel.add(this.imageButton);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(westPanel, BorderLayout.WEST);
    }

    /* ______________________________________________________________________ */
    @Override
    protected void initEvents() {
        this.imageButton.addActionListener(ae -> {
            this.setImageAction();
        });

        super.titleField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                getTip().setTitle(titleField.getText());
            }
        });

        super.descriptionArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent arg0) {
                getTip().setDescription(descriptionArea.getText());
            }
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
            super.imageLabel.setIcon(Tools.getAbsoluteImageIcon(
                    image.getAbsolutePath(),
                    super.imageLabel.getWidth(),
                    super.imageLabel.getHeight())
            );
            this.getTip().setImage(image.getAbsolutePath());
        }
    }

    /* ______________________________________________________________________ */
    @Override
    public String toString() {
        return "TipEditor{tip=" + this.getTip() + "}";
    }

    /* GETTERS ______________________________________________________________ */
    public Tip getTip() {
        return (Tip) this.getTheme();
    }

    /* ______________________________________________________________________ */
    public TextField getTitleField() {
        return this.titleField;
    }

    /* SETTERS_______________________________________________________________ */
    public void setTip(Tip tip) {
        this.setTheme(tip);
    }
}
