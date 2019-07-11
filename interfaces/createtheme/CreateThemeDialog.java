package interfaces.createtheme;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
        JPanel northPanel;
        JPanel westPanel;
        JPanel themePanel;
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
        this.setThemeImageButton = new JButton("Elegir Imagen");
        this.themeValueField = new TextField();

        this.themeTitleField = new TextField();
        this.themeDescriptionArea = new TextArea();

        this.tabbedPane = new JTabbedPane();

        this.cancelButton = new JButton("Cancelar");
        this.finishButton = new JButton("Finalizar");

        northPanel = new JPanel(new BorderLayout());
        westPanel = new JPanel(new BorderLayout());
        themePanel = new JPanel(new BorderLayout());
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
            this.addNewTabAction();
        });

        this.setThemeImageButton.addActionListener(ae -> {
            this.setImageAction();
        });

        this.finishButton.addActionListener(ae -> {
            this.finish();
            
        });

        this.cancelButton.addActionListener(ae -> {
            dispose();
        });
    }

    /* ______________________________________________________________________ */
    private void addNewTabAction() {
        int option = DialogPane.showOption(
                "Agregar nueva Pestaña",
                "Escoja el tipo de pestaña a agregar",
                DialogPane.DEFAULT_OPTION,
                DialogPane.QUESTION_MESSAGE,
                new String[]{"Tema", "Tip"},
                "Tip"
        );

        if (option == 0) {
        } else if (option == 1) {
            addNewTipAction();
        }
    }

    /* ______________________________________________________________________ */
    private void addNewTipAction() {
        int index;
        TitleTab titleTab;
        TipEditor tipEditor;
        String name;

        name = DialogPane.showInput("Nuevo Tip", "Nombre del nuevo Tip:");

        if (name == null) {
            return;
        }

        if (name.isEmpty()) {
            name = "nuevo tip " + generateTabNumber();
        }
        titleTab = new TitleTab(name);
        tipEditor = new TipEditor(name);

        this.tabbedPane.addTab(name, tipEditor);
        System.out.println("naname " + tipEditor.getName());
        tipEditor.setName(name);
        tipEditor.getTipTitleField().requestFocus();
        index = this.tabbedPane.indexOfComponent(tipEditor);
        this.tabbedPane.setTabComponentAt(index, titleTab);
        this.tabbedPane.setSelectedIndex(index);

        this.tipEditors.add(tipEditor);

        tipEditor.getTipTitleField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String text = tipEditor.getTipTitleField().getText();
                titleTab.setText(text);
            }
        });

//        updateMenuBar();
//        updateStatusBar();
//        updatePopupMenus();
    }

    /* ______________________________________________________________________ */
    private void addNewThemeAction() {

    }

    /* ______________________________________________________________________ */
    private void setImageAction() {
        JFileChooser fileChooser = new JFileChooser();
        int result;
        File image;

        fileChooser.setFileFilter(new FileNameExtensionFilter(
                "JPG, PNG & GIF", "jpg", "png", "gif"
        ));
        result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            image = fileChooser.getSelectedFile();
            this.themeImageLabel.setIcon(Tools.getAbsoluteImageIcon(
                    image.getAbsolutePath(),
                    this.themeImageLabel.getWidth(),
                    this.themeImageLabel.getHeight())
            );
            this.themeImageLabel.setName(image.getAbsolutePath());
        }
    }

    /* ______________________________________________________________________ */
    private void finish() {
        int option = DialogPane.showOption(
                "Crear Tema", "Guardar el tema?", DialogPane.YES_NO_CANCEL_OPTION
        );

        if (option == DialogPane.YES_OPTION) {
            try {
                this.initTheme();
                dispose();
                this.okAction();
            } catch (Exception e) {
                DialogPane.showMessage(
                        "Error al crear un tema",
                        "Ingrese un valor valido para el tema",
                        DialogPane.ERROR_MESSAGE
                );
            }
        } else if (option == DialogPane.NO_OPTION) {
            dispose();
        }
    }

    /* ______________________________________________________________________ */
    private void initTheme() throws NumberFormatException, Exception {
        String image = this.themeImageLabel.getName();
        String title = this.themeTitleField.getText();
        String description = this.themeDescriptionArea.getText();
        Double value = Double.parseDouble(this.themeValueField.getText());
        ArrayList<Theme> files = this.getFiles(this.tabbedPane);

        if (image == null) {
            throw new Exception("No se escogio una imagen para el tema");
        } else if (title.isEmpty()) {
            throw new Exception("No se ingreso un titulo para el tema");
        } else if (description.isEmpty()) {
            throw new Exception("No se ingreso una descripcion para el tema");
        } 

        Theme theme = new Theme(
                image,
                title,
                description,
                value,
                0,
                files
        );

        this.setTheme(theme);

        System.out.println(theme);
    }

    /* ______________________________________________________________________ */
    private ArrayList<Theme> getFiles(JComponent root) {
        ArrayList<Theme> files = new ArrayList<>();

        for (Component component : root.getComponents()) {
            if (component.getName() != null) {
                if (component instanceof TipEditor) {
                    files.add(((TipEditor) component).getTip());
                } else if (component instanceof ThemeEditor) {
                    files.add(((ThemeEditor) component).getTheme());
                }
            }
        }

        return files;
    }

    /* ______________________________________________________________________ */
    private int generateTabNumber() {
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

        option = DialogPane.showOption(
                "Save file before close",
                "Save " + fileName + "?",
                DialogPane.YES_NO_CANCEL_OPTION
        );

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
