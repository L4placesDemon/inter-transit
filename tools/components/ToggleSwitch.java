package tools.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ToggleSwitch extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private boolean activated;

    private Color inactiveColor;
    private Color buttonColor;
    private Color activeColor;

    /* CONSTRUCTOR __________________________________________________________ */
    public ToggleSwitch(boolean activated, Color inactiveColor, Color activeColor, Color buttonColor) {
        super();
        this.activated = activated;
        this.inactiveColor = inactiveColor;
        this.activeColor = activeColor;
        this.buttonColor = buttonColor;

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
        BufferedImage puffer = null;
        Graphics2D g = null;

        if (g == null) {
            puffer = (BufferedImage) createImage(getWidth(), getHeight());
            g = (Graphics2D) puffer.getGraphics();
            RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHints(rh);
        }
        g.setColor(this.isActivated() ? getActiveColor() : getInactiveColor());
        g.fillRoundRect(0, 0, 40, 20, 5, 5);

        g.setColor(Color.black);
        g.drawRoundRect(0, 0, 40, 20, 5, 5);

        g.setColor(this.getButtonColor());
        if (activated) {
            g.fillRoundRect(21, 1, 18, 18, 5, 5);
            g.setColor(Color.black);
            g.drawRoundRect(20, 0, 20, 20, 5, 5);
        } else {
            g.fillRoundRect(1, 1, 18, 18, 5, 5);
            g.setColor(Color.black);
            g.drawRoundRect(0, 0, 20, 20, 5, 5);
        }

        gr.drawImage(puffer, 0, 0, null);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                setActivated(!isActivated());
                repaint();
            }
        });
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
}
