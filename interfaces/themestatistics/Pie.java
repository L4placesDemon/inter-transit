package interfaces.themestatistics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;

public class Pie extends JComponent {

    /* ATTRIBUTES ___________________________________________________________ */
    private ArrayList<Integer> values;
    private ArrayList<Color> colors;

    /* CONSTRUCTORS _________________________________________________________ */
    public Pie(ArrayList<Integer> values) {
        this.values = values;
        this.colors = new ArrayList<>();

        values.forEach(i -> {
            this.getColors().add(new Color(
                    new Random().nextInt(256),
                    new Random().nextInt(256),
                    new Random().nextInt(256)
            ));
        });
    }

    /* ______________________________________________________________________ */
    public Pie() {
        this(new ArrayList<>());
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public void paint(Graphics g) {
        int sum = 0, arc;
        int value, pos;

        for (int i = 0; i < this.getValues().size(); i++) {
            sum += this.getValues().get(i);
        }

        pos = 90;
        for (int i = 0; i < this.getValues().size(); i++) {
            value = this.getValues().get(i);

            g.setColor(this.getColors().get(i));

            arc = (value * 360) / sum;
            g.fillArc(0, 0, getWidth(), getHeight(), pos, -arc);
            pos -= arc + 1;
        }
    }

    /* ______________________________________________________________________ */
    public Color addValue(Integer value) {
        Color color = new Color(
                new Random().nextInt(256),
                new Random().nextInt(256),
                new Random().nextInt(256)
        );

        if (value > 0) {
            this.getValues().add(value);
            this.getColors().add(color);
            this.repaint();
        }

        return color;
    }

    /* GETTERS  ______________________________________________________________ */
    public ArrayList<Integer> getValues() {
        return values;
    }

    /* ______________________________________________________________________ */
    public void setValues(ArrayList<Integer> values) {
        this.values = values;
    }

    /* SETTERS  ______________________________________________________________ */
    public ArrayList<Color> getColors() {
        return colors;
    }

    /* ______________________________________________________________________ */
    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
    }
}
