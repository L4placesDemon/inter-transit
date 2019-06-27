package interfaces;

import interfaces.showaccount.ShowAccountDialog;
import interfaces.showaccount.ShowAdminDialog;
import interfaces.showaccount.ShowUserDialog;
import interfaces.signin.SigninDialog;
import interfaces.workshops.WorkshopsPanel;

import java.awt.BorderLayout;
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
import tools.components.Panel;

import worldclasses.Settings;
import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;
import worldclasses.accounts.UserAccount;

public class MainFrame extends JFrame {

    /* ATTRIBUTES ___________________________________________________________ */
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

        initSettings();
        theme = Settings.getCurrentSettings().getTheme();

        if (theme.equals(Settings.LIGHT_THEME)) {
            Settings.lightTheme();
            logo = Settings.LIGHT_LOGO;
        } else if (theme.equals(Settings.DARK_THEME)) {
            Settings.darkTheme();
            logo = Settings.DARK_LOGO;
        } else {
            System.out.println("Error");
        }
        System.out.println(logo);

        // Set up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(950, 750);
        this.setIconImage(Tools.getImage(logo));
        this.setLocationRelativeTo(null);
        this.setTitle("Inter Transit");
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        this.menuPanel = new MenuPanel(this.getAccount());

        // ---------------------------------------------------------------------
        this.add(this.menuPanel);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Frame Events --------------------------------------------------------
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Components Events ---------------------------------------------------
        this.menuPanel.getSettingsButton().addActionListener(ae -> {
            this.settingsAction();
        });

        this.menuPanel.getUserButton().addActionListener(ae -> {
            this.userAction();
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
    private boolean userAction() {
//        String result;
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
            this.remove(workshopsPanel);
            this.add(this.menuPanel);
            this.menuPanel.updateUI();
        });

        this.remove(this.menuPanel);
        this.add(workshopsPanel);
        workshopsPanel.updateUI();
    }

    /* ______________________________________________________________________ */
    private void workshopsSigninAction() {
        boolean result;

        if (getAccount() == null) {
            int option = DialogPane.yesNoCancelOption(
                    "Iniciar Sesion",
                    "Iniciar sesion para guardar el progreso?"
            );

            if (option == DialogPane.YES_OPTION) {
                result = this.userAction();
                if (result) {
                    this.showWorkshopsPanel();
                }
            } else if (option == DialogPane.NO_OPTION) {
                this.showWorkshopsPanel();
            }
        } else {
            this.showWorkshopsPanel();
        }
    }

    /* ______________________________________________________________________ */
    private void initSettings() {
        BinaryFileManager manager = new BinaryFileManager("settings.dat");
        ArrayList<Object> objects = manager.read();

        System.out.println(objects);
        if (objects.isEmpty()) {
            manager.write(new Settings());
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
        BinaryFileManager manager = new BinaryFileManager("accounts.dat");
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
        BinaryFileManager manager = new BinaryFileManager("accounts.dat");
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
        ArrayList<Object> objects = new BinaryFileManager("accounts.dat").read();
        System.out.println(objects.size());

        objects.forEach(i -> {
            System.out.println(i);
        });
    }

    /* ______________________________________________________________________ */
    private static void initTestThemes() {
        int chars = 5;
        Random random = new Random();
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            chars = 6;
        }

        String pathFolder = MenuPanel.class.getResource("/tools").toString().substring(chars);
        pathFolder = pathFolder.substring(0, pathFolder.indexOf("build")) + "src/files/";

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
                fileWriter.write("progress=" + (random.nextInt(99) + 1) + '\n');
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
        initTestThemes();

        new MainFrame(null).setVisible(true);
    }
}
