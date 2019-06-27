package interfaces.accountstatistics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

public class StatisticsGraph extends JComponent {

    /* ATTRIBUTTES __________________________________________________________ */
    private ArrayList<Integer> values;
    private Color color;

    /* CONSTRUCTORS _________________________________________________________ */
    public StatisticsGraph(ArrayList<Integer> values, Color color) {
        this.values = values;
        this.color = color;
    }

    /* ______________________________________________________________________ */
    public StatisticsGraph(Color color) {
        this(new ArrayList<>(), color);
    }

    /* ______________________________________________________________________ */
    public StatisticsGraph() {
        this(Color.white);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        int size = this.getValues().size();
        int higherPopulation = 0;
        int currentValue = 0;
        int x, y, h;
        int w = 18;

        g.setColor(this.getColor());
        
        // draw x and y axis ---------------------------------------------------
        g.drawLine(20, 20, 20, height - 20);
        g.drawLine(20, height - 20, width - 20, height - 20);

        // Find the higher value -----------------------------------------------
        for (Integer value : this.getValues()) {
            currentValue = value;
            if (currentValue > higherPopulation) {
                higherPopulation = currentValue;
            }
        }
        // Calculate the optimal bar width -------------------------------------
        while ((w + 2) * size >= width - 40) {
            w--;
        }

        // Calculate the initial x coordinate ----------------------------------
        x = (width / 2) - ((w + 2) * size / 2);

        // Draw the value bars -------------------------------------
        for (int i = 0; i < size; i++) {

            currentValue = this.getValues().get(i);
            // Calculate the y coordinate and height for a city bar ------------
            h = ((currentValue * (height - 40)) / higherPopulation);
            y = height - h - 20;

            g.fillRect(x, y, w, h);

            // Draw bar index --------------------------------------------------
            if (i < 9) {
                g.drawString((i + 1) + "", x + 5, height - 7);
            } else {
                g.drawString((i + 1) + "", x, height - 7);
            }

            // Increment x coordinate ------------------------------------------
            x += w + 2;
        }
    }

    /* ______________________________________________________________________ */
    public void addValue(Integer value) {
        this.getValues().add(value);
        repaint();
    }

    /* ______________________________________________________________________ */
    public void removeValue(Integer value) {
        this.getValues().remove(value);
        repaint();
    }

    /* GETTERS ______________________________________________________________ */
    public ArrayList<Integer> getValues() {
        return this.values;
    }
    /* ______________________________________________________________________ */
    public Color getColor() {
        return this.color;
    }
    /* SETTERS ______________________________________________________________ */
    public void setValues(ArrayList<Integer> values) {
        this.values = values;
        repaint();
    }
    
    /* ______________________________________________________________________ */
    public void setColor(Color color) {
        this.color = color;
    }
}
