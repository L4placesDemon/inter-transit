package interfaces.tests;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import tools.Tools;
import worldclasses.accounts.Account;
import worldclasses.accounts.UserAccount;

public class UserPanel extends JPanel {

	/* ATTRIBUTES ___________________________________________________________ */
	private static final long serialVersionUID = -6461631134630809408L;

	private Account account;

	private JButton accountButton;
	private JLabel pointsLabel;
	private JLabel levelLabel;

	/* CONSTRUCTORS _________________________________________________________ */
	public UserPanel(Account account) {
		this.account = account;

		this.initComponents();
	}

	/* METHODS ______________________________________________________________ */
	private void initComponents() {
		String imagePath;
		String nickname;

		JPanel eastPanel;
		JPanel pointsPanel;
		JPanel levelPanel;

		// Set up Panel --------------------------------------------------------
		this.setLayout(new BorderLayout());

		// Set up Components ---------------------------------------------------
		if (this.getAccount() != null) {
			imagePath = this.getAccount().getImage();
			nickname = this.getAccount().getNickname();
		} else {
			imagePath = "profile/image-00";
			nickname = "Iniciar Sesion";
		}

		this.accountButton = new JButton(nickname, Tools.getImageIcon(imagePath, 40, 40));
		this.pointsLabel = new JLabel("sin puntos");
		this.levelLabel = new JLabel("sin nivel");

		eastPanel = new JPanel();
		pointsPanel = new JPanel(new GridLayout(2, 1));
		levelPanel = new JPanel(new GridLayout(2, 1));

		// ---------------------------------------------------------------------
		if (this.getAccount() instanceof UserAccount) {
			this.pointsLabel.setText(((UserAccount) this.getAccount()).getPoints() + " Puntos");
			this.levelLabel.setText("Nivel " + ((UserAccount) this.getAccount()).getLevel());
		}

		// ---------------------------------------------------------------------
		pointsPanel.add(new JLabel("Puntos"));
		pointsPanel.add(this.pointsLabel);
		
		levelPanel.add(new JLabel("Nivel"));
		levelPanel.add(this.levelLabel);
		
		eastPanel.add(pointsPanel);
		eastPanel.add(new JSeparator(JSeparator.VERTICAL));
		eastPanel.add(levelPanel);

		this.add(this.accountButton, BorderLayout.CENTER);
		this.add(eastPanel, BorderLayout.EAST);
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
