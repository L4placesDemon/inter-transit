package interfaces.createtheme;

import javax.swing.JPanel;
import worldclasses.themes.Theme;

public class ThemeEditor extends JPanel {

    private Theme theme;

    public ThemeEditor(Theme theme) {
        this.theme = theme;

        this.initComponents();
        this.initEvents();
    }

    private void initComponents() {

    }

    private void initEvents() {
    }
}
