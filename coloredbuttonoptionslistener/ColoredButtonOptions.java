package coloredbuttonoptionslistener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ColoredButtonOptions extends JFrame {

    private Button[] coloredButtons;

    public ColoredButtonOptions() {
        setUpComponents();
        setUpEvents();
        setVisible(true);
    }

    private void setUpComponents() {

        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(200, 150));
        setTitle("Colored Button Options");

        // Set Up Components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        coloredButtons = new Button[4];
        for (int i = 0; i < 4; i++) {
            coloredButtons[i] = new Button("Option " + (i + 1), Color.RED);
            panel.add(coloredButtons[i]);
        }

        panel.setPreferredSize(new Dimension((int) (getWidth() * 0.4), getHeight()));

        add(panel, BorderLayout.WEST);
        add(new JPanel(), BorderLayout.CENTER);
    }

    private void setUpEvents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Listener coloredButtonListener = new Listener() {
            @Override
            public void click(MouseEvent m) {
                Button coloredButton = (Button) m.getSource();
                String text = coloredButton.darTexto();

                JOptionPane.showMessageDialog(null, text + (coloredButton.darState() ? " was Actived" : " was Deactivated"));
            }

            @Override
            public void enter(MouseEvent m) {
                Button coloredButton = (Button) m.getSource();
                coloredButton.setForeground(Color.blue);
            }

            @Override
            public void exit(MouseEvent m) {
                Button coloredButton = (Button) m.getSource();
                coloredButton.setForeground(Color.black);
            }
        };

        for (int i = 0; i < 4; i++) {
            coloredButtons[i].setListener(coloredButtonListener);
        }
    }

    public static void main(String[] args) {
        new ColoredButtonOptions();
    }
}
