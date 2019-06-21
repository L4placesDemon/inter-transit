package interfaces;

import interfaces.showaccount.ShowAccountDialog;
import interfaces.signin.SigninDialog;
import interfaces.workshops.WorkshopsFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import tools.Dialog;
import tools.DialogPane;
import tools.binaryfilemanager.BinaryFileManager;

import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;
import worldclasses.accounts.UserAccount;
import tools.Tools;

public class MainMenuFrame extends JFrame {

    /* ATTRIBUTES ___________________________________________________________ */
    public static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);

    private Account account;
    private JButton aboutButton;
    private JButton userButton;
    private JButton worksopsButton;
    private JButton testButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public MainMenuFrame() {
        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JLabel logoLabel;

        JPanel northPanel;
        JPanel southPanel;

        // Set up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(350, 537);
        this.setIconImage(Tools.getImage("/images/logos/logo.png"));
        this.setLocationRelativeTo(null);
        this.setTitle("Inter Transit");
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        this.aboutButton = new JButton();
        this.userButton = new JButton();
        this.worksopsButton = new JButton();
        this.testButton = new JButton();

        logoLabel = new JLabel();

        northPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel = new JPanel(new GridLayout(1, 3, 20, 20));

        // ---------------------------------------------------------------------
        this.aboutButton.setBorder(null);
        this.aboutButton.setIcon(Tools.getImageIcon("/images/about.png", 30, 30));

        this.userButton.setBorder(null);
        this.userButton.setFocusable(false);
        this.userButton.setIcon(Tools.getImageIcon("/images/profile/image-00.png", 80, 80));

        this.worksopsButton.setBorder(null);
        this.worksopsButton.setFocusable(false);
        this.worksopsButton.setIcon(Tools.getImageIcon("/images/learn.png", 70, 70));

        this.testButton.setBorder(null);
        this.testButton.setFocusable(false);
        this.testButton.setIcon(Tools.getImageIcon("/images/test.png", 70, 70));

        logoLabel.setIcon(Tools.getImageIcon("/images/logos/logo.png", 350, 350));

        northPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
        southPanel.setPreferredSize(new Dimension(0, 100));
        southPanel.setBorder(new EmptyBorder(0, 40, 15, 40));

        // ---------------------------------------------------------------------
        northPanel.add(new JLabel());
        northPanel.add(new JLabel());
        northPanel.add(this.aboutButton);

        southPanel.add(this.userButton);
        southPanel.add(this.worksopsButton);
        southPanel.add(this.testButton);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(logoLabel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Frame Events --------------------------------------------------------
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Components Events ---------------------------------------------------
        this.aboutButton.addActionListener(ae -> {
            new AboutDialog().showDialog();
        });

        this.userButton.addActionListener(ae -> {
            String result = this.userAction();
            System.out.println(result);
        });

        this.worksopsButton.addActionListener(ae -> {
            this.workshopsSigninAction();
        });

        this.testButton.addActionListener(ae -> {
        });
    }

    /* ______________________________________________________________________ */
    private String userAction() {
        String result;
        Dialog userDialog;

        userDialog = this.getAccount() == null
                ? new SigninDialog()
                : new ShowAccountDialog(this.getAccount());

        int option = userDialog.showDialog();
        String imagePath = "/images/profile/image-00.png";

        if (this.getAccount() == null) {
            if (option == SigninDialog.OK_OPTION) {
                this.setAccount(((SigninDialog) userDialog).getAccount());
                imagePath = this.getAccount().getImage();
                result = "ok sign in";
            } else {
                result = "cancel sign in";
            }
        } else {
            if (option == ShowAccountDialog.OK_OPTION) {
                this.setAccount(null);
                result = "ok show";
            } else {
                this.setAccount(((ShowAccountDialog) userDialog).getAccount());
                imagePath = this.getAccount().getImage();
                result = "cancel show";
            }
        }
        this.userButton.setIcon(Tools.getImageIcon(imagePath, 80, 80));

        return result;
    }

    /* ______________________________________________________________________ */
    private void showWorkshopsDialog() {
        this.setVisible(false);
        new WorkshopsFrame(this.getAccount()).showDialog();
        this.setVisible(true);
    }

    /* ______________________________________________________________________ */
    private void workshopsSigninAction() {
        String result;

        if (getAccount() == null) {
            int option = DialogPane.yesNoCancelOption(
                    "Iniciar Sesion",
                    "Iniciar sesion para guardar el progreso?"
            );

            if (option == DialogPane.YES_OPTION) {
                result = this.userAction();
                if (result.equals("ok sign in")) {
                    this.showWorkshopsDialog();
                }
            } else if (option == DialogPane.NO_OPTION) {
                this.showWorkshopsDialog();
            }
        } else {
            this.showWorkshopsDialog();
        }
    }

    /* ______________________________________________________________________ */
    private static void initUI() {
        UIManager.put("OptionPane.background", Color.white);
        UIManager.put("OptionPane.messageFont", MainMenuFrame.DEFAULT_FONT);
        UIManager.put("OptionPane.buttonFont", MainMenuFrame.DEFAULT_FONT);

        UIManager.put("Panel.background", Color.white);
        UIManager.put("ToggleButton.background", Color.white);
        UIManager.put("Label.font", MainMenuFrame.DEFAULT_FONT);

        UIManager.put("Button.background", Color.white);
        UIManager.put("Button.font", MainMenuFrame.DEFAULT_FONT);

        UIManager.put("RadioButton.font", MainMenuFrame.DEFAULT_FONT);
        UIManager.put("RadioButton.background", Color.white);

        UIManager.put("ScrollPane.background", Color.white);
        UIManager.put("ScrollBar.background", Color.white);
        
        UIManager.put("TextField.background", Color.white);
    }

    /* ______________________________________________________________________ */
    private static void initTestAccounts() {
        BinaryFileManager manager = new BinaryFileManager("accounts.dat");
        Random random = new Random();

        if (manager.read().isEmpty()) {
            manager.add(new AdminAccount(
                    "Alejandro", "Admin1", "Passwd",
                    "/images/profile/image-31.png"
            ));
            for (int i = 10; i < 20; i++) {
                UserAccount userAccount = new UserAccount(
                        "test user", "nickname" + i, "passwd",
                        "/images/profile/image-" + i + ".png");
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

        String pathFolder = MainMenuFrame.class.getResource("/utilities").toString().substring(chars);
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
                fileWriter.write("progress=" + (random.nextInt(99) + 1) + '\n');
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

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return account;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        initUI();
        initTestAccounts();
        sortTestAccounts();
        showTestAccounts();
        initTestThemes();
        new MainMenuFrame().setVisible(true);
    }
}
