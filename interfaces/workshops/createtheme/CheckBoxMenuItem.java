package interfaces.workshops.createtheme;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;

public class CheckBoxMenuItem extends JCheckBox {

	/* ATTRIBUTES ___________________________________________________________ */
	private static final long serialVersionUID = 4333420196270174783L;

	/* CONSTRUCTORS _________________________________________________________ */
    public CheckBoxMenuItem(String text, boolean selected, int mnemonic) {
        setText(text);
        setSelected(selected);
        setFont(new Font("Dialog", Font.PLAIN, 11));
        setForeground(Color.BLACK);
        setMnemonic(mnemonic);
    }
}
