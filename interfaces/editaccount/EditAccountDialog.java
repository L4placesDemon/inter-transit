package interfaces.editaccount;

import interfaces.registeraccount.RegisterAccountDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utilities.DialogPane;

import utilities.binaryfilemanager.BinaryFileManager;
import worldclasses.Account;
import worldclasses.AdminAccount;
import worldclasses.UserAccount;

public class EditAccountDialog extends RegisterAccountDialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private UserPanel userPanel;
    private PasswordPanel passwordPanel;

    /* CONSTRUCTORS _________________________________________________________ */
    public EditAccountDialog(Account account) {
        super(new JFrame(), true);

        if (account instanceof AdminAccount) {
            this.account = new AdminAccount(
                    account.getUsername(),
                    account.getNickname(),
                    account.getPassword(),
                    account.getImage()
            );
        } else if (account instanceof UserAccount) {
            this.account = new UserAccount(
                    account.getUsername(),
                    account.getNickname(),
                    account.getPassword(),
                    account.getImage(),
                    ((UserAccount) account).getLevel(),
                    ((UserAccount) account).getPoints()
            );
        }

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
        this.setSize(370, 525);
        this.setLocationRelativeTo(null);
        this.setTitle("Editar Datos Usuario");
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        // ---------------------------------------------------------------------
        this.imagePanel = new ImagePanel(this.account.getImage());
        this.userPanel = new UserPanel(this.account.getUsername(), this.account.getNickname());
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
                    if (passwordPanel.isEditable()) {
                        passwordPanel.getPasswordField().requestFocus();
                    } else {
                        finishButton.doClick();
                    }
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
                    _account = new AdminAccount(_account);
                } else {
                    userPanel.setMessage("No tiene permiso para ser Administrador");
                    bool = false;
                }
            }

            if (bool) {
                this.edit(_account);
                this.dispose();
                this.ok();
            }
        }
    }

    /* ______________________________________________________________________ */
    @Override
    public boolean verifyAdmin() {
        String string = DialogPane.input(
                "Ingresar como Administrador",
                "Contraseña de Administrador"
        );

        if (string != null) {
            if (string.equals(RegisterAccountDialog.ADMIN_PASSWORD)) {
                return true;
            }
        }

        return false;
    }

    /* ______________________________________________________________________ */
    @Override
    public String verifyAccount() {
        String username = this.userPanel.getUsername();
        String nickname = this.account.getNickname();
        String _nickname = this.userPanel.getNickname();
        String password = this.passwordPanel.getPassword();
        String confirmPassword = this.passwordPanel.getConfirmPassword();

        boolean verifiedUser = true;
        boolean verifiedPassword = true;

        boolean admin = _nickname.toLowerCase().contains("admin");

        if (this.userPanel.isEditable()) {

            try {
                this.verifyUser(username, nickname, _nickname);
                this.userPanel.setMessage(" ");

            } catch (Exception e) {

                this.userPanel.setMessage(e.getMessage());
                verifiedUser = false;
            }
        } else {
            this.userPanel.setMessage(" ");
        }

        if (this.passwordPanel.isEditable()) {

            try {
                this.verifyPassword(password, confirmPassword);
                this.passwordPanel.setMessage(" ");

            } catch (Exception e) {

                this.passwordPanel.setMessage(e.getMessage());
                verifiedPassword = false;
            }
        } else {
            this.passwordPanel.setMessage(" ");
        }

        if (verifiedUser && verifiedPassword) {
            return admin ? "Admin" : "Ok";
        } else {
            return "Error";
        }
    }

    /* ______________________________________________________________________ */
    public void verifyUser(String username, String nickname, String _nickname) throws Exception {
        BinaryFileManager binaryFileManager;
        ArrayList<Object> objects;

        if (username.isEmpty()) {
            throw new Exception("El campo del nombre no debe estar vacio");
        } else if (username.length() < 6) {
            throw new Exception("El nombre debe tener almenos 6 caracteres");
        } else if (_nickname.isEmpty()) {
            throw new Exception("El campo del apodo no debe estar vacio");
        } else if (_nickname.length() < 6) {
            throw new Exception("El apodo debe tener almenos 6 caracteres");
        } else {

            for (int i = 0; i < username.length(); i++) {
                char c = username.charAt(i);
                if (!Character.isAlphabetic(c) && !Character.isWhitespace(c)) {
                    throw new Exception(
                            "Caracteres validos para nombre: (a-z), ' '"
                    );
                }
            }

            for (int i = 0; i < _nickname.length(); i++) {
                char c = _nickname.charAt(i);
                if (!Character.isAlphabetic(c) && !Character.isDigit(c)
                        && !Character.isWhitespace(c)) {
                    throw new Exception(
                            "Caracteres validos para apodo: (a-z), (0-9), ' '"
                    );
                }
            }

            binaryFileManager = new BinaryFileManager("accounts.dat");
            objects = binaryFileManager.read();

            for (Object object : objects) {
                Account _account = (Account) object;
                if (!_account.getNickname().equals(nickname)) {
                    if (_account.getNickname().equals(_nickname)) {
                        throw new Exception("El usuario ya existe");
                    }
                }
            }
        }
    }

    /* ______________________________________________________________________ */
    protected Account initAccount() {
        String image = this.imagePanel.getImage();
        String name = this.userPanel.getUsername();
        String username = this.userPanel.getNickname();
        String password = this.passwordPanel.getPassword();

        if (this.account instanceof AdminAccount) {
            return new AdminAccount(name, username, password, image);

        } else if (this.account instanceof UserAccount) {
            Integer level = ((UserAccount) this.account).getLevel();
            Integer points = ((UserAccount) this.account).getPoints();

            return new UserAccount(name, username, password, image, level, points);
        }
        return null;
    }

    /* ______________________________________________________________________ */
    public void edit(Account account) {

        this.account.setImage(account.getImage());

        if (account instanceof AdminAccount) {
            this.account = new AdminAccount(this.account);
        }

        if (this.userPanel.isEditable()) {

            this.account.setUsername(account.getUsername());
            this.account.setNickname(account.getNickname());
        }
        if (this.passwordPanel.isEditable()) {
            this.account.setPassword(account.getPassword());
        }
    }

    /* GETTERS ______________________________________________________________ */
    @Override
    public Account getAccount() {
        return this.account;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new EditAccountDialog(
                new AdminAccount("Alejandro", "Admin1", "password", "/images/profile/image-01.png")
        ).setVisible(true);
    }
}
