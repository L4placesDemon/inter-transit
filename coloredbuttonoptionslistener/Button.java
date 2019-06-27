package coloredbuttonoptionslistener;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

public class Button extends JComponent {

    private Listener listener;

    private String text;
    private boolean actived;

    private Color background;
    private Color foreground;

    public Button(String text, Color color) {
        this.text = text;
        this.background = color;
        actived = false;

        setUpEvents();
    }

    public void setUpEvents() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));

                listener.enter(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                listener.exit(e);
            }

            @Override
            public void mouseClicked(MouseEvent me) {
                actived = !actived;

                listener.click(me);
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();

        if (actived == true) {
            g.setColor(background);
            g.fillRect(0, 0, w, h);
        }

        g.setColor(Color.black);
        g.drawRect(0, 0, w - 1, h - 1);

        g.setColor(foreground);
        g.setFont(new Font("Dialog", Font.BOLD, 12));
        g.drawString(text, (w / 2) - (7 * text.length()) / 2, h / 2);

    }

    public boolean darState() {
        return actived;
    }

    public void setListener(Listener l) {
        this.listener = l;
    }

    public String darTexto() {
        return text;
    }

    @Override
    public void setForeground(Color fg) {
        this.foreground = fg;
        repaint();
    }
}
