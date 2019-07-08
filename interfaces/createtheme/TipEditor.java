package interfaces.createtheme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import tools.components.TextArea;
import tools.components.TextField;
import worldclasses.themes.Tip;

public class TipEditor extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private Tip tip;
    private String title;

    private JLabel tipImageLabel;
    private JButton setTipImageButton;
    private TextField tipTitleField;
    private TextArea tipContentArea;

    /* CONSTRUCTORS _________________________________________________________ */
    public TipEditor() {
        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel centerPanel;
        JPanel westPanel;

        // ---------------------------------------------------------------------
        this.setLayout(new BorderLayout());

        // ---------------------------------------------------------------------
        this.tipImageLabel = new JLabel();
        this.setTipImageButton = new JButton("Imagen");

        this.tipTitleField = new TextField();
        this.tipContentArea = new TextArea();

        centerPanel = new JPanel(new BorderLayout());
        westPanel = new JPanel();

        // ---------------------------------------------------------------------
        this.tipImageLabel.setMaximumSize(new Dimension(120, 110));
        this.tipImageLabel.setPreferredSize(new Dimension(120, 110));
        this.tipImageLabel.setBorder(new EtchedBorder());

        this.tipImageLabel.setOpaque(true);
        this.tipTitleField.setHint("Titulo");

        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

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

    }

    /* ______________________________________________________________________ */
    @Override
    public String toString() {
        return "TipEditor{title=" + this.getTitle() + "}";
    }

    /* GETTERS ______________________________________________________________ */
    public Tip getTip() {
        return this.tip;
    }

    /* ______________________________________________________________________ */
    public String getTitle() {
        if (this.getTip() != null) {
            return this.getTip().getTitle();
        } else {
            return this.title;
        }
    }
    
    /* SETTERS_______________________________________________________________ */
    public void setTip(Tip tip) {
        this.tip = tip;
    }
}
