package interfaces.themestatistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PieInformation extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 5233709319697674166L;

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
        this.setBorder(new EmptyBorder(0, 0, 0, 5));
        this.setLayout(new BorderLayout(5, 0));

        // Set up Components ---------------------------------------------------
        this.westPanel = new JPanel();
        this.centerPanel = new JPanel();

        // ---------------------------------------------------------------------
        this.westPanel.setLayout(new BoxLayout(this.westPanel, BoxLayout.Y_AXIS));
        this.centerPanel.setLayout(new BoxLayout(this.centerPanel, BoxLayout.Y_AXIS));

        if (this.getNames().size() == this.getColors().size()) {
            for (int i = 0; i < this.getNames().size(); i++) {
                this.add(this.getNames().get(i), this.getColors().get(i));
            }
        }

        // ---------------------------------------------------------------------
        this.add(this.westPanel, BorderLayout.WEST);
        this.add(this.centerPanel, BorderLayout.CENTER);
    }

    /* ______________________________________________________________________ */
//    @Override
//    public void paint(Graphics g) {
//        System.out.println("init");
////        this.westPanel.removeAll();
////        this.centerPanel.removeAll();
//        if (this.getNames().size() == this.getColors().size()) {
//            for (int i = 0; i < this.getNames().size(); i++) {
//                this.add(this.getNames().get(i), this.getColors().get(i));
//            }
//        }
//        this.updateUI();
//    }

    /* ______________________________________________________________________ */
    public void add(String name, Color color) {
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

//        this.updateUI();
    }

    /* ______________________________________________________________________ */
//    public void reload() {
//        this.repaint();
//        this.updateUI();
//    }

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
