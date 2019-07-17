package interfaces.tests;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.Tools;
import worldclasses.accounts.Account;
import worldclasses.accounts.UserAccount;

public class UserPanel extends JPanel {

	/* ATTRIBUTES ___________________________________________________________ */
	private static final long serialVersionUID = -6461631134630809408L;

	private Account account;

	private JLabel accountLabel;
	private JLabel pointsLabel;
	private JLabel levelLabel;

	/* CONSTRUCTORS _________________________________________________________ */
	public UserPanel(Account account) {
		this.account = account;

		this.initComponents();
		this.initEvents();
	}

	/* METHODS ______________________________________________________________ */
	private void initComponents() {
		JPanel eastPanel;

		// Set up Panel --------------------------------------------------------
		this.setLayout(new BorderLayout());

		// Set up Components ---------------------------------------------------
		this.accountLabel = new JLabel(this.getAccount().getUsername(),
				Tools.getImageIcon(this.getAccount().getImage(), 30, 30), JLabel.RIGHT);
		this.pointsLabel = new JLabel("sin puntos");
		this.levelLabel = new JLabel("sin nivel");

		eastPanel = new JPanel(new GridLayout());

		// ---------------------------------------------------------------------
		if (this.getAccount() instanceof UserAccount) {
			this.pointsLabel.setText(((UserAccount) this.getAccount()).getPoints() + " Puntos");
			this.levelLabel.setText("Nivel " + ((UserAccount) this.getAccount()).getLevel());
		}

		// ---------------------------------------------------------------------
		eastPanel.add(this.pointsLabel);
		eastPanel.add(this.levelLabel);

		this.add(this.accountLabel, BorderLayout.CENTER);
		this.add(eastPanel, BorderLayout.EAST);
	}

	/* ______________________________________________________________________ */
	private void initEvents() {
		// ---------------------------------------------------------------------
	}

	/* GETTERS ______________________________________________________________ */
	public Account getAccount() {
		return this.account;
	}

	/* SETTERS ______________________________________________________________ */
	public void setAccount(Account account) {
		this.account = account;
	}
}
