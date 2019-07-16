package interfaces.workshops;

import interfaces.createtheme.CreateThemeDialog;
import interfaces.themestatistics.ThemesStatisticsDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import tools.Pair;
import tools.Tools;
import tools.components.DialogPane;
import tools.components.Panel;
import tools.filemanager.BinaryFileManager;
import tools.filemanager.PlainFileManager;

import worldclasses.Settings;
import worldclasses.themes.Tip;
import worldclasses.accounts.Account;
import worldclasses.accounts.AdminAccount;
import worldclasses.accounts.UserAccount;
import worldclasses.themes.Theme;

public class WorkshopsPanel extends Panel {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -2890938347215601719L;

    private Account account;
    private ArrayList<Theme> themes;

    private AccountButton accountButton;
    private JTree themesTree;
    private JPanel filePanel;

    private JButton backButton;

    private JButton createButton;
    private JButton removeButton;
    private JButton editButton;
    private JButton statisticsButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public WorkshopsPanel(Account account) {
        this.account = account;
        this.themes = new ArrayList<>();

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JScrollPane treeScrollPane;

        JPanel westPanel;
        JPanel buttonsPanel;
        JPanel southPanel;

        boolean isAdmin;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.accountButton = new AccountButton(this.getAccount());
        this.themesTree = new JTree(this.initTree());

        westPanel = new JPanel(new BorderLayout());
        this.filePanel = new JPanel();

        this.backButton = new JButton("Volver");
        this.statisticsButton = new JButton("Estadisticas");

        this.createButton = new JButton(Tools.getImageIcon("add", 30, 30));
        this.editButton = new JButton(Tools.getImageIcon("edit", 30, 30));
        this.removeButton = new JButton(Tools.getImageIcon("remove", 30, 30));

        treeScrollPane = new JScrollPane(this.themesTree);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        this.themesTree.setCellRenderer(new DefaultTreeCellRenderer() {
            private static final long serialVersionUID = 5756325787925811601L;

            @Override
            public Component getTreeCellRendererComponent(JTree t, Object v, boolean s, boolean e, boolean l, int r, boolean h) {
                super.getTreeCellRendererComponent(t, v, s, e, l, r, h);

                Settings settings = Settings.getCurrentSettings();
                setFont(settings.getFont());

                String theme = settings.getTheme();
                if (theme.equals(Settings.LIGHT_THEME)) {
                    setForeground(Color.black);
                } else if (theme.equals(Settings.DARK_THEME)) {
                    setForeground(Color.white);
                }
                return this;
            }
        });

        this.createButton.setBorder(null);
        this.editButton.setBorder(null);
        this.removeButton.setBorder(null);

        isAdmin = this.getAccount() instanceof AdminAccount;
        this.createButton.setVisible(isAdmin);
        this.editButton.setVisible(isAdmin);
        this.removeButton.setVisible(isAdmin);

        // ---------------------------------------------------------------------
        buttonsPanel.add(this.createButton);
        buttonsPanel.add(this.editButton);
        buttonsPanel.add(this.removeButton);

        westPanel.add(this.accountButton, BorderLayout.NORTH);
        westPanel.add(treeScrollPane, BorderLayout.CENTER);
        westPanel.add(buttonsPanel, BorderLayout.SOUTH);

        southPanel.add(this.statisticsButton);
        southPanel.add(this.backButton);

        this.add(westPanel, BorderLayout.WEST);
        this.add(this.filePanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.accountButton.addActionListener(ae -> {
            this.setAccountAction();
        });

        this.themesTree.addTreeSelectionListener((TreeSelectionEvent tse) -> {
            this.showTip(tse);
        });

        this.createButton.addActionListener(ae -> {
            this.createTheme();
        });

        this.removeButton.addActionListener(ae -> {
            this.removeTheme();
        });

        this.editButton.addActionListener(ae -> {
            this.editTheme();
        });

        this.statisticsButton.addActionListener(ae -> {
            new ThemesStatisticsDialog(this.getThemes()).showDialog();
        });
    }

    /* ______________________________________________________________________ */
    private DefaultTreeModel initTree() {
        DefaultMutableTreeNode root;
        DefaultTreeModel defaultTreeModel;

        String themesDirectoryPath;
        File themesDirectory;

        // ---------------------------------------------------------------------
        themesDirectoryPath = "docs";
        File docs = new File(themesDirectoryPath);

        if (!docs.exists()) {
            docs.mkdir();
        }

        themesDirectory = new File(themesDirectoryPath);

        // ---------------------------------------------------------------------
        root = new DefaultMutableTreeNode("Documentos");
        defaultTreeModel = new DefaultTreeModel(root);

        // ---------------------------------------------------------------------
        if (themesDirectory.exists()) {
            for (File listFile : themesDirectory.listFiles()) {
                root.add(this.initFiles(listFile.getAbsolutePath()));
            }
        }
        System.out.println("Tthemes");
        System.out.println(this.getThemes());

        return defaultTreeModel;
    }

    /* ______________________________________________________________________ */
    private DefaultMutableTreeNode initFiles(String path) {
        File file = new File(path);
        DefaultMutableTreeNode defaultMutableTreeNode = null;
        DefaultMutableTreeNode child = null;
        Theme theme;

        if (file.isDirectory()) {
            defaultMutableTreeNode = new DefaultMutableTreeNode(file.getName());

            for (File listFile : file.listFiles()) {
                child = this.initFiles(listFile.getAbsolutePath());

                if (listFile.getName().contains(".dat")) {
                    theme = (Theme) new BinaryFileManager(
                            listFile.getAbsolutePath()
                    ).read().get(0);

                    if (theme instanceof Theme) {
                        this.getThemes().add(theme);
                    }
                }

                if (child != null) {
                    defaultMutableTreeNode.add(child);
                }
            }
        }

        return defaultMutableTreeNode;
    }

    /* ______________________________________________________________________ */
    private void setAccountAction() {
        String themeTitle;
        String tipTitle;
        boolean isAdmin;

        this.accountButton.accountAction();
        this.setAccount(this.accountButton.getAccount());

        if (this.filePanel instanceof FilePanel) {

            themeTitle = ((FilePanel) this.filePanel).getTheme().getTitle();
            tipTitle = ((FilePanel) this.filePanel).getTip().getTitle();

            this.showTip(themeTitle, tipTitle);
            System.out.println(themeTitle + ", " + tipTitle);
        }

        isAdmin = this.getAccount() instanceof AdminAccount;
        this.createButton.setVisible(isAdmin);
        this.editButton.setVisible(isAdmin);
        this.removeButton.setVisible(isAdmin);
    }

    /* ______________________________________________________________________ */
    private void showTip(TreeSelectionEvent tse) {
        TreePath treePath = tse.getNewLeadSelectionPath();
        if (treePath != null) {

            Object[] path = treePath.getPath();
            if (path != null) {
                System.out.println(Arrays.toString(path));

                try {
                    this.showTip(
                            path[path.length - 2] + "",
                            path[path.length - 1] + ""
                    );
                } catch (Exception e) {
                    System.out.println(e + "");
                }
            }
        }
    }

    /* ______________________________________________________________________ */
    private void showTip(String themeTitle, String tipTitle) {
        Theme theme = this.searchTheme(themeTitle);
        Theme tip = this.searchTheme(tipTitle);

        if (theme != null && tip != null) {
            if (tip instanceof Tip) {

                this.remove(this.filePanel);
                this.filePanel = new FilePanel(theme, (Tip) tip);

                this.add(this.filePanel, BorderLayout.CENTER);
                this.filePanel.updateUI();

                if (this.getAccount() instanceof UserAccount) {
                    ArrayList<String> viewedThemes;
                    viewedThemes = ((UserAccount) this.getAccount()).getViewedThemes();

                    if (!viewedThemes.contains(theme.getTitle())) {
                        viewedThemes.add(theme.getTitle());
                    }
                }
            }
        }
    }

    /* ______________________________________________________________________ */
    private Theme searchTheme(String nameTheme) {
        Theme theme = new Theme("Documentos", "");
        theme.setFiles(this.getThemes());
        return this.searchTheme(theme, nameTheme);
    }

    /* ______________________________________________________________________ */
    private Theme searchTheme(Theme theme, String nameTheme) {
        Theme _theme;

        for (Theme subTheme : theme.getFiles()) {
            if (subTheme.getTitle().equals(nameTheme)) {
                return subTheme;

            } else {
                _theme = this.searchTheme(subTheme, nameTheme);
                if (_theme != null) {
                    return _theme;
                }
            }
        }
        return null;
    }

    /* ______________________________________________________________________ */
    private void createTheme() {
        CreateThemeDialog createThemeDialog = new CreateThemeDialog(new Theme("", ""));
        int result;
        Theme theme;

        result = createThemeDialog.showDialog();
        if (result != DialogPane.OK_OPTION) {
            return;
        }

        theme = createThemeDialog.getTheme();
        if (theme == null) {
            return;
        }

        try {
            this.createTheme("docs", theme);
            this.getThemes().add(theme);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /* ______________________________________________________________________ */
    private void createTheme(String path, Theme theme) throws IOException {
        File directory;
        PlainFileManager plainFileManager;
        File imageFile;
        File descriptionFile;
        BinaryFileManager binaryFileManager;

        String image = theme.getImage();
        String title = theme.getTitle();
        String description = theme.getDescription();
        ArrayList<Theme> files = theme.getFiles();
        ArrayList<Pair<Integer, Integer>> accounts = theme.getAccounts();

        // Carpeta documentos --------------------------------------------------
        File docs = new File(path);
        if (!docs.exists()) {
            docs.mkdir();
        }

        // Crear carpeta -------------------------------------------------------
        directory = new File(docs.getAbsolutePath() + "/" + title);
        directory.mkdir();
        System.out.println("path " + path);
        System.out.println("theme path " + directory.getAbsolutePath());

        // Mover imagen --------------------------------------------------------
        imageFile = new File(image);

        Files.copy(
                Paths.get(image),
                Paths.get(directory.getAbsolutePath() + "/" + imageFile.getName()),
                StandardCopyOption.REPLACE_EXISTING
        );
        theme.setImage(directory.getAbsolutePath() + "/" + imageFile.getName());

        // Escribir descripcion ------------------------------------------------
        descriptionFile = new File(directory.getAbsoluteFile() + "/descripcion.txt");
        descriptionFile.createNewFile();

        plainFileManager = new PlainFileManager(descriptionFile.getAbsolutePath());
        plainFileManager.write(description);

        // Crear archivos ------------------------------------------------------
        System.out.println(files);
        for (Theme file : files) {
            if (file instanceof Tip) {
                System.out.println("i1 " + file.getImage());
                this.createTip(directory.getAbsolutePath(), (Tip) file);
                System.out.println("i2 " + file.getImage());
            } else {
                this.createTheme(directory.getAbsolutePath(), file);
            }
        }

        // Crear archivo tema --------------------------------------------------
        binaryFileManager = new BinaryFileManager(
                directory.getAbsolutePath() + "/" + title + ".dat"
        );
        binaryFileManager.write(theme);
    }

    /* ______________________________________________________________________ */
    private void createTip(String path, Tip tip) throws IOException {
        File directory;
        PlainFileManager plainFileManager;
        File imageFile;
        File tipFile;

        String image = tip.getImage();
        String title = tip.getTitle();
        String content = tip.getDescription();

        // Crear carpeta
        directory = new File(path + "/" + title);
        directory.mkdir();

        // Mover Imagen
        imageFile = new File(image);
        Files.copy(
                Paths.get(image),
                Paths.get(directory.getAbsolutePath() + "/" + imageFile.getName()),
                StandardCopyOption.REPLACE_EXISTING
        );
        tip.setImage(directory.getAbsolutePath() + "/" + imageFile.getName());

        // Escribir archivo
        tipFile = new File(directory.getAbsolutePath() + "/" + title + ".txt");
        tipFile.createNewFile();

        plainFileManager = new PlainFileManager(tipFile.getAbsolutePath());
        plainFileManager.write(content);
    }

    /* ______________________________________________________________________ */
    private void removeTheme() {
        System.out.println(Arrays.toString(this.themesTree.getSelectionPath().getPath()));
    }

    /* ______________________________________________________________________ */
    private void editTheme() {
        System.out.println(Arrays.toString(this.themesTree.getSelectionPath().getPath()));
    }

    /* GETTERS ______________________________________________________________ */
    public Account getAccount() {
        return this.account;
    }

    /* ______________________________________________________________________ */
    public ArrayList<Theme> getThemes() {
        return this.themes;
    }

    /* ______________________________________________________________________ */
    @Override
    public JButton getCloseButton() {
        return this.backButton;
    }

    /* SETTERS ______________________________________________________________ */
    public void setAccount(Account account) {
        this.account = account;
    }

    /* ______________________________________________________________________ */
    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    /*  MAIN ________________________________________________________________ */
    public static void main(String[] args) {
        new WorkshopsPanel((Account) new BinaryFileManager(
                Settings.ACCOUNTS_PATH_FILE
        ).read().get(0)).showTestDialog();
    }
}
