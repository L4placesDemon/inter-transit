package interfaces.accountsmanagement;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import interfaces.showaccount.ShowAccountDialog;
import interfaces.showaccount.showadminaccount.ShowAdminDialog;
import interfaces.showaccount.showuseraccount.ShowUserDialog;
import tools.Tools;
import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;
import worldclasses.accounts.UserAccount;

public class AccountButton extends JButton {

	/* ATTRIBUTES ___________________________________________________________ */
	private static final long serialVersionUID = 6655939696146523981L;

	private Account account;

	private JLabel imageLabel;
	private JLabel nicknameLabel;

	/* CONSTRUCTORS _________________________________________________________ */
	public AccountButton(Account account) {
		this.account = account;

		this.initComponents();
		this.initEvents();
	}

	/* METHODS ______________________________________________________________ */
	private void initComponents() {
		// Set up Button --------------------------------------------------------
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Set up Components ---------------------------------------------------
		this.imageLabel = new JLabel(Tools.getImageIcon(this.getAccount().getImage(), 60, 60));
		this.nicknameLabel = new JLabel(this.getAccount().getNickname());

		// ---------------------------------------------------------------------
		// ---------------------------------------------------------------------
		this.add(this.imageLabel, BorderLayout.CENTER);
		this.add(this.nicknameLabel, BorderLayout.SOUTH);
	}

	/* ______________________________________________________________________ */
	private void initEvents() {
		// Button Events -------------------------------------------------------
		this.addActionListener(ae -> {
			ShowAccountDialog showAccountDialog;

			if (this.getAccount() instanceof AdminAccount) {
				showAccountDialog = new ShowAdminDialog((AdminAccount) this.getAccount());
				showAccountDialog.getSignoutButton().setVisible(false);
				showAccountDialog.showDialog();
			} else if (this.getAccount() instanceof UserAccount) {
				showAccountDialog = new ShowUserDialog((UserAccount) this.getAccount());
				showAccountDialog.getSignoutButton().setVisible(false);
				showAccountDialog.showDialog();
			}
		});
	}

	/* ______________________________________________________________________ */
	public void updateAccount(Account account) {
		this.setAccount(account);
		this.imageLabel.setIcon(Tools.getImageIcon(this.account.getImage(), 60, 60));
		this.nicknameLabel.setText(this.account.getNickname());
	}

	/* GETTERS ______________________________________________________________ */
	public Account getAccount() {
		return this.account;
	}

	/* SETTERS ______________________________________________________________ */
	public final void setAccount(Account account) {
		this.account = account;
	}
}
