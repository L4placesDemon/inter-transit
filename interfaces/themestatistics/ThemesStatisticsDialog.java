package interfaces.themestatistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.components.Dialog;
import worldclasses.themes.Theme;

public class ThemesStatisticsDialog extends Dialog {

    /* ATRRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -3222881567483637962L;

    private ArrayList<Theme> themes;

    private Pie viewsPie;
    private PieInformation viewsInfo;

    private JButton backButton;
    private JButton reloadButton;

    /* CONSRUCTORS __________________________________________________________ */
    public ThemesStatisticsDialog(ArrayList<Theme> themes) {
        this.themes = themes;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        ArrayList<Integer> views;

        JPanel labelsPanel;
        JPanel themesPanel;

        JPanel viewsPanel;

        JPanel centerPanel;
        JPanel westPanel;
        JPanel southPanel;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(300, 200));
        this.setTitle("Inter Transit");

        // Set up Components ---------------------------------------------------
        this.viewsPie = new Pie();
        this.viewsInfo = new PieInformation();

        this.backButton = new JButton("Volver");
        this.reloadButton = new JButton("Recargar");

        views = new ArrayList<>();

        labelsPanel = new JPanel(new GridLayout(1, 3));
        themesPanel = new JPanel();

        viewsPanel = new JPanel(new BorderLayout());

        centerPanel = new JPanel(new GridLayout());
        westPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
//        this.setThemes(this.initTheme(Tools.getResource("/docs")).getFiles());

        themesPanel.setLayout(new BoxLayout(themesPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < this.getThemes().size(); i++) {
            Theme theme = this.getThemes().get(i);
            Integer view = theme.getViews();
            Color color = this.viewsPie.addValue(view);
            views.add(view);
            this.viewsInfo.add(theme.getTitle(), color);
        }

        // ---------------------------------------------------------------------
        labelsPanel.add(new JLabel("Imagen", JLabel.CENTER));
        labelsPanel.add(new JLabel("Titulo", JLabel.CENTER));
        labelsPanel.add(new JLabel("Vistas", JLabel.CENTER));

        this.getThemes().forEach(i -> {
            themesPanel.add(new ThemeButton(i));
        });

        westPanel.add(labelsPanel, BorderLayout.NORTH);
        westPanel.add(themesPanel, BorderLayout.CENTER);

        viewsPanel.add(new JLabel("Vistas", JLabel.CENTER), BorderLayout.NORTH);
        viewsPanel.add(viewsPie, BorderLayout.CENTER);
        viewsPanel.add(viewsInfo, BorderLayout.EAST);

        centerPanel.add(viewsPanel);
        southPanel.add(this.backButton);
        southPanel.add(this.reloadButton);

        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.backButton.addActionListener(ae -> {
            this.dispose();
        });

        this.reloadButton.addActionListener(ae -> {
//            ArrayList<String> names = new ArrayList<>();
//            ArrayList<Color> colors = new ArrayList<>();
//
//            this.viewsPie.reload();
//
//            for (int i = 0; i < getThemes().size(); i++) {
//                Theme theme = getThemes().get(i);
//                names.add(theme.getTitle());
//                colors.add(this.viewsPie.getColors().get(i));
//            }
//
//            this.viewsInfo.setNames(names);
//            this.viewsInfo.setColors(colors);
//            this.viewsInfo.repaint();
//            this.viewsInfo.updateUI();
        });
    }

    /* ______________________________________________________________________ */
//    private Theme initTheme(String path) {
//        File file = new File(path);
//        Object[] themeData;
//        Theme theme;
//
//        try {
//            themeData = this.getThemeData(path + "/descripcion.txt");
//            theme = new Theme(
//                    null,
//                    file.getName(),
//                    themeData[0] + "",
//                    Double.parseDouble(themeData[1] + ""),
//                    Integer.parseInt(themeData[2] + ""),
//                    new ArrayList<>()
//            );
//        } catch (FileNotFoundException | NumberFormatException e) {
//            if (file.isDirectory()) {
//                theme = new Theme(file.getName(), "");
//            } else {
//                theme = new Tip(file.getName(), new PlainFileManager(file).read());
//            }
//        }
//
//        if (file.isDirectory()) {
//            for (File listFile : file.listFiles()) {
//                if (!listFile.getName().equals("descripcion.txt")) {
//                    theme.getFiles().add(this.initTheme(
//                            listFile.getAbsolutePath()
//                    ));
//                }
//            }
//        }
//        return theme;
//    }

    /* ______________________________________________________________________ */
//    private Object[] getThemeData(String themeDirectoryPath) throws FileNotFoundException {
//        File descriptionFile = new File(themeDirectoryPath);
//
//        if (descriptionFile.exists()) {
//            String text = new PlainFileManager(descriptionFile).read();
//
//            int start = text.indexOf('=') + 1;
//            int end = text.indexOf('\n');
//            String description = text.substring(start, end);
//
//            start = text.indexOf('=', start) + 1;
//            end = text.indexOf('\n', end + 1);
//            String value = text.substring(start, end);
//
//            start = text.indexOf('=', start) + 1;
//            end = text.indexOf('\n', end + 1);
//            String views = text.substring(start, end);
//
//            return new Object[]{
//                description,
//                Double.parseDouble(value),
//                Integer.parseInt(views)
//            };
//        } else {
//            System.out.println("description file do not exists");
//            throw new FileNotFoundException("description file do no exists");
//        }
//    }

    /* GETTERS ______________________________________________________________ */
    public ArrayList<Theme> getThemes() {
        return this.themes;
    }

    /* SETTERS ______________________________________________________________ */
    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
//        new ThemesStatisticsDialog().showTestDialog();
    }
}
