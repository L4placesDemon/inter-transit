package interfaces.registeraccount;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tools.components.Dialog;
import tools.components.DialogPane;
import tools.binaryfilemanager.BinaryFileManager;
import tools.components.PasswordField;

import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;
import worldclasses.accounts.UserAccount;

public class RegisterAccountDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    protected static final String ADMIN_PASSWORD = "4DM1N";

    protected Account account;

    protected ImagePanel imagePanel;
    private UserPanel userPanel;
    private PasswordPanel passwordPanel;

    protected JButton cancelButton;
    protected JButton finishButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public RegisterAccountDialog() {
        

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel panelsPanel;
        JPanel southPanel;
        JPanel buttonsPanel;

        // Set up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(370, 475);
        this.setLocationRelativeTo(null);
        this.setTitle("Registrar Usuario");
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        this.imagePanel = new ImagePanel();
        this.userPanel = new UserPanel();
        this.passwordPanel = new PasswordPanel();

        this.cancelButton = new JButton("Cancelar");
        this.finishButton = new JButton("Finalizar");

        panelsPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new GridLayout(2, 1));
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        this.imagePanel.setPreferredSize(new Dimension(0, getHeight() / 2));

        // ---------------------------------------------------------------------
        southPanel.add(this.userPanel);
        southPanel.add(this.passwordPanel);

        panelsPanel.add(this.imagePanel, BorderLayout.CENTER);
        panelsPanel.add(southPanel, BorderLayout.SOUTH);

        buttonsPanel.add(this.cancelButton);
        buttonsPanel.add(this.finishButton);

        this.add(panelsPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.cancelButton.addActionListener(ae -> {
            this.dispose();
        });

        this.finishButton.addActionListener(ae -> {
            this.finishAction();
        });

        this.userPanel.getNicknameField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    passwordPanel.getPasswordField().requestFocus();
                }
            }
        });

        this.passwordPanel.getConfirmPasswordField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    finishButton.doClick();
                }
            }
        });
    }

    /* ______________________________________________________________________ */
    private void finishAction() {
        boolean bool = true;
        String string = this.verifyAccount();

        if (!string.equals("Error")) {
            Account _account = this.initAccount();

            if (string.equals("Admin")) {
                if (verifyAdmin()) {
                    _account = (AdminAccount) _account;
                } else {
                    userPanel.setMessage("No tiene permiso para ser Administrador");
                    bool = false;
                }
            } else {
                _account = (UserAccount) _account;
            }

            if (bool) {
                this.register(_account);
                this.dispose();
            }
        }
    }

    /* ______________________________________________________________________ */
    public boolean verifyAdmin() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        PasswordField passwordField = new PasswordField();
        panel.add(new JLabel("Contraseña de Administrador:"));
        panel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Ingresar como Administrador",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (option == DialogPane.OK_OPTION) {
            String password = passwordField.getText();
            if (!password.isEmpty()) {
                if (password.equals(RegisterAccountDialog.ADMIN_PASSWORD)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* ______________________________________________________________________ */
    public String verifyAccount() {

        String username = this.userPanel.getUsername();
        String nickname = this.userPanel.getNickname();
        String password = this.passwordPanel.getPassword();
        String confirmPassword = this.passwordPanel.getConfirmPassword();

        boolean verifiedUser = true;
        boolean verifiedPassword = true;
        boolean admin = nickname.toLowerCase().contains("admin");

        try {
            this.verifyUser(username, nickname);
            this.userPanel.setMessage(" ");

        } catch (Exception e) {
            this.userPanel.setMessage(e.getMessage());
            verifiedUser = false;
        }

        try {
            this.verifyPassword(password, confirmPassword);
            this.passwordPanel.setMessage(" ");
        } catch (Exception e) {
            this.passwordPanel.setMessage(e.getMessage());
            verifiedPassword = false;
        }

        if (verifiedUser && verifiedPassword) {
            return admin ? "Admin" : "Ok";
        } else {
            return "Error";
        }
    }

    /* ______________________________________________________________________ */
    public void verifyUser(String username, String nickname) throws Exception {
        BinaryFileManager binaryFileManager;
        ArrayList<Object> objects;

        if (username.isEmpty()) {
            throw new Exception("El campo del nombre no debe estar vacio");
        } else if (username.length() < 6) {
            throw new Exception("El nombre debe tener almenos 6 caracteres");
        } else if (nickname.isEmpty()) {
            throw new Exception("El campo del apodo no debe estar vacio");
        } else if (nickname.length() < 6) {
            throw new Exception("El apodo debe tener almenos 6 caracteres");
        } else {

            for (int i = 0; i < username.length(); i++) {
                char c = username.charAt(i);
                if (!Character.isAlphabetic(c) && !Character.isWhitespace(c)) {
                    throw new Exception("Caracteres validos para nombre: (a-z), ' '");
                }
            }
            for (int i = 0; i < nickname.length(); i++) {
                char c = nickname.charAt(i);

                if (!Character.isAlphabetic(c) && !Character.isDigit(c)
                        && !Character.isWhitespace(c)) {
                    throw new Exception("Caracteres validos para apodo: (a-z), (0-9), ' '");
                }
            }

            binaryFileManager = new BinaryFileManager("accounts.dat");
            objects = binaryFileManager.read();

            for (Object object : objects) {
                Account _account = (Account) object;
                if (_account.getNickname().toLowerCase().equals(nickname.toLowerCase())) {
                    throw new Exception("El usuario ya existe");
                }
            }
        }
    }

    /* ______________________________________________________________________ */
    public void verifyPassword(String password, String confirmPassword) throws Exception {
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            throw new Exception("El campo de la contraseña no debe estar vacio");
        } else if (password.length() < 6 || confirmPassword.length() < 6) {
            throw new Exception("La contraseña debe tener almenos 6 caracteres");
        } else if (password.isEmpty() || confirmPassword.isEmpty()) {
            throw new Exception("La campo de la contraseña no debe estar vacio");
        } else if (!password.equals(confirmPassword)) {
            throw new Exception("Las contraseñas no coinciden");
        } else {

            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (!Character.isAlphabetic(c) && !Character.isDigit(c)
                        && !Character.isWhitespace(c)) {
                    throw new Exception(
                            "Caracteres validos para contraseña: (a-z), (0-9), ' '"
                    );
                }
            }

            for (int i = 0; i < confirmPassword.length(); i++) {
                char c = confirmPassword.charAt(i);
                if (!Character.isAlphabetic(c) && !Character.isDigit(c)
                        && !Character.isWhitespace(c)) {
                    throw new Exception(
                            "Caracteres validos para contraseña: (a-z), (0-9), ' '"
                    );
                }
            }
        }
    }

    /* ______________________________________________________________________ */
    private Account initAccount() {
        Account _account;

        String image = this.imagePanel.getImagePath();
        String username = this.userPanel.getUsername();
        String nickname = this.userPanel.getNickname();
        String password = this.passwordPanel.getPassword();

        if (nickname.toLowerCase().contains("admin")) {
            _account = new AdminAccount(username, nickname, password, image);

        } else {
            _account = new UserAccount(username, nickname, password, image);
        }
        return _account;
    }

    /* ______________________________________________________________________ */
    public void register(Account account) {
        BinaryFileManager binaryFileManager = new BinaryFileManager("accounts.dat");

        if (account instanceof AdminAccount) {
            this.setAccount((AdminAccount) account);
        } else if (account instanceof UserAccount) {
            this.setAccount((UserAccount) account);
        }

        System.out.println("new account: " + this.account);
        binaryFileManager.add(this.account);
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new RegisterAccountDialog().showTestDialog();
    }
}
