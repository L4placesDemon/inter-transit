package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.util.ArrayList;

import interfaces.showaccount.ShowAccountDialog;
import interfaces.signin.SigninDialog;
import interfaces.workshops.WorkshopsFrame;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;
import worldclasses.accounts.UserAccount;

import utilities.binaryfilemanager.BinaryFileManager;
import utilities.Dialog;
import utilities.DialogPane;
import utilities.Utilities;

/* NOTES ____________________________________________________________________ */
// - Statistics for Workshops
// - Statistics for Users in each Workshop
// - Add getters and setter for each attribute in each class
public class MainMenuFrame extends JFrame {

    /* ATTRIBUTES ___________________________________________________________ */
    public static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);

    private Account account;

    private JLabel logoLabel;
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
        JPanel northPanel;
        JPanel southPanel;

        // Set up Frame --------------------------------------------------------
//        this.getContentPane().setBackground(Color.white);
        this.setLayout(new BorderLayout());
        this.setSize(350, 537);
        this.setIconImage(Utilities.getImage("/images/logos/logo.png"));
        this.setLocationRelativeTo(null);
        this.setTitle("Inter Transit");
        this.setResizable(false);

        // Set up Components ---------------------------------------------------
        this.aboutButton = new JButton();
        this.logoLabel = new JLabel();
        this.userButton = new JButton();
        this.worksopsButton = new JButton();
        this.testButton = new JButton();

        northPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel = new JPanel(new GridLayout(1, 3, 20, 20));

        // ---------------------------------------------------------------------
        this.aboutButton.setBackground(Color.white);
        this.aboutButton.setBorder(null);
        this.aboutButton.setIcon(Utilities.getImageIcon("/images/about.png", 30, 30));
        this.aboutButton.setOpaque(false);

        this.logoLabel.setIcon(Utilities.getImageIcon("/images/logos/logo.png", 350, 350));

        this.userButton.setBackground(Color.white);
        this.userButton.setBorder(null);
        this.userButton.setFocusable(false);
        this.userButton.setIcon(Utilities.getImageIcon("/images/profile/image-00.png", 80, 80));
        this.userButton.setOpaque(false);

        this.worksopsButton.setBackground(Color.white);
        this.worksopsButton.setBorder(null);
        this.worksopsButton.setFocusable(false);
        this.worksopsButton.setIcon(Utilities.getImageIcon("/images/learn.png", 70, 70));
        this.worksopsButton.setOpaque(false);

        this.testButton.setBackground(Color.white);
        this.testButton.setBorder(null);
        this.testButton.setFocusable(false);
        this.testButton.setIcon(Utilities.getImageIcon("/images/test.png", 70, 70));
        this.testButton.setOpaque(false);

        northPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
        northPanel.setBackground(Color.white);
        southPanel.setPreferredSize(new Dimension(0, 100));
        southPanel.setBackground(Color.white);
        southPanel.setBorder(new EmptyBorder(0, 40, 15, 40));

        // ---------------------------------------------------------------------
        northPanel.add(new JLabel());
        northPanel.add(new JLabel());
        northPanel.add(this.aboutButton);

        southPanel.add(this.userButton);
        southPanel.add(this.worksopsButton);
        southPanel.add(this.testButton);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(this.logoLabel, BorderLayout.CENTER);
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

        userDialog = this.account == null
                ? new SigninDialog()
                : new ShowAccountDialog(this.account);

        int option = userDialog.showDialog();
        String imagePath = "/images/profile/image-00.png";

        if (this.account == null) {
            if (option == SigninDialog.OK_OPTION) {
                this.account = ((SigninDialog) userDialog).getAccount();
                imagePath = this.account.getImage();
                result = "ok sign in";
            } else {
                result = "cancel sign in";
            }
        } else {
            if (option == ShowAccountDialog.OK_OPTION) {
                this.account = null;
                result = "ok show";
            } else {
                this.account = ((ShowAccountDialog) userDialog).getAccount();
                imagePath = this.account.getImage();
                result = "cancel show";
            }
        }
        this.userButton.setIcon(Utilities.getImageIcon(imagePath, 80, 80));

        return result;
    }

    /* ______________________________________________________________________ */
    private void showWorkshopsDialog() {
        this.setVisible(false);
        new WorkshopsFrame(this.account).showDialog();
        this.setVisible(true);
    }

    /* ______________________________________________________________________ */
    private void workshopsSigninAction() {
        String result;

        if (account == null) {
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
        UIManager.put("Button.background", Color.white);
        UIManager.put("ToggleButton.background", Color.white);
        UIManager.put("Label.font", MainMenuFrame.DEFAULT_FONT);
        UIManager.put("Button.font", MainMenuFrame.DEFAULT_FONT);
        UIManager.put("RadioButton.font", MainMenuFrame.DEFAULT_FONT);
        UIManager.put("RadioButton.background", Color.white);
        UIManager.put("ScrollPane.background", Color.white);
        UIManager.put("ScrollBar.background", Color.white);
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

        String pathFolder = MainMenuFrame.class.getResource("/files").toString().substring(chars);
        pathFolder = pathFolder.substring(0, pathFolder.indexOf("build")) + "src/files/";
        File folder;
        File file;
        FileWriter fileWriter;

        for (int i = 1; i < 10; i++) {
            try {
                folder = new File(pathFolder + "Tema " + i);
                folder.mkdir();

                file = new File(pathFolder + "Tema " + i + "/descripcion.txt");
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
                    file = new File(pathFolder + "Tema " + i + "/Tip " + j + ".txt");
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
        initUI();
        initTestAccounts();
        sortTestAccounts();
        showTestAccounts();
        initTestThemes();
        EventQueue.invokeLater(() -> {
            new MainMenuFrame().setVisible(true);
        });
    }
}
