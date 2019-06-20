package interfaces.workshops;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import worldclasses.themes.Tip;

public class TipPanel extends JPanel {

    /* ______________________________________________________________________ */
    private Tip tip;
    private Boolean state;

    private JButton titleButton;
    private JScrollPane scrollPane;

    /* ______________________________________________________________________ */
    public TipPanel(Tip tip) {
        this.tip = tip;
        this.state = true;

        this.initComponents();
        this.initEvents();
    }

    /* ______________________________________________________________________ */
    private void initComponents() {
        JTextArea contentArea;

        // Set up Button -------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        contentArea = new JTextArea(this.tip.getContent());

        this.titleButton = new JButton(this.tip.getTitle());
        this.scrollPane = new JScrollPane(contentArea);

        // ---------------------------------------------------------------------
        this.scrollPane.setVisible(this.state);
//        this.scrollPane.setViewportView(contentArea);

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
