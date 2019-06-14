package interfaces.workshops;

import javax.swing.JFrame;
import utilities.Dialog;
import worldclasses.accounts.Account;
import worldclasses.themes.Theme;

public class ThemeDialog extends Dialog {
    
    /* ATTRIBUTES ___________________________________________________________ */
    private Theme theme;
    private Account account;
    
    /* CONSTRUCTORS _________________________________________________________ */
    public ThemeDialog(Theme theme, Account account) {
        super(new JFrame(), true);
        
        this.theme = theme;
        this.account = account;
        
        this.initComponents();
        this.initEvents();
    }
    
    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        
    }
    
    /* ______________________________________________________________________ */
    private void initEvents() {
        
    }
}
