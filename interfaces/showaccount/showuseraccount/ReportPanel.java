package interfaces.showaccount.showuseraccount;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.components.Panel;

import worldclasses.accounts.UserAccount;

public class ReportPanel extends Panel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 1055295974594356626L;

    private UserAccount userAccount;

    private JButton printButton;
    private JButton backButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public ReportPanel(UserAccount userAccount) {
        this.userAccount = userAccount;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        DataUserPanel dataUserPanel;
        JPanel buttonsPanel;

        this.setLayout(new BorderLayout());

        this.printButton = new JButton("Imprimir");
        this.backButton = new JButton("Volver");

        dataUserPanel = new DataUserPanel(this.getUserAccount());
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonsPanel.add(this.printButton);
        buttonsPanel.add(this.backButton);

        this.add(dataUserPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        this.printButton.addActionListener(ae -> {

        });
    }

    /* GETTERS ______________________________________________________________ */
    public UserAccount getUserAccount() {
        return userAccount;
    }

    /* ______________________________________________________________________ */
    @Override
    public JButton getCloseButton() {
        return this.backButton;
    }

    /* SETTERS ______________________________________________________________ */
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
