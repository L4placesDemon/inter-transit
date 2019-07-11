package interfaces.signin;

import interfaces.registeraccount.RegisterAccountDialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.Tools;
import tools.filemanager.BinaryFileManager;
import tools.components.Dialog;
import worldclasses.Settings;

import worldclasses.accounts.Account;

public class SigninDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -7318033861023063175L;

    private Account account;

    private JLabel imageLabel;
    private UserPanel userPanel;

    private JButton registerButton;
    private JButton cancelButton;
    private JButton signinButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public SigninDialog() {

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        String logo = null;
        String theme;

        JPanel mainPanel;
        JPanel buttonsPanel;

        theme = Settings.getCurrentSettings().getTheme();
        if (theme.equals(Settings.LIGHT_THEME)) {
            logo = Settings.LIGHT_LOGO;
        } else if (theme.equals(Settings.DARK_THEME)) {
            logo = Settings.DARK_LOGO;
        }

        // Set up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(380, getHeight() / 2));
        this.setTitle("Iniciar Sesion");
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        this.imageLabel = new JLabel(Tools.getImageIcon(logo, 130, 130));
        this.userPanel = new UserPanel();

        this.registerButton = new JButton("Registrar");
        this.cancelButton = new JButton("Cancelar");
        this.signinButton = new JButton("Iniciar Sesion");

        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        buttonsPanel.add(this.registerButton);
        buttonsPanel.add(this.cancelButton);
        buttonsPanel.add(this.signinButton);

        mainPanel.add(this.imageLabel, BorderLayout.CENTER);
        mainPanel.add(this.userPanel, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.registerButton.addActionListener(ae -> {
            this.registerAction();
        });

        this.cancelButton.addActionListener(ae -> {
            this.dispose();
        });

        this.signinButton.addActionListener(ae -> {
            this.signinAction();
        });

        this.userPanel.getPasswordField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    signinButton.doClick();
                }
            }
        });
    }

    /* ______________________________________________________________________ */
    public void registerAction() {
        setVisible(false);
        new RegisterAccountDialog().showDialog();
        this.userPanel.clear();
        setVisible(true);
    }

    /* ______________________________________________________________________ */
    public void signinAction() {
        String nickname = this.userPanel.getNickname();

        if (this.verifyAccount()) {
            Account _account = this.initAccount(nickname);
            this.singin(_account);
            this.dispose();
            this.okAction();
        }
    }

    /* ______________________________________________________________________ */
    public boolean verifyAccount() {
        String nickname = this.userPanel.getNickname();
        String password = this.userPanel.getPassword();

        String userOutput = this.verifyUser(nickname);
        String passwordOutput = this.verifyPassword(nickname, password);

        this.userPanel.setMessage(userOutput);
        if (userOutput.equals(" ")) {
            this.userPanel.setMessage(passwordOutput);
        }

        return userOutput.equals(" ") && passwordOutput.equals(" ");
    }

    /* ______________________________________________________________________ */
    public String verifyUser(String nickname) {
        BinaryFileManager binaryFileManager;
        ArrayList<Object> objects;

        if (nickname.isEmpty()) {
            return "El campo del apodo no debe estar vacio";
        } else if (nickname.length() < 6) {
            return "El apodo debe tener almenos 6 caracteres";
        } else {
            for (int i = 0; i < nickname.length(); i++) {
                char c = nickname.charAt(i);
                if (!Character.isAlphabetic(c) && !Character.isDigit(c)
                        && !Character.isWhitespace(c)) {

                    return "Caracteres validos para apodo: (a-z), (0-9), ' '";
                }
            }

            binaryFileManager = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE);
            objects = binaryFileManager.read();

            boolean exists = false;
            for (Object object : objects) {
                Account _account = (Account) object;
                if (_account.getNickname().equals(nickname)) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                return " ";
            } else {
                return "El usuario no existe";
            }
        }
    }

    /* ______________________________________________________________________ */
    public String verifyPassword(String nickname, String password) {
        BinaryFileManager binaryFileManager;
        ArrayList<Object> objects;
        boolean match;

        if (password.isEmpty()) {
            return "El campo de la contraseña no debe estar vacio";
        } else if (password.length() < 6) {
            return "La contraseña debe tener almenos 6 caracteres";
        } else if (password.isEmpty()) {
            return "La campo de la contraseña no debe estar vacio";
        } else {
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (!Character.isAlphabetic(c) && !Character.isDigit(c)
                        && !Character.isWhitespace(c)) {
                    return "Caracteres validos para contraseña: (a-z), (0-9), ' '";
                }
            }

            binaryFileManager = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE);
            objects = binaryFileManager.read();

            match = false;

            for (Object object : objects) {
                Account _account = (Account) object;
                if (_account.getNickname().equals(nickname)
                        && _account.getPassword().equals(password)) {
                    match = true;
                    break;
                }
            }

            if (!match) {
                return "La contraseña es incorrecta";
            }
            return " ";
        }
    }

    /* ______________________________________________________________________ */
    public Account initAccount(String nickname) {
        BinaryFileManager binaryFileManager = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE);
        ArrayList<Object> objects = binaryFileManager.read();

        for (Object object : objects) {
            Account _account = (Account) object;
            if (_account.getNickname().equals(nickname)) {
                return _account;
            }
        }

        return null;
    }

    /* ______________________________________________________________________ */
    public void singin(Account account) {
        this.account = account;
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* GETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new SigninDialog().showTestDialog();
    }
}
