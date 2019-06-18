package interfaces.workshops;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utilities.Dialog;
import worldclasses.accounts.Account;
import worldclasses.accounts.UserAccount;
import worldclasses.themes.Theme;

public class WorkshopsFrame extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;
    private ArrayList<Theme> themes;

    private JButton backButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public WorkshopsFrame(Account account) {
        super(new JFrame(), true);
        this.account = account;
        this.themes = new ArrayList<>();

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        UserPanel userPanel;
        JPanel labelsPanel;

        JPanel northPanel;
        JPanel centerPanel;
        JPanel southPanel;

        JLabel imageLabel;
        JLabel titleLabel;
        JLabel descriptionLabel;
        JLabel progressLabel;
        JLabel valueLabel;

        // Set up Frame --------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(300, 200));
        this.setTitle("Temas");

        // Set up Components ---------------------------------------------------
        this.backButton = new JButton("Volver");

        userPanel = new UserPanel(this.account);
        northPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        labelsPanel = new JPanel(new GridLayout());

        imageLabel = new JLabel("Imagen", JLabel.CENTER);
        titleLabel = new JLabel("Titulo", JLabel.CENTER);
        descriptionLabel = new JLabel("Descripcion", JLabel.CENTER);
        progressLabel = new JLabel("Progreso", JLabel.CENTER);
        valueLabel = new JLabel("Valor", JLabel.CENTER);

        // ---------------------------------------------------------------------
        centerPanel = this.addThemes();

        // ---------------------------------------------------------------------
        labelsPanel.add(imageLabel);
        labelsPanel.add(titleLabel);
        labelsPanel.add(descriptionLabel);
        labelsPanel.add(progressLabel);
        labelsPanel.add(valueLabel);

        northPanel.add(userPanel, BorderLayout.NORTH);
        northPanel.add(labelsPanel, BorderLayout.CENTER);

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
        themesPanel.setLayout(new BoxLayout(themesPanel, BoxLayout.Y_AXIS));

        ThemeButton themeButton;

        String path = WorkshopsFrame.class.getResource("/files").toString().substring(5);

        File themeFolders = new File(path);
        File descriptionFile;
        ArrayList<File> files;

        path = path.substring(0, path.indexOf("build")) + "src/files";

        if (themeFolders.exists()) {
            files = new ArrayList<>(Arrays.asList(themeFolders.listFiles()));
//            System.out.println(files);

            for (File theme : themeFolders.listFiles()) {

                descriptionFile = new File(path + "/" + theme.getName() + "/descripcion.txt");

                if (descriptionFile.exists()) {

                    String text = getFileText(descriptionFile);

                    int index1 = text.indexOf('=') + 1;
                    int index2 = text.indexOf('\n');
                    String description = text.substring(index1, index2);

                    index1 = text.indexOf('=', index1) + 1;
                    index2 = text.indexOf('\n', index2 + 1);
                    String progress = text.substring(index1, index2);

                    index1 = text.indexOf('=', index1) + 1;
                    index2 = text.indexOf('\n', index2 + 1);
                    String value = text.substring(index1, index2);

                    index1 = text.indexOf('=', index1) + 1;
                    index2 = text.indexOf('\n', index2 + 1);
                    String views = text.substring(index1, index2);

                    Theme _theme = new Theme(
                            null,
                            theme.getName(),
                            description,
                            Integer.parseInt(progress),
                            Double.parseDouble(value),
                            Integer.parseInt(views)
                    );

                    this.themes.add(_theme);
                    themeButton = new ThemeButton(_theme);
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
                text += line + '\n';
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (IOException e) {
        }
//        System.out.println(text);
        return text;
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* ______________________________________________________________________ */
    public ArrayList<Theme> getThemes() {
        return this.themes;
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
