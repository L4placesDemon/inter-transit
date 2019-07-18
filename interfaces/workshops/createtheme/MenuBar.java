package interfaces.workshops.createtheme;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = -1443740280797494141L;
	
	private JMenuItem newItem;
    private JMenuItem finishItem;

//    private JMenuItem undoItem;
//    private JMenuItem redoItem;
//    private JMenuItem copyItem;
//    private JMenuItem cutItem;
//    private JMenuItem pasteItem;
//    private JMenuItem selectAllItem;
//    private JMenuItem findItem;
//
//    private JMenuItem fontItem;
//    private JMenuItem marginSizeItem;
//    private JCheckBoxMenuItem warpTextItem;
//    private JCheckBoxMenuItem showStatusBarItem;
//    private JMenuItem closeFindBarItem;

    public MenuBar() {
        this.initComponents();
    }

    private void initComponents() {

        // Menus ---------------------------------------------------------------
        JMenu fileMenu = new JMenu("Archivo");
        JMenu editMenu = new JMenu("Editar");
        JMenu viewMenu = new JMenu("Vista");

        // Menu Items ----------------------------------------------------------
        // File ----------------------------------------------------------------
        newItem = new JMenuItem("Nuevo");
        finishItem = new JMenuItem("Finalizar");
        
//        newItem = new MenuItem("Nuevo Tip", 'N', 'N', "new.png");
//        closeItem = new MenuItem("Eliminar Tip", 'C', 'W', "closered.png");
//        closeAllItem = new MenuItem("Eliminar Todos", 'C', 'W', ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
//        finishItem = new MenuItem("Finalizar", 'E', java.awt.event.KeyEvent.VK_F4, ActionEvent.ALT_MASK);

        // Edit ----------------------------------------------------------------
//        undoItem = new MenuItem("Deshacer", 'U', 'Z', "/images/undo.png");
//        redoItem = new MenuItem("Rehacer", 'R', 'Y', "/images/redo.png");
//        cutItem = new MenuItem("Cortar", 'C', 'X', "/images/cut.png");
//        copyItem = new MenuItem("Copiar", 'C', 'C', "/images/copy.png");
//        pasteItem = new MenuItem("Pegar", 'P', 'V', "/images/paste.png");
//        selectAllItem = new MenuItem("Seleccionar Todo", 'S', 'A', "/images/select.png");
//        findItem = new MenuItem("Buscar", 'F', 'F', "/images/find.png");
        // View ----------------------------------------------------------------
//        fontItem = new MenuItem("Font", 'F', "/images/font.png");
//        marginSizeItem = new MenuItem("Margin Size", 'B', "/images/marginSize.png");
//        warpTextItem = new CheckBoxMenuItem("WarpText", true, 'W');
//        showStatusBarItem = new CheckBoxMenuItem("StatusBar", true, 'T');
//        closeFindBarItem = new MenuItem("Close Find Bar", 'C', KeyEvent.VK_ESCAPE, 0);
        // Set Up Components ---------------------------------------------------
        // File Menu -----------------------------------------------------------
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(newItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(new JSeparator());
        fileMenu.add(finishItem);

        // Edit Menu -----------------------------------------------------------
        editMenu.setMnemonic(KeyEvent.VK_E);
//        editMenu.add(undoItem);
//        editMenu.add(redoItem);
//        editMenu.addSeparator();
//        editMenu.add(copyItem);
//        editMenu.add(cutItem);
//        editMenu.add(pasteItem);
//        editMenu.addSeparator();
//        editMenu.add(selectAllItem);
//        editMenu.addSeparator();
//        editMenu.add(findItem);

        // View Menu -----------------------------------------------------------
        viewMenu.setMnemonic(KeyEvent.VK_V);
//        viewMenu.add(fontItem);
//        viewMenu.add(marginSizeItem);
//        viewMenu.addSeparator();
//        viewMenu.add(warpTextItem);
//        viewMenu.add(showStatusBarItem);
//        viewMenu.addSeparator();
//        viewMenu.add(closeFindBarItem);

        // Menu Bar ------------------------------------------------------------
        add(fileMenu);
        add(editMenu);
        add(viewMenu);
    }

    /* ______________________________________________________________________ */
    public JMenuItem getNewItem() {
        return newItem;
    }

    /* ______________________________________________________________________ */
    public JMenuItem getFinishItem() {
        return finishItem;
    }

    /* ______________________________________________________________________ */
//    public MenuItem getUndoItem() {
//        return undoItem;
//    }
    /* ______________________________________________________________________ */
//    public MenuItem getRedoItem() {
//        return redoItem;
//    }
//
//    public MenuItem getCopyItem() {
//        return copyItem;
//    }
    /* ______________________________________________________________________ */
//    public MenuItem getCutItem() {
//        return cutItem;
//    }
    /* ______________________________________________________________________ */
//    public MenuItem getPasteItem() {
//        return pasteItem;
//    }
    /* ______________________________________________________________________ */
//    public MenuItem getSelectAllItem() {
//        return selectAllItem;
//    }
//
//    public MenuItem getFindItem() {
//        return findItem;
//    }
    /* ______________________________________________________________________ */
//    public MenuItem getFontItem() {
//        return fontItem;
//    }
    /* ______________________________________________________________________ */
//    public MenuItem getMarginSizeItem() {
//        return marginSizeItem;
//    }
    /* ______________________________________________________________________ */
//    public CheckBoxMenuItem getWarpTextItem() {
//        return warpTextItem;
//    }
    /* ______________________________________________________________________ */
//    public CheckBoxMenuItem getShowStatusBarItem() {
//        return showStatusBarItem;
//    }
    /* ______________________________________________________________________ */
//    public MenuItem getCloseFindBarItem() {
//        return closeFindBarItem;
//    }
}
