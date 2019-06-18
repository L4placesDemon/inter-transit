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
        int valuei, valuej;

        for (int i = 0; i < this.values.size(); i++) {
            sum += this.values.get(i);
        }

        valuej = 90;
        for (int i = 0; i < this.values.size(); i++) {
            valuei = this.values.get(i);

            g.setColor(this.colors.get(i));

            arc = (valuei * 360) / sum;
            g.fillArc(0, 0, getWidth(), getHeight(), valuej, -arc);
            valuej -= arc + 1;
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
