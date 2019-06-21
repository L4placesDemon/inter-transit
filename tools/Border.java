package tools;

import java.awt.Font;
import javax.swing.border.AbstractBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Border extends CompoundBorder {

    /* CONSTRUCTORS _________________________________________________________ */
    public Border(AbstractBorder outsideBorder, AbstractBorder insideBorder) {
        super(outsideBorder, insideBorder);
    }

    public Border(AbstractBorder outsideBorder, AbstractBorder centerBorder, AbstractBorder insideBorder) {
        super(outsideBorder, new Border(centerBorder, insideBorder));
    }

    /* ______________________________________________________________________ */
    public Border(int top, int left, int bottom, int right) {
        this(new EmptyBorder(top, left, bottom, right), new EtchedBorder());
    }

    /* ______________________________________________________________________ */
    public Border(String string) {
        this.insideBorder = new TitledBorder(string);
        ((TitledBorder) this.insideBorder).setTitleFont(new Font("Dialog", Font.PLAIN, 12));
    }

    /* ______________________________________________________________________ */
    public Border() {
        this(5, 5, 5, 5);
    }
}
