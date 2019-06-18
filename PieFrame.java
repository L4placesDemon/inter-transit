
import interfaces.accountstatistics.Pie;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PieFrame extends JFrame {

    private Pie pie;
    private JTextField textField;

    public PieFrame() {
        initComponents();
        initEvents();
    }

    private void initComponents() {
        // Set Up Frame
        setLayout(new BorderLayout());
        setSize(400, 430);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(200, 215));
        setTitle("Pie");

        // Set Up Components
        JPanel panel = new JPanel(new GridLayout());
        JLabel label = new JLabel("New Value");

        pie = new Pie();
        textField = new JTextField();

        panel.add(label);
        panel.add(textField);

        add(pie, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    private void initEvents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (ke.getKeyChar() == '\n') {
                    String stringValue = textField.getText();
                    int value = Integer.parseInt(stringValue);
                    pie.addValue(value);
                    
                    textField.setText("");
                }
            }
        });
    }
    
    public static void main(String[] args) {
        new PieFrame().setVisible(true);
    }
}
