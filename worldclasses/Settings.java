package worldclasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import tools.Pair;
import tools.filemanager.BinaryFileManager;

public class Settings implements Serializable, Cloneable {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -8572381626825274780L;

    public static final String ACCOUNTS_PATH_FILE = "accounts.dat";
    public static final String SETTINGS_PATH_FILE = "settings.dat";

    public static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);

    public static final String LIGHT_LOGO = "logos/light-logo";
    public static final String DARK_LOGO = "logos/dark-logo";

    public static final String LIGHT_THEME = "light-theme";
    public static final String DARK_THEME = "dark-theme";

    private String theme;
    private Font font;
    private Pair<Dimension, Integer> size;

    /* CONSTRUCTORS _________________________________________________________ */
    public Settings(String theme, Font font, Pair<Dimension, Integer> size) {
        this.theme = theme;
        this.font = font;
        this.size = size;
    }

    /* ______________________________________________________________________ */
    public Settings(String theme, Font font) {
        this(theme, font, new Pair<>(
                new Dimension(1000, 700), JFrame.MAXIMIZED_BOTH
        ));
    }

    /* ______________________________________________________________________ */
    public Settings() {
        this(Settings.LIGHT_THEME, Settings.DEFAULT_FONT);
    }

    /* METHODS ______________________________________________________________ */
    private static void theme(Color primary, Color secondary, Color select,
            Color foreground, Border border) {
        Font font = Settings.getCurrentSettings().getFont();

        UIManager.put("OptionPane.background", primary);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.messageForeground", foreground);

        UIManager.put("Panel.background", primary);

        UIManager.put("ToggleButton.background", secondary);
        UIManager.put("ToggleButton.focus", new Color(0, 0, 0, 0));
        UIManager.put("ToggleButton.select", select);

        UIManager.put("Label.font", font);
        UIManager.put("Label.foreground", foreground);

        UIManager.put("Button.background", primary);
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("Button.font", font);
        UIManager.put("Button.foreground", foreground);
        UIManager.put("Button.select", select);

        UIManager.put("RadioButton.background", primary);
        UIManager.put("RadioButton.font", font);
        UIManager.put("RadioButton.foreground", foreground);

        UIManager.put("ScrollBar.thumbHighlight", secondary);
        UIManager.put("ScrollBar.thumbLightHighlight", secondary);
        UIManager.put("ScrollBar.thumbDarkHighlight", secondary);
        UIManager.put("ScrollBar.thumb", secondary);
        UIManager.put("ScrollBar.track", secondary);
        UIManager.put("ScrollBar.trackHighlight", secondary);
        UIManager.put("ScrollBar.darkShadow", secondary);
        UIManager.put("ScrollBar.foreground", secondary);
        UIManager.put("ScrollBar.highlight", secondary);
        UIManager.put("ScrollBar.shadow", secondary);
        UIManager.put("ScrollBar.thumbDarkShadow", secondary);
        UIManager.put("ScrollBar.thumbShadow", secondary);
        UIManager.put("ScrollBar.background", primary);

        UIManager.put("ScrollPane.background", primary);

        UIManager.put("TextField.caretForeground", foreground);
        UIManager.put("TextField.background", secondary);
        UIManager.put("TextField.border", border);
        UIManager.put("TextField.font", font);
        UIManager.put("TextField.foreground", foreground);

        UIManager.put("TextArea.caretForeground", foreground);
        UIManager.put("TextArea.background", secondary);
        UIManager.put("TextArea.border", border);
        UIManager.put("TextArea.font", font);
        UIManager.put("TextArea.foreground", foreground);

        UIManager.put("PasswordField.caretForeground", foreground);
        UIManager.put("PasswordField.border", border);
        UIManager.put("PasswordField.background", secondary);
        UIManager.put("PasswordField.font", font);
        UIManager.put("PasswordField.foreground", foreground);

        UIManager.put("Tree.background", secondary);
        UIManager.put("Tree.foreground", foreground);
        UIManager.put("Tree.font", font);
        UIManager.put("Tree.rendererFillBackground", false);

        UIManager.put("List.background", secondary);
        UIManager.put("List.foreground", foreground);

        UIManager.put("MenuBar.background", primary);
        UIManager.put("MenuBar.border", border);

        UIManager.put("Menu.foreground", foreground);
        UIManager.put("Menu.font", font);

        UIManager.put("PopupMenu.border", border);

        UIManager.put("MenuItem.background", primary);
        UIManager.put("MenuItem.border", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("MenuItem.foreground", foreground);
        UIManager.put("MenuItem.font", font);

        UIManager.put("Separator.background", primary);
        UIManager.put("Separator.foreground", Color.gray);

        UIManager.put("TabbedPane.background", primary);
//        UIManager.put("TabbedPane.selected", select);
//        UIManager.put("TabbedPane.light", primary);
//        UIManager.put("TabbedPane.highlight", primary);
//        UIManager.put("TabbedPane.shadow", primary);
//        UIManager.put("TabbedPane.darkShadow", primary);
//        
//        UIManager.put("TabbedPane.foreground", primary);
//        UIManager.put("TabbedPane.focus", primary);
//        UIManager.put("TabbedPane.textIconGap", primary);
//        UIManager.put("TabbedPane.tabInsets", primary);
//        UIManager.put("TabbedPane.selectedTabPadInsets", primary);
//        UIManager.put("TabbedPane.tabAreaInsets", primary);
//        UIManager.put("TabbedPane.contentBorderInsets", primary);
//        UIManager.put("TabbedPane.tabRunOverlay", primary);
//        UIManager.put("TabbedPane.tabRunOverlay", primary);
    }

    /* ______________________________________________________________________ */
    public static void lightTheme() {
        Settings.theme(Color.white, Color.white, null, Color.black, null);
    }

    /* ______________________________________________________________________ */
    public static void darkTheme() {
        Settings.theme(
                new Color(45, 45, 45),
                new Color(60, 60, 60),
                new Color(24, 136, 255),
                Color.white,
                new EtchedBorder(Color.black, Color.gray)
        );
    }

    /* ______________________________________________________________________ */
    public static Settings getCurrentSettings() {
        BinaryFileManager manager = new BinaryFileManager(Settings.SETTINGS_PATH_FILE);
        if (manager.read().isEmpty()) {
            manager.write(new Settings());
        }
        return (Settings) manager.read().get(0);
    }

    /* ______________________________________________________________________ */
    @Override
    public String toString() {
        return "Settings{" + "theme=" + theme + ", font=" + font + ", size=" + size + '}';
    }

    /* ______________________________________________________________________ */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Settings) super.clone();
    }

    /* GETTERS ______________________________________________________________ */
    public String getTheme() {
        return this.theme;
    }

    /* ______________________________________________________________________ */
    public Pair<Dimension, Integer> getSize() {
        return this.size;
    }

    /* ______________________________________________________________________ */
    public Font getFont() {
        return this.font;
    }

    /* SETTERS ______________________________________________________________ */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /* ______________________________________________________________________ */
    public void setSize(Pair<Dimension, Integer> size) {
        this.size = size;
    }

    /* ______________________________________________________________________ */
    public void setFont(Font font) {
        this.font = font;
    }
}
