package interfaces.tests;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import tools.components.Panel;
import worldclasses.accounts.Account;
import worldclasses.test.Test;

public class SolveTestPanel extends Panel {

	/* ATTRIBUTES ___________________________________________________________ */
	private static final long serialVersionUID = -7440857656059281125L;

	private Account account;
	private ArrayList<Test> tests;

	private UserPanel userPanel;
	private JButton backButton;

	/* CONSTRUCTORS _________________________________________________________ */
	public SolveTestPanel(Account account, ArrayList<Test> tests) {
		this.account = account;
		this.tests = tests;

		this.initComponents();
		this.initEvents();
	}

	/* ______________________________________________________________________ */
	public SolveTestPanel(Account account) {
		this(account, new ArrayList<>());
	}

	/* METHODS ______________________________________________________________ */
	private void initComponents() {
		JPanel testsPanel;

		// Set up Panel --------------------------------------------------------
		this.setLayout(new BorderLayout());

		// Set up Components ---------------------------------------------------
		this.userPanel = new UserPanel(this.getAccount());
		this.backButton = new JButton("Volver");
		testsPanel = new JPanel();

		// ---------------------------------------------------------------------
		this.add(testsPanel, BorderLayout.CENTER);
	}

	private void initEvents() {
		// ---------------------------------------------------------------------
	}

	/* GETTERS ______________________________________________________________ */
	public Account getAccount() {
		return this.account;
	}

	/* ______________________________________________________________________ */
	public ArrayList<Test> getTests() {
		return this.tests;
	}

	/* ______________________________________________________________________ */
	@Override
	public JButton getCloseButton() {
		return this.backButton;
	}

	/* SETTERS ______________________________________________________________ */
	public void setAccount(Account account) {
		this.account = account;
	}

	/* ______________________________________________________________________ */
	public void setTests(ArrayList<Test> tests) {
		this.tests = tests;
	}
}
