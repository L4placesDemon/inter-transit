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
import worldclasses.themes.Tip;

public class CreateThemeDialog extends Dialog {

    /* ATTRIBUTES ___________________________________________________________ */
    private Theme theme;

    private MenuBar menuBar;

    private JLabel themeImageLabel;
    private JButton setThemeImageButton;

    private TextField themeTitleField;
    private TextArea themeDescriptionArea;

    private JTabbedPane tabbedPane;
    private ArrayList<ThemeEditor> themeEditors;

    private JButton finishButton;
    private JButton cancelButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public CreateThemeDialog(Theme theme) {
        this.theme = theme;
        themeEditors = new ArrayList<>();

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

        this.themeTitleField = new TextField();

        this.themeImageLabel = new JLabel();
        this.setThemeImageButton = new JButton("Elegir Imagen");
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

        if (this.getTheme() != null) {
            System.out.println("is not null");
            this.themeTitleField.setText(this.getTheme().getTitle());

            if (this.getTheme().getImage() != null) {
                this.themeImageLabel.setName(this.getTheme().getImage());

                this.themeImageLabel.setIcon(Tools.getAbsoluteImageIcon(
                        this.getTheme().getImage(),
                        120,
                        120
                ));
            }

            this.themeDescriptionArea.setText(this.getTheme().getDescription());
        }

        scrollPane.getVerticalScrollBar().setUnitIncrement(7);

        // ---------------------------------------------------------------------
        northPanel.add(this.themeTitleField, BorderLayout.CENTER);

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
            addNewThemeAction();
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
        tipEditor = new TipEditor(new Tip(name, ""));

        this.tabbedPane.addTab(name, tipEditor);

        tipEditor.setName(name);

        index = this.tabbedPane.indexOfComponent(tipEditor);
        this.tabbedPane.setTabComponentAt(index, titleTab);
        this.tabbedPane.setSelectedIndex(index);

        this.themeEditors.add(tipEditor);

        tipEditor.getTitleField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String text = tipEditor.getTitleField().getText();
                titleTab.setText(text);
            }
        });

        titleTab.addCloseAction(ae -> {
            this.closeTab(tipEditor);
        });
//        updateMenuBar();
//        updateStatusBar();
//        updatePopupMenus();
        tipEditor.getTitleField().requestFocus();
    }

    /* ______________________________________________________________________ */
    private void addNewThemeAction() {
        int index;
        TitleTab titleTab;
        ThemeEditor themeEditor;
        String name;

        name = DialogPane.showInput("Nuevo Tema", "Nombre del nuevo Tema:");

        if (name == null) {
            return;
        }

        if (name.isEmpty()) {
            name = "nuevo tema " + generateTabNumber();
        }
        titleTab = new TitleTab(name);
        themeEditor = new ThemeEditor(new Theme(name, ""));

        this.tabbedPane.addTab(name, themeEditor);

        themeEditor.setName(name);

        index = this.tabbedPane.indexOfComponent(themeEditor);
        this.tabbedPane.setTabComponentAt(index, titleTab);
        this.tabbedPane.setSelectedIndex(index);

        this.themeEditors.add(themeEditor);

        titleTab.addCloseAction(ae -> {
            this.closeTab(themeEditor);
        });
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
                        e.getMessage(),
                        DialogPane.ERROR_MESSAGE
                );
            }
        } else if (option == DialogPane.NO_OPTION) {
            dispose();
        }
    }

    /* ______________________________________________________________________ */
    private void initTheme() throws Exception {
        String image = this.themeImageLabel.getName();
        String title = this.themeTitleField.getText();
        String description = this.themeDescriptionArea.getText();
        ArrayList<Theme> files = this.getFiles();

        if (title.isEmpty()) {
            throw new Exception("No se ingreso un titulo para el tema");
        } else if (image == null) {
            throw new Exception("No se escogio una imagen para el tema");
        } else if (description.isEmpty()) {
            throw new Exception("No se ingreso una descripcion para el tema");
        }

        this.setTheme(new Theme(
                image,
                title,
                description,
                0,
                files
        ));
    }

    /* ______________________________________________________________________ */
    private ArrayList<Theme> getFiles() {
        ArrayList<Theme> files = new ArrayList<>();

        for (ThemeEditor themeEditor : this.themeEditors) {
            if (themeEditor instanceof TipEditor) {
                files.add(((TipEditor) themeEditor).getTip());
            } else if (themeEditor instanceof ThemeEditor) {
                files.add(themeEditor.getTheme());
            }
        }

//        for (Component component : this.tabbedPane.getComponents()) {
//            if (component.getName() != null) {
//                if (component instanceof TipEditor) {
//                    files.add(((TipEditor) component).getTip());
//                } else if (component instanceof ThemeEditor) {
//                    files.add(((ThemeEditor) component).getTheme());
//                }
//            }
//        }
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
    public void closeTab(ThemeEditor themeEditor) {
        int option = DialogPane.showOption(
                "Eliminar Pestaña",
                "Eliminar definitivamente la pestaña "
                + themeEditor.getTheme().getTitle() + "?",
                DialogPane.YES_NO_OPTION
        );

        if (option == DialogPane.OK_OPTION) {
            this.tabbedPane.remove(themeEditor);
            this.themeEditors.remove(themeEditor);
            Tools.output(themeEditor + " closed");
        }
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
        new CreateThemeDialog(null).showTestDialog();
    }
}
