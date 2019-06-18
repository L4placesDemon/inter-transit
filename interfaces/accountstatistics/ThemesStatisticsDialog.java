package interfaces.accountstatistics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import utilities.Dialog;
import utilities.Utilities;
import static utilities.Utilities.getFileText;
import worldclasses.themes.Theme;

public class ThemesStatisticsDialog extends Dialog {

    /* ATRRIBUTES ___________________________________________________________ */
    private ArrayList<Theme> themes;

    /* CONSRUCTORS __________________________________________________________ */
    public ThemesStatisticsDialog() {
        super(new JFrame(), true);
        this.themes = new ArrayList<>();

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(300, 200));
        this.setTitle("Inter Transit");
    }

    /* ______________________________________________________________________ */
    private void initEvents() {

    }

    /* ______________________________________________________________________ */
    private void initThemes() {
        String path = ThemesStatisticsDialog.class.getResource("/files").toString().substring(5);

        File themeFolders = new File(path);
        File descriptionFile;
        ArrayList<File> files;

        path = path.substring(0, path.indexOf("build")) + "src/files";

        if (themeFolders.exists()) {
            files = new ArrayList<>(Arrays.asList(themeFolders.listFiles()));

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
                } else {
                    System.out.println("description file do not exists");
                }
            }
        }
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new ThemesStatisticsDialog().showDialog();
    }
}
