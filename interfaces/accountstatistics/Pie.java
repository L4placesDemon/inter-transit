package interfaces.accountstatistics;


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

        Random random = new Random();

        this.values = values;
        this.colors = new ArrayList<>();

        values.forEach(i -> {
            this.colors.add(new Color(
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
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

        for (int i = 0; i < this.values.size(); i++) {
            sum += this.values.get(i);
        }

        pos = 90;
        for (int i = 0; i < this.values.size(); i++) {
            value = this.values.get(i);

            g.setColor(this.colors.get(i));

            arc = (value * 360) / sum;
            g.fillArc(0, 0, getWidth(), getHeight(), pos, -arc);
            pos -= arc + 1;
        }
    }

    /* ______________________________________________________________________ */
    public void addValue(Integer value) {
        Random random = new Random();

        if (value > 0) {
            this.values.add(value);
            this.colors.add(new Color(
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
            ));
            this.repaint();
        }
    }
}
