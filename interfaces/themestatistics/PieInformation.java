package interfaces.themestatistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PieInformation extends JPanel {

    private JPanel westPanel;
    private JPanel centerPanel;

    private ArrayList<String> names;
    private ArrayList<Color> colors;

    public PieInformation() {
        this.names = names;
        this.colors = colors;

        this.initComponents();
    }

    public PieInformation(ArrayList<String> names, ArrayList<Color> colors) {
        this.names = new ArrayList<>();
        this.colors = new ArrayList<>();

        this.initComponents();
    }

    private void initComponents() {

        this.setLayout(new BorderLayout());

        westPanel = new JPanel();
        centerPanel = new JPanel();

        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        if (names.size() == colors.size()) {
            for (int i = 0; i < names.size(); i++) {
                this.addSomething(this.names.get(i), this.colors.get(i));
            }
        }
        
        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    public void addSomething(String name, Color color) {

        JLabel colorLabel;
        JLabel nameLabel;

        colorLabel = new JLabel("     ");
        colorLabel.setOpaque(true);
        colorLabel.setBackground(color);

        nameLabel = new JLabel(name);

        this.westPanel.add(colorLabel);
        this.centerPanel.add(nameLabel);

        this.updateUI();
    }
}
