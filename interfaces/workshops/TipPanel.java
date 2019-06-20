package interfaces.workshops;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import worldclasses.themes.Tip;

public class TipPanel extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private Tip tip;
    private Boolean state;

    private JButton titleButton;
    private JScrollPane scrollPane;

    /* CONSTRUCTORS _________________________________________________________ */
    public TipPanel(Tip tip) {
        this.tip = tip;
        this.state = true;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JTextArea contentArea;

        // Set up Button -------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.titleButton = new JButton(this.tip.getTitle());
        this.scrollPane = new JScrollPane();

        contentArea = new JTextArea(this.tip.getContent());

        // ---------------------------------------------------------------------
        this.scrollPane.setVisible(this.state);
        this.scrollPane.setViewportView(contentArea);

        // ---------------------------------------------------------------------
        this.add(this.titleButton, BorderLayout.NORTH);
        this.add(this.scrollPane, BorderLayout.CENTER);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.titleButton.addActionListener(ae -> {
            this.state = !this.state;
            this.scrollPane.setVisible(this.state);
            this.updateUI();
        });
    }

    /* GETTERS ______________________________________________________________ */
    public JButton getTitleButton() {
        return this.titleButton;
    }
}
