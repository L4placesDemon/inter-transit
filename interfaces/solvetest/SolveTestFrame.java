package interfaces.solvetest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import tools.Border;

import worldclasses.tests.Question;

public class SolveTestFrame extends JFrame {

    /* ATTRIBUTES ___________________________________________________________ */
    private ArrayList<Question> questions;

    private JButton answer1Button;
    private JButton answer2Button;
    private JButton answer3Button;
    private JButton answer4Button;
    private JLabel questionLabel;

    /* CONSTRUCTORS _________________________________________________________ */
    public SolveTestFrame(ArrayList<Question> question) {
        this.questions = question;

        this.initComponents();
        this.initEvents();
    }

    /* ______________________________________________________________________ */
    public SolveTestFrame() {
        this(new ArrayList<Question>(Arrays.asList(
                new Question("Statement 1", "A1", "A2", "A3", "A3")
        )));
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel questionPanel;
        JPanel anwersPanel;

        // Set up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(450, 450));
        this.setLocationRelativeTo(null);
        this.setTitle("Solve Test Desing");

        // Set up Components ---------------------------------------------------
        questionLabel = new JLabel(getQuestions().get(0).getStatement());
        answer1Button = new JButton(getQuestions().get(0).getAnswers().get(0));
        answer2Button = new JButton(getQuestions().get(0).getAnswers().get(1));
        answer3Button = new JButton(getQuestions().get(0).getAnswers().get(2));
        answer4Button = new JButton(getQuestions().get(0).getAnswers().get(3));

        questionPanel = new JPanel(new BorderLayout());
        anwersPanel = new JPanel(new GridLayout(2, 2, 20, 20));

        // ---------------------------------------------------------------------
        questionLabel.setBorder(new Border(new EmptyBorder(20, 20, 20, 20), new EtchedBorder(), new EmptyBorder(20, 20, 20, 20)));

        anwersPanel.setBorder(new Border(new EmptyBorder(20, 20, 20, 20), new EtchedBorder(), new EmptyBorder(20, 20, 20, 20)));

        // ---------------------------------------------------------------------
        questionPanel.add(questionLabel, BorderLayout.CENTER);

        anwersPanel.add(answer1Button);
        anwersPanel.add(answer2Button);
        anwersPanel.add(answer3Button);
        anwersPanel.add(answer4Button);

        this.add(questionPanel, BorderLayout.PAGE_START);
        this.add(anwersPanel, BorderLayout.CENTER);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        ActionListener actionListener;

        // Frame Events --------------------------------------------------------
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Components Events ---------------------------------------------------
        actionListener = ae -> {
            JButton source = (JButton) ae.getSource();
            this.setOption(source.getText());
        };
    }

    public void setOption(String option) {

    }

    /* GETTERS ______________________________________________________________ */
    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    /* SETTERS ______________________________________________________________ */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /* ______________________________________________________________________ */
    public static void main(String[] args) {
        new SolveTestFrame().setVisible(true);
    }
}
