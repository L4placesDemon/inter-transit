package interfaces.showaccount.showuseraccount;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.components.Panel;

import worldclasses.accounts.UserAccount;

public class ReportPanel extends Panel {

    private UserAccount userAccount;

    private JButton printButton;
    private JButton backButton;

    public ReportPanel(UserAccount userAccount) {
        this.userAccount = userAccount;

        this.initComponents();
        this.initEvents();
    }

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

    private void initEvents() {
        this.printButton.addActionListener(ae -> {

        });
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public JButton getCloseButton() {
        return this.backButton;
    }
}
