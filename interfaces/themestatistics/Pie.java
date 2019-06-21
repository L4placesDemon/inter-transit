package interfaces.themestatistics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
            this.getColors().add(this.generateColor());
        });
    }

    /* ______________________________________________________________________ */
    public Pie() {
        this(new ArrayList<>());
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int sum = 0;
        double arc;
        int value, pos;

        for (Integer i : this.getValues()) {
            sum += i;
        }

        pos = 90;
        for (int i = 0; i < this.getValues().size(); i++) {
            value = this.getValues().get(i);

            arc = -((double) value * 360) / (double) sum;
            BigDecimal bd = new BigDecimal(arc).setScale(0, RoundingMode.HALF_UP);

            g2d.setColor(this.getColors().get(i));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fillArc(0, 0, getWidth(), getHeight(), pos, bd.intValue());

            pos += bd.intValue();
        }
    }

    /* _____________________________._________________________________________ */
    public Color addValue(Integer value) {
        Color color = generateColor();

        if (value > 0) {
            this.getValues().add(value);
            this.getColors().add(color);
            this.repaint();
        }
        return color;
    }

    /* _____________________________._________________________________________ */
    public Color generateColor() {
        return new Color(
                new Random().nextInt(256),
                new Random().nextInt(256),
                new Random().nextInt(256)
        );
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
