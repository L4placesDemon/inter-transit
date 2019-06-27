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
        this.state = false;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JTextArea contentArea;

        // Set up Button -------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.titleButton = new JButton(this.getTip().getTitle());
        this.scrollPane = new JScrollPane();

        contentArea = new JTextArea(this.getTip().getContent());

        // ---------------------------------------------------------------------
        this.scrollPane.setVisible(this.getState());
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

    /* GETTERS ______________________________________________________________ */
    public Tip getTip() {
        return this.tip;
    }

    /* ______________________________________________________________________ */
    public Boolean getState() {
        return this.state;
    }

    /* SETTERS ______________________________________________________________ */
    public void setTip(Tip tip) {
        this.tip = tip;
    }

    /* ______________________________________________________________________ */
    public void setState(Boolean state) {
        this.state = state;
    }
}
