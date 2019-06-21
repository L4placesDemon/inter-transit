package interfaces.themestatistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PieInformation extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private ArrayList<String> names;
    private ArrayList<Color> colors;

    private JPanel westPanel;
    private JPanel centerPanel;

    /* CONSTRUCTORS _________________________________________________________ */
    public PieInformation(ArrayList<String> names, ArrayList<Color> colors) {
        this.names = names;
        this.colors = colors;

        this.initComponents();
    }

    /* ______________________________________________________________________ */
    public PieInformation() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        // Set up Button -------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        westPanel = new JPanel();
        centerPanel = new JPanel();

        // ---------------------------------------------------------------------
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        if (names.size() == colors.size()) {
            for (int i = 0; i < names.size(); i++) {
                this.addSomething(this.names.get(i), this.colors.get(i));
            }
        }

        // ---------------------------------------------------------------------
        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    /* ______________________________________________________________________ */
    public void addSomething(String name, Color color) {
        JLabel nameLabel;
        JLabel colorLabel;

        nameLabel = new JLabel(name);

        colorLabel = new JLabel();
        colorLabel.setMaximumSize(new Dimension(17, 17));
        colorLabel.setPreferredSize(new Dimension(17, 17));
        colorLabel.setOpaque(true);
        colorLabel.setBackground(color);

        this.westPanel.add(colorLabel);
        this.centerPanel.add(nameLabel);

        this.updateUI();
    }

    /* GETTERS ______________________________________________________________ */
    public ArrayList<String> getNames() {
        return names;
    }

    /* ______________________________________________________________________ */
    public ArrayList<Color> getColors() {
        return colors;
    }

    /* SETTERS ______________________________________________________________ */
    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    /* ______________________________________________________________________ */
    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
    }
}
