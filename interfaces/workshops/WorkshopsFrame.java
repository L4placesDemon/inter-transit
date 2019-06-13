package interfaces.workshops;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import worldclasses.Account;
import worldclasses.UserAccount;

public class WorkshopsFrame extends JFrame {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;
//    private ArrayList<Theme> themes;

    private JPanel themesPanel;
    private JButton backButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public WorkshopsFrame(Account account) {
        this.account = account;
//        this.themes = themes;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        UserPanel northPanel;
        JPanel centerPanel;
        JPanel southPanel;

        // Set up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("Temas");

        // Set up Components ---------------------------------------------------
        this.themesPanel = new JPanel();
        this.backButton = new JButton("Volver");

        northPanel = new UserPanel(this.account);
        centerPanel = new JPanel();
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        this.themesPanel.setLayout(new BoxLayout(this.themesPanel, BoxLayout.Y_AXIS));
        this.addThemes();

        // ---------------------------------------------------------------------
        southPanel.add(this.backButton);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(this.themesPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    private void addThemes() {
        String path = WorkshopsFrame.class.getResource("/files").toString().substring(5);
        path = path.substring(0, path.indexOf("build")) + "src/files";
        ThemeButton themeButton;

        File files = new File(path);

        if (files.exists()) {
            for (File theme : files.listFiles()) {
                System.out.println(theme.getName());

                File description = new File(path + "/" + theme.getName() + "/descripcion.txt");

                if (description.exists()) {
                    System.out.println(description.getName());
                    themeButton = new ThemeButton(theme.getName(), description.getName());

//                    for (File file : directory.listFiles()) {
//                        System.out.println(file.getName());
//                    }
                    this.themesPanel.add(themeButton);
                } else {
                    System.out.println("desc not exists");
                }
            }
        }
        System.out.println("...");
    }

    private String getFileText(File file) {
        System.out.println("enter");
        String text = "";
        BufferedReader reader;

        try {
            System.out.println("try");
            reader = new BufferedReader(new FileReader(file));
            
            String line = reader.readLine();
            System.out.println(line);
            while (!line.isBlank()) {
                text += line + "\n";
            }   
            reader.close();
        } catch (Exception e) {
        }
        return text;
    }

    private void initEvents() {
        // Frame Events --------------------------------------------------------
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Components Events ---------------------------------------------------
        this.backButton.addActionListener(ae -> {
            this.dispose();
        });
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /*  MAIN ________________________________________________________________ */
    public static void main(String[] args) {
        new WorkshopsFrame(new UserAccount(
                "Alejandro",
                "413J0c",
                "passwd",
                "/images/profile/image-31.png")
        ).setVisible(true);
    }
}
