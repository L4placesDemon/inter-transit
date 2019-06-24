package tools.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ToggleSwitch extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private boolean activated;

    private Color inactiveColor;
    private Color buttonColor;
    private Color activeColor;

    private ArrayList<ToggleSwitchListener> toggleSwitchListeners;

    /* CONSTRUCTOR __________________________________________________________ */
    public ToggleSwitch(boolean activated, Color inactiveColor, Color activeColor, Color buttonColor) {
        super();
        this.activated = activated;

        this.inactiveColor = inactiveColor;
        this.activeColor = activeColor;
        this.buttonColor = buttonColor;

        this.toggleSwitchListeners = new ArrayList<>();

        this.initEvents();
    }

    /* ______________________________________________________________________ */
    public ToggleSwitch(boolean activated, Color inactiveColor, Color activeColor) {
        this(activated, inactiveColor, activeColor, Color.white);
    }

    /* ______________________________________________________________________ */
    public ToggleSwitch(boolean activated, Color activeColor) {
        this(activated, Color.lightGray, activeColor);
    }

    /* ______________________________________________________________________ */
    public ToggleSwitch(boolean activated) {
        this(activated, new Color(0, 125, 255));
    }

    /* ______________________________________________________________________ */
    public ToggleSwitch() {
        this(false);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public void paint(Graphics gr) {
        int width = this.getWidth();
        int height = this.getHeight();

        BufferedImage puffer = null;
        Graphics2D g = null;

        super.repaint();

        if (g == null) {
            puffer = (BufferedImage) createImage(getWidth(), getHeight());
            g = (Graphics2D) puffer.getGraphics();
            RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHints(rh);
        }

        // Fill color
        g.setColor(this.isActivated() ? this.getActiveColor() : this.getInactiveColor());
        g.fillRoundRect(width / 2 - 20, height / 2 - 10, 40, 20, 5, 5);

        // Draw Border
        g.setColor(Color.black);
        g.drawRoundRect(width / 2 - 20, height / 2 - 10, 40, 20, 5, 5);

        // Fill Button and Draw Border
        g.setColor(this.getButtonColor());
        if (this.isActivated()) {
            g.fillRoundRect(width / 2, height / 2 - 10, 20, 20, 5, 5);
            g.setColor(Color.black);
            g.drawRoundRect(width / 2, height / 2 - 10, 20, 20, 5, 5);
        } else {
            g.fillRoundRect(width / 2 - 20, height / 2 - 10, 20, 20, 5, 5);
            g.setColor(Color.black);
            g.drawRoundRect(width / 2 - 20, height / 2 - 10, 20, 20, 5, 5);
        }

        gr.drawImage(puffer, 0, 0, null);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                setActivated(!isActivated());

                toggleSwitchListeners.forEach(i -> {
                    if (isActivated()) {
                        i.activate();
                    } else {
                        i.deactivate();
                    }
                });

//                repaint();
            }
        });
    }

    /* ______________________________________________________________________ */
    public void addToggleSwitchListener(ToggleSwitchListener toggleSwitchListeners) {
        this.getToggleSwitchListeners().add(toggleSwitchListeners);
    }

    /* ______________________________________________________________________ */
    public void removeToggleSwitchListener(ToggleSwitchListener toggleSwitchListeners) {
        this.getToggleSwitchListeners().remove(toggleSwitchListeners);
    }

    /* GETTERS ______________________________________________________________ */
    public boolean isActivated() {
        return this.activated;
    }

    /* ______________________________________________________________________ */
    public Color getInactiveColor() {
        return this.inactiveColor;
    }

    /* ______________________________________________________________________ */
    public Color getButtonColor() {
        return this.buttonColor;
    }

    /* ______________________________________________________________________ */
    public Color getActiveColor() {
        return this.activeColor;
    }

    /* ______________________________________________________________________ */
    public ArrayList<ToggleSwitchListener> getToggleSwitchListeners() {
        return this.toggleSwitchListeners;
    }

    /* SETTERS ______________________________________________________________ */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    /* ______________________________________________________________________ */
    public void setInactiveColor(Color inactiveColor) {
        this.inactiveColor = inactiveColor;
    }

    /* ______________________________________________________________________ */
    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }

    /* ______________________________________________________________________ */
    public void setActiveColor(Color activeColor) {
        this.activeColor = activeColor;
    }

    /* ______________________________________________________________________ */
    public void getToggleSwitchListeners(ArrayList<ToggleSwitchListener> toggleSwitchListeners) {
        this.toggleSwitchListeners = toggleSwitchListeners;
    }
}
