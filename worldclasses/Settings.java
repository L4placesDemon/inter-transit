package worldclasses;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import tools.binaryfilemanager.BinaryFileManager;

public class Settings implements Serializable {

    /* ATTRIBUTES ___________________________________________________________ */
    public static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 12);
    public static final String LIGHT_LOGO = "logos/light-logo";
    public static final String DARK_LOGO = "logos/dark-logo";
    public static final String LIGHT_THEME = "light-theme";
    public static final String DARK_THEME = "dark-theme";

    private String theme;
    private Font font;

    /* CONSTRUCTORS _________________________________________________________ */
    public Settings(String theme, Font font) {
        this.theme = theme;
        this.font = font;
    }

    /* ______________________________________________________________________ */
    public Settings() {
        this(Settings.LIGHT_THEME, Settings.DEFAULT_FONT);
    }

    /* METHODS ______________________________________________________________ */
    private void theme(
            Color primary,
            Color secondary,
            Color select,
            Color foreground,
            Border border
    ) {
        Font _font = getFont();

        UIManager.put("OptionPane.background", primary);
        UIManager.put("OptionPane.buttonFont", _font);
        UIManager.put("OptionPane.messageFont", _font);
        UIManager.put("OptionPane.messageForeground", foreground);

        UIManager.put("Panel.background", primary);

        UIManager.put("ToggleButton.background", secondary);
        UIManager.put("ToggleButton.focus", new Color(0, 0, 0, 0));
        UIManager.put("ToggleButton.select", select);

        UIManager.put("Label.font", _font);
        UIManager.put("Label.foreground", foreground);

        UIManager.put("Button.background", primary);
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("Button.font", _font);
        UIManager.put("Button.foreground", foreground);
        UIManager.put("Button.select", select);

        UIManager.put("RadioButton.background", primary);
        UIManager.put("RadioButton.foreground", foreground);
        UIManager.put("RadioButton.font", _font);

        UIManager.put("ScrollBar.background", primary);
        UIManager.put("ScrollPane.background", primary);

        UIManager.put("TextField.caretForeground", foreground);
        UIManager.put("TextField.border", border);
        UIManager.put("TextField.background", secondary);
        UIManager.put("TextField.foreground", foreground);

        UIManager.put("PasswordField.caretForeground", foreground);
        UIManager.put("PasswordField.border", border);
        UIManager.put("PasswordField.background", secondary);
        UIManager.put("PasswordField.foreground", foreground);
    }

    public static void lightTheme() {
        new Settings().theme(Color.white, Color.white, null, Color.black, null);
    }

    /* ______________________________________________________________________ */
    public static void darkTheme() {
        new Settings().theme(
                new Color(45, 45, 45),
                new Color(72, 72, 72),
                new Color(24, 136, 255),
                Color.white,
                new EmptyBorder(0, 0, 0, 0)
        );
    }

    /* ______________________________________________________________________ */
    public static Settings getCurrentSettings() {
        return (Settings) new BinaryFileManager("settings.dat").read().get(0);
    }

    /* ______________________________________________________________________ */
    @Override
    public String toString() {
        return "Settings{" + "theme=" + this.getTheme() + ", font=" + this.getFont() + '}';
    }

    /* GETTERS ______________________________________________________________ */
    public String getTheme() {
        return theme;
    }

    /* ______________________________________________________________________ */
    public Font getFont() {
        return font;
    }

    /* SETTERS ______________________________________________________________ */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /* ______________________________________________________________________ */
    public void setFont(Font font) {
        this.font = font;
    }
}
