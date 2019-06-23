package interfaces.workshops;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tools.components.Dialog;

import worldclasses.accounts.Account;
import worldclasses.accounts.UserAccount;
import worldclasses.themes.Theme;
import worldclasses.themes.Tip;

public class ThemeDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private Theme theme;
    private Account account;

    private JButton backButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public ThemeDialog(Theme theme, Account account) {
        super();
        this.theme = theme;
        this.account = account;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        UserPanel userPanel;
        JPanel centerPanel;
        JPanel southPanel;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(300, 200));
        this.setTitle("Temas");

        // Set up Components ---------------------------------------------------
        this.backButton = new JButton("Volver");

        userPanel = new UserPanel(this.getAccount());
        centerPanel = new JPanel();
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------------------------
        for (Tip tip : this.getTheme().getTips()) {
            TipPanel tipPanel = new TipPanel(tip);

            tipPanel.getTitleButton().addActionListener(ae -> {
                centerPanel.updateUI();
            });
            centerPanel.add(tipPanel);
        }
        southPanel.add(this.backButton);

        this.add(userPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(centerPanel), BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components events ---------------------------------------------------
        this.backButton.addActionListener(ae -> {
            this.dispose();
        });
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* ______________________________________________________________________ */
    public Theme getTheme() {
        return this.theme;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }

    /* ______________________________________________________________________ */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        ArrayList<Tip> tips = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            tips.add(new Tip(
                    "Tip " + i,
                    "Content of tip " + i
            ));
        }

        Theme theme = new Theme(
                null,
                "Test Theme",
                "Test theme description",
                tips,
                14,
                14000.0,
                4
        );
        UserAccount account = new UserAccount(
                "Alejandro",
                "413j0.c",
                "passwd",
                "/images/profile/image-31.png",
                4,
                40
        );

        new ThemeDialog(theme, account).showTestDialog();
    }
}
