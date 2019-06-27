package interfaces.workshops;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.components.Panel;

import worldclasses.Settings;
import worldclasses.themes.Tip;
import worldclasses.accounts.Account;
import worldclasses.accounts.UserAccount;
import worldclasses.themes.Theme;

public class WorkshopsPanel extends Panel {

    /* ATTRIBUTES ___________________________________________________________ */
    private Account account;
    private ArrayList<Theme> themes;

    private JButton backButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public WorkshopsPanel(Account account) {

        this.account = account;
        this.themes = new ArrayList<>();

        this.initThemes();
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

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.backButton = new JButton("Volver");

        userPanel = new UserPanel(this.account);
        centerPanel = new JPanel();
        northPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        labelsPanel = new JPanel(new GridLayout());

        // ---------------------------------------------------------------------
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------------------------
        this.themes.forEach(i -> {
            ThemeButton themeButton = new ThemeButton(i);

            themeButton.addActionListener(ae -> {
                new ThemeDialog(i, this.account).showDialog();
            });
            centerPanel.add(themeButton);
        });

        labelsPanel.add(new JLabel("Imagen", JLabel.CENTER));
        labelsPanel.add(new JLabel("Titulo", JLabel.CENTER));
        labelsPanel.add(new JLabel("Descripcion", JLabel.CENTER));
        labelsPanel.add(new JLabel("Progreso", JLabel.CENTER));
        labelsPanel.add(new JLabel("Valor", JLabel.CENTER));
        labelsPanel.add(new JLabel("Visitas", JLabel.CENTER));

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
    private void initThemes() {
        String themesDirectoryPath = Settings.class.getResource("/tools").toString().substring(5);

        File themesDirectory;

        Object[] description = null;
        ArrayList<Tip> tips;
        String fileName;

        themesDirectoryPath = themesDirectoryPath.substring(0, themesDirectoryPath.indexOf("build")) + "src/files";
        themesDirectory = new File(themesDirectoryPath);

        if (themesDirectory.exists()) {
            for (File themeDirectory : themesDirectory.listFiles()) {

                description = this.getDescription(
                        themesDirectoryPath + "/"
                        + themeDirectory.getName() + "/descripcion.txt");

                tips = new ArrayList<>();
                for (File themeFile : themeDirectory.listFiles()) {
                    fileName = themeFile.getName();

                    if (!fileName.contains("descripcion")) {
                        tips.add(new Tip(
                                fileName.substring(0, fileName.indexOf(".txt")),
                                this.getFileText(themeFile)
                        ));
                    }
                }
                if (description != null) {
                    this.themes.add(new Theme(
                            null,
                            themeDirectory.getName(),
                            description[0] + "",
                            tips,
                            (int) description[1],
                            (double) description[2],
                            (int) description[3]
                    ));
                }
            }
        }
//        for (Theme theme : this.themes) {
//            System.out.println(theme);
//        }
    }

    /* ______________________________________________________________________ */
    private Object[] getDescription(String themeDirectoryPath) {
        File descriptionFile = new File(themeDirectoryPath);

        if (descriptionFile.exists()) {
            String text = this.getFileText(descriptionFile);

            int start = text.indexOf('=') + 1;
            int end = text.indexOf('\n');
            String description = text.substring(start, end);

            start = text.indexOf('=', start) + 1;
            end = text.indexOf('\n', end + 1);
            String value = text.substring(start, end);

            start = text.indexOf('=', start) + 1;
            end = text.indexOf('\n', end + 1);
            String progress = text.substring(start, end);

            start = text.indexOf('=', start) + 1;
            end = text.indexOf('\n', end + 1);
            String views = text.substring(start, end);

            return new Object[]{
                description,
                Integer.parseInt(progress),
                Double.parseDouble(value),
                Integer.parseInt(views)
            };
        } else {
            System.out.println("description file do not exists");
        }
        return null;
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
                    text += '\n';
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
    public ArrayList<Theme> getThemes() {
        return this.themes;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }

    /* ______________________________________________________________________ */
    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    @Override
    public JButton getCloseButton() {
        return this.backButton;
    }

    /*  MAIN ________________________________________________________________ */
    public static void main(String[] args) {
        new WorkshopsPanel(new UserAccount(
                "Alejandro",
                "413J0c",
                "passwd",
                "profile/image-31"
        )).showTestDialog();
    }
}
