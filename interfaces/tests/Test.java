package interfaces.tests;

import javax.swing.JButton;

import tools.components.Panel;

import worldclasses.accounts.Account;

public class Test extends Panel {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;

    private JButton backButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public Test(Account account) {
        this.account = account;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        // Set up Panel --------------------------------------------------------
        // Set up Components ---------------------------------------------------
        // ---------------------------------------------------------------------
        // ---------------------------------------------------------------------
    }

    /* ______________________________________________________________________ */
    private void initEvents() {

    }

    /* GETTERS ______________________________________________________________ */
    @Override
    public JButton getCloseButton() {
        return backButton;
    }
}
