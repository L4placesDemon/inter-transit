package interfaces.createtheme;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import tools.Tools;
import tools.components.Dialog;
import tools.components.DialogPane;
import tools.components.TextArea;
import tools.components.TextField;

import worldclasses.themes.Theme;

public class CreateThemeDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private Theme theme;

    private MenuBar menuBar;

    private JLabel themeImageLabel;
    private JButton setThemeImageButton;

    private TextField themeTitleField;
    private TextField themeValueField;
    private TextArea themeDescriptionArea;

    private JTabbedPane tabbedPane;
    private ArrayList<TipEditor> tipEditors;

    private JButton finishButton;
    private JButton cancelButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public CreateThemeDialog() {
        tipEditors = new ArrayList<>();

        this.initComponents();
        this.initEvents();
    }

    /* ______________________________________________________________________ */
    private void initComponents() {
        JPanel westPanel;
        JPanel themePanel;
        JPanel northPanel;
        JPanel buttonsPanel;

        JScrollPane scrollPane;

        // Set up Dialog -------------------------------------------------------
        this.setLayout(new BorderLayout());
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("Crear Tema");

        // Set up Components ---------------------------------------------------
        this.menuBar = new MenuBar();
        this.setJMenuBar(this.menuBar);

        this.themeImageLabel = new JLabel();
        this.setThemeImageButton = new JButton("Elegir");
        this.themeValueField = new TextField();

        this.themeTitleField = new TextField();
        this.themeDescriptionArea = new TextArea();

        this.tabbedPane = new JTabbedPane();

        this.cancelButton = new JButton("Cancelar");
        this.finishButton = new JButton("Finalizar");

        themePanel = new JPanel(new BorderLayout());
        northPanel = new JPanel(new BorderLayout());
        westPanel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane(this.themeDescriptionArea);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        this.themeImageLabel.setPreferredSize(new Dimension(120, 110));

        this.themeTitleField.setHint("Titulo del Tema");
        this.themeValueField.setHint("Valor del Tema");

        scrollPane.getVerticalScrollBar().setUnitIncrement(7);

        // ---------------------------------------------------------------------
        northPanel.add(this.themeTitleField, BorderLayout.CENTER);
        northPanel.add(this.themeValueField, BorderLayout.EAST);

        westPanel.add(this.themeImageLabel, BorderLayout.CENTER);
        westPanel.add(this.setThemeImageButton, BorderLayout.SOUTH);

        themePanel.add(northPanel, BorderLayout.NORTH);
        themePanel.add(westPanel, BorderLayout.WEST);
        themePanel.add(scrollPane, BorderLayout.CENTER);

        buttonsPanel.add(this.cancelButton);
        buttonsPanel.add(this.finishButton);

        this.add(themePanel, BorderLayout.NORTH);
        this.add(this.tabbedPane, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        this.menuBar.getNewItem().addActionListener(ae -> {
            this.addNewTab();
        });
    }

    /* ______________________________________________________________________ */
    public TipEditor addNewTab() {
        Integer index;
        TitleTab titleTab;
        TipEditor tipEditor;
        String name;

        name = DialogPane.input("Nuevo Tip", "Nombre del nuevo Tip:");
        if (name == null || name.isEmpty()) {
            name = "nuevo tip " + generateTabNumber();
        }

        titleTab = new TitleTab(name);
        tipEditor = new TipEditor();

        this.tabbedPane.addTab(name, tipEditor);
        index = this.tabbedPane.indexOfComponent(tipEditor);
        this.tabbedPane.setTabComponentAt(index, titleTab);
        this.tabbedPane.setSelectedIndex(index);

        this.tipEditors.add(tipEditor);

//        updateMenuBar();
//        updateStatusBar();
//        updatePopupMenus();
        return tipEditor;
    }

    /* ______________________________________________________________________ */
    public int generateTabNumber() {
        int newTabNumber = 1;
        Integer tabCount = this.tabbedPane.getTabCount();
        String tabTitle;
        Boolean exists;

        do {
            exists = false;
            for (int i = 0; i < tabCount; i++) {
                tabTitle = this.tabbedPane.getTitleAt(i);
                if (tabTitle.equals("nuevo tip " + newTabNumber)) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                newTabNumber++;
            }
        } while (exists);

        return newTabNumber;
    }

    /* ______________________________________________________________________ */
    public void closeTab(TipEditor tipEditor) {
        String fileName;
        Integer option;

//        if (!tipEditor.isClosable()) {
            fileName = tipEditor.getName();

            option = DialogPane.yesNoCancelOption("Save file before close",
                    "Save " + fileName + "?");

            if (option == DialogPane.OK_OPTION) {
//                tipEditor.saveTools();

                Tools.output(fileName + " saved succesfully");
                close(tipEditor);
            } else if (option == DialogPane.NO_OPTION) {

                Tools.output(fileName + " file didn't be saved");
                close(tipEditor);
            }
//        } else {
//            close(scrollPane);
//        }

//        updateMenuBar();
//        updateStatusBar();
    }

    /* ______________________________________________________________________ */
    public void close(TipEditor tipEditor) {
        this.tabbedPane.remove(tipEditor);
        this.tipEditors.remove(tipEditor);
        Tools.output(tipEditor + " closed");
    }

    /* GETTERS ______________________________________________________________ */
    public Theme getTheme() {
        return this.theme;
    }

    /* SETTERS ______________________________________________________________ */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    /* MAIN _________________________________________________________________ */
    public static void main(String[] args) {
        new CreateThemeDialog().showTestDialog();
    }
}
