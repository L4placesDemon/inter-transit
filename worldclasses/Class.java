package worldclasses;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Class extends JFrame {

    /* CONSTRUCTORS _________________________________________________________ */
    public Class() {
        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        // Set Up Frame --------------------------------------------------------
        this.setLayout(null);
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(getWidth() / 2, getHeight() / 2));
        this.setTitle("Class");

        // Set Up Components ---------------------------------------------------
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Frame Events --------------------------------------------------------
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Components Events ---------------------------------------------------
    }
}
