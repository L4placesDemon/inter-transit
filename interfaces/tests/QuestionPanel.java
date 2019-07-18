package interfaces.tests;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import tools.Pair;
import tools.Tools;
import tools.components.Panel;
import worldclasses.accounts.Account;
import worldclasses.test.Question;

public class QuestionPanel extends Panel {

	/* ATTRIBUTES ___________________________________________________________ */
	private static final long serialVersionUID = 7697489716466375731L;

	private Account account;
	private Question question;

	private JLabel statementLabel;
	private ArrayList<JToggleButton> answerButtons;

	private JButton backButton;

	/* CONSTRUCTORS _________________________________________________________ */
	public QuestionPanel(Account account, Question question) {
		this.account = account;
		this.question = question;

		this.initComponents();
		this.initEvents();
	}

	/* METHODS ______________________________________________________________ */
	private void initComponents() {
		JPanel statementPanel;
		JPanel answersPanel;
		
		ButtonGroup buttonGroup = new ButtonGroup(); 

		// Set up Panel --------------------------------------------------------
		this.setLayout(new BorderLayout());

		// Set up Components ---------------------------------------------------
		this.statementLabel = new JLabel(this.getQuestion().getStatement(),
				Tools.getAbsoluteImageIcon(this.getQuestion().getImage(), 70, 70), JLabel.CENTER);
		this.answerButtons = new ArrayList<>();

		statementPanel = new JPanel(new BorderLayout());
		answersPanel = new JPanel(new GridLayout());

		// ---------------------------------------------------------------------

		for (Pair<String, Integer> answer : this.getQuestion().getAnswers()) {
			JToggleButton answerButton = new JToggleButton(answer.getKey());
			this.answerButtons.add(answerButton);
			buttonGroup.add(answerButton);
		}
		// ---------------------------------------------------------------------
		statementPanel.add(this.statementLabel);

		this.add(statementPanel, BorderLayout.NORTH);
		this.add(answersPanel, BorderLayout.CENTER);
	}

	/* ______________________________________________________________________ */
	private void initEvents() {
		// Components Events ---------------------------------------------------
		for (int i = 0; i < 4; i++) {
			Pair<String, Integer> answer = this.getQuestion().getAnswers().get(i);
			this.answerButtons.get(i).addActionListener(ae -> {
				this.selectAnswer(answer);
			});
		}
	}

	public void selectAnswer(Pair<String, Integer> answer) {

	}

	/* GETTERS ______________________________________________________________ */
	@Override
	public JButton getCloseButton() {
		return backButton;
	}

	/* ______________________________________________________________________ */
	public Account getAccount() {
		return this.account;
	}

	/* ______________________________________________________________________ */
	public Question getQuestion() {
		return this.question;
	}

	/* SETTINGS _____________________________________________________________ */
	public void setAccount(Account account) {
		this.account = account;
	}

	/* ______________________________________________________________________ */
	public void setQuestion(Question question) {
		this.question = question;
	}
}
