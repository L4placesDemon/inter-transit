package interfaces;

import interfaces.showaccount.ShowAccountDialog;
import interfaces.showaccount.showadminaccount.ShowAdminDialog;
import interfaces.showaccount.showuseraccount.ShowUserDialog;
import interfaces.signin.SigninDialog;
import interfaces.workshops.WorkshopsPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

import tools.Tools;
import tools.binaryfilemanager.BinaryFileManager;
import tools.components.Dialog;
import tools.components.DialogPane;

import worldclasses.Settings;
import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;
import worldclasses.accounts.UserAccount;

public class MainFrame extends JFrame {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = 5008656210722811627L;

    private Account account;

    private MenuPanel menuPanel;

    /* CONSTRUCTORS _________________________________________________________ */
    public MainFrame(Account account) {
        this.account = account;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        String logo = null;
        String theme;

        theme = Settings.getCurrentSettings().getTheme();

        if (theme.equals(Settings.LIGHT_THEME)) {
            Settings.lightTheme();
            logo = Settings.LIGHT_LOGO;
        } else if (theme.equals(Settings.DARK_THEME)) {
            Settings.darkTheme();
            logo = Settings.DARK_LOGO;
        }
        System.out.println(logo);

        // Set up Frame --------------------------------------------------------
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());
        this.setSize(1000, 700);
        this.setIconImage(Tools.getImage(logo));
        this.setMinimumSize(new Dimension(460, 390));
        this.setLocationRelativeTo(null);
        this.setTitle("Inter Transit");

        // Set up Components ---------------------------------------------------
        this.menuPanel = new MenuPanel(this.getAccount());

        // ---------------------------------------------------------------------
        this.add(this.menuPanel);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Frame Events --------------------------------------------------------
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent ce) {
                int less = getWidth();
                if (getHeight() < getWidth()) {
                    less = getHeight();
                }
                menuPanel.setLogo(less);
            }

            @Override
            public void componentMoved(ComponentEvent ce) {
                int less = getWidth();
                if (getHeight() < getWidth()) {
                    less = getHeight();
                }
                menuPanel.setLogo(less);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                int result = DialogPane.yesNoOption("Cerrar Aplicacion?");

                if (result == DialogPane.OK_OPTION) {
                    setDefaultCloseOperation(EXIT_ON_CLOSE);
                }
            }
        });

        // Components Events ---------------------------------------------------
        this.menuPanel.getSettingsButton().addActionListener(ae -> {
            this.settingsAction();
        });

        this.menuPanel.getUserButton().addActionListener(ae -> {
            this.accountAction();
        });

        this.menuPanel.getWorkshopsButton().addActionListener(ae -> {
            this.workshopsSigninAction();
        });

        this.menuPanel.getTestButton().addActionListener(ae -> {
        });
    }

    /* ______________________________________________________________________ */
    private void settingsAction() {
        String theme = Settings.getCurrentSettings().getTheme();
        int result;
        SettingsDialog settingsDialog = null;

        System.out.println("theme: " + theme);

        if (theme.equals(Settings.LIGHT_THEME)) {
            settingsDialog = new SettingsDialog(false);
        } else if (theme.equals(Settings.DARK_THEME)) {
            settingsDialog = new SettingsDialog(true);
        }

        result = settingsDialog.showDialog();
        System.out.println("result: " + result);
        if (result == SettingsDialog.OK_OPTION) {

            theme = settingsDialog.getTheme();
            if (theme.equals(Settings.LIGHT_THEME)) {
                Settings.lightTheme();
            } else if (theme.equals(Settings.DARK_THEME)) {
                Settings.darkTheme();
            }
            new BinaryFileManager("settings.dat").write(new Settings(
                    theme,
                    settingsDialog.getSelectedFont()
            ));

            new MainFrame(this.getAccount()).setVisible(true);
            dispose();
        }
    }

    /* ______________________________________________________________________ */
    private boolean accountAction() {
        Dialog accountDialog;

        accountDialog = this.getAccount() == null
                ? new SigninDialog()
                : this.getAccount() instanceof UserAccount
                ? new ShowUserDialog((UserAccount) this.getAccount())
                : new ShowAdminDialog((AdminAccount) this.getAccount());

        int option = accountDialog.showDialog();
        String imagePath = "profile/image-00";

        if (this.getAccount() == null) {
            if (option == SigninDialog.OK_OPTION) {
                this.setAccount(((SigninDialog) accountDialog).getAccount());
                imagePath = this.getAccount().getImage();
            }
        } else {
            if (option == ShowUserDialog.OK_OPTION) {
                this.setAccount(null);
            } else {
                this.setAccount(((ShowAccountDialog) accountDialog).getAccount());
                imagePath = this.getAccount().getImage();
            }
        }
        this.menuPanel.getUserButton().setIcon(Tools.getImageIcon(imagePath, 80, 80));

        return this.getAccount() != null && option == ShowUserDialog.OK_OPTION;
    }

    /* ______________________________________________________________________ */
    private void showWorkshopsPanel() {
        WorkshopsPanel workshopsPanel = new WorkshopsPanel(this.getAccount());

        workshopsPanel.getCloseButton().addActionListener(ae -> {
            String imagePath = "profile/image-00";
            this.setAccount(workshopsPanel.getAccount());
            if (this.getAccount() != null) {
                imagePath = this.getAccount().getImage();
            }

            this.remove(workshopsPanel);
            this.add(this.menuPanel);
            this.menuPanel.getUserButton().setIcon(Tools.getImageIcon(imagePath, 80, 80));
            this.menuPanel.updateUI();
        });

        this.remove(this.menuPanel);
        this.add(workshopsPanel);
        workshopsPanel.updateUI();

    }

    /* ______________________________________________________________________ */
    private void workshopsSigninAction() {
        if (getAccount() == null) {
            int option = DialogPane.yesNoCancelOption(
                    "Iniciar Sesion",
                    "Iniciar sesion para guardar el progreso?"
            );

            if (option == DialogPane.YES_OPTION) {
                if (this.accountAction()) {
                    this.showWorkshopsPanel();
                }
            } else if (option == DialogPane.NO_OPTION) {
                this.showWorkshopsPanel();
            }
        } else {
            this.showWorkshopsPanel();
        }
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }

    /* TEST METHODS _________________________________________________________ */
    private static void initTestAccounts() {
        BinaryFileManager manager = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE);
        Random random = new Random();

        if (manager.read().isEmpty()) {
            manager.add(new AdminAccount(
                    "Alejandro", "Admin1", "Passwd",
                    "profile/image-31"
            ));
            for (int i = 10; i < 20; i++) {
                UserAccount userAccount = new UserAccount(
                        "test user", "nickname" + i, "passwd",
                        "profile/image-" + i);
                userAccount.setLevel(random.nextInt(10) + 1);
                userAccount.setPoints(random.nextInt(50) + 1);
                manager.add(userAccount);
            }
        }
    }

    /* ______________________________________________________________________ */
    private static void sortTestAccounts() {
        BinaryFileManager manager = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE);
        ArrayList<Account> accounts = new ArrayList<>();

        manager.read().forEach(i -> {
            accounts.add((Account) i);
        });
        accounts.sort((Account a, Account b) -> {
            return a instanceof AdminAccount ? 0 : 1;
        });
        accounts.sort((Account a, Account b) -> {
            return a.getNickname().compareTo(b.getNickname());
        });

        manager.clear();
        accounts.forEach(i -> {
            manager.add(i);
        });
    }

    /* ______________________________________________________________________ */
    private static void showTestAccounts() {
        ArrayList<Object> objects = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE).read();
        System.out.println(objects.size());

        objects.forEach(i -> {
            System.out.println(i);
        });
    }

    /* ______________________________________________________________________ */
    private static void initTestThemes() {
//        int chars = 5;
        Random random = new Random();
//        String os = System.getProperty("os.name").toLowerCase();
//        if (os.contains("win")) {
//            chars = 6;
//        }

        String pathFolder = Settings.getResource() + "src/files/";

        File foldersFolder = new File(pathFolder);
        if (!foldersFolder.exists()) {
            try {
                foldersFolder.mkdir();
            } catch (Exception ex) {
            }
        }

        File folder;
        File file;
        FileWriter fileWriter;

        for (int i = 1; i < 10; i++) {
            String name = "Tema";

            try {
                folder = new File(pathFolder + name + " " + i);
                folder.mkdir();

                file = new File(pathFolder + name + " " + i + "/descripcion.txt");
                file.createNewFile();

                fileWriter = new FileWriter(file);
                fileWriter.write("description=Descripcion del Tema " + i + '\n');
                fileWriter.write("value=" + (random.nextInt(9999) + 1) + '\n');
                fileWriter.write("views=" + random.nextInt(19) + 1 + "\n\n");
                fileWriter.close();
            } catch (IOException e) {
                System.out.println(e);
            }

            for (int j = 1; j < 10; j++) {
                try {
                    file = new File(pathFolder + name + " " + i + "/Tip " + j + ".txt");
                    file.createNewFile();
                    fileWriter = new FileWriter(file);
                    fileWriter.write("Contenido del Tip " + j);
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        initTestAccounts();
        sortTestAccounts();
        showTestAccounts();
//        initTestThemes();

        new MainFrame(null).setVisible(true);
    }
}