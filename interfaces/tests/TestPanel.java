package interfaces.tests;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;

import tools.components.Panel;

import worldclasses.accounts.Account;

public class TestPanel extends Panel {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;

    private JLabel statementLabel;
    private ArrayList<JButton> optionButtons;

    private JButton backButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public TestPanel(Account account) {
        this.account = account;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());

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
