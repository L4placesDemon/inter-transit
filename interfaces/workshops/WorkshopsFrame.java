package interfaces.workshops;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utilities.Dialog;
import worldclasses.accounts.Account;
import worldclasses.accounts.UserAccount;

public class WorkshopsFrame extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;
//    private ArrayList<Theme> themes;

    private JButton backButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public WorkshopsFrame(Account account) {
        super(new JFrame(), true);
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
        this.backButton = new JButton("Volver");

        northPanel = new UserPanel(this.account);
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        centerPanel = this.addThemes();

        // ---------------------------------------------------------------------
        southPanel.add(this.backButton);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.backButton.addActionListener(ae -> {
            this.dispose();
        });
    }

    /* ______________________________________________________________________ */
    private JPanel addThemes() {
        JPanel themesPanel = new JPanel();
        ThemeButton themeButton;
        ArrayList<File> files;

        String description = "";
        String path = WorkshopsFrame.class.getResource("/files").toString().substring(5);

        File themeFolders = new File(path);
        File descriptionFile;

        path = path.substring(0, path.indexOf("build")) + "src/files";
        themesPanel.setLayout(new BoxLayout(themesPanel, BoxLayout.Y_AXIS));

        if (themeFolders.exists()) {
            files = new ArrayList<>(Arrays.asList(themeFolders.listFiles()));
            System.out.println(files);
            
            for (File theme : themeFolders.listFiles()) {

                descriptionFile = new File(path + "/" + theme.getName() + "/descripcion.txt");

                if (descriptionFile.exists()) {
                    description = this.getFileText(descriptionFile);

                    themeButton = new ThemeButton(theme.getName(), description);
                    themesPanel.add(themeButton);
                } else {
                    System.out.println("description file do not exists");
                }
            }
        }

        return themesPanel;
    }

    /* ______________________________________________________________________ */
    private String getFileText(File file) {
        String text = "";
        String line;
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            line = bufferedReader.readLine();

            while (line != null) {
                text += line;
                line = bufferedReader.readLine();

                if (line != null) {
                    text += "\n";
                }
            }
            bufferedReader.close();

        } catch (IOException e) {
        }
        return text;
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* ______________________________________________________________________ */
//    public ArrayList<Themes> getThemes() {
//        return this.themes;
//    }
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
