package interfaces.showaccount.showadminaccount;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaces.showaccount.AccountPanel;

import tools.components.Border;

import worldclasses.accounts.AdminAccount;

public class AdminPanel extends AccountPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 6022729394255065302L;

    /* CONSTRUCTORS _________________________________________________________ */
    public AdminPanel(AdminAccount adminAccount) {
        super(adminAccount);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    protected void initComponents() {
        JPanel leftPanel;
        JPanel rightPanel;

        // Set up Panel --------------------------------------------------------
        super.initComponents();
        this.setBorder(new Border(new EmptyBorder(0, 20, 0, 20), new Border("Administrador")));

        // Set up Components ---------------------------------------------------
        leftPanel = new JPanel(new GridLayout(2, 1, 10, 7));
        rightPanel = new JPanel(new GridLayout(2, 1, 10, 5));

        // ---------------------------------------------------------------------
        leftPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        rightPanel.setBorder(new EmptyBorder(10, 0, 20, 20));

        // ---------------------------------------------------------------------
        leftPanel.add(new JLabel("Nombre:", JLabel.RIGHT));
        leftPanel.add(new JLabel("Alias:", JLabel.RIGHT));

        rightPanel.add(super.usernameField);
        rightPanel.add(super.nicknameField);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);
    }
}
