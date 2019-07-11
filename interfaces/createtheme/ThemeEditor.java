package interfaces.createtheme;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import tools.components.DialogPane;

import tools.components.TextArea;
import tools.components.TextField;

import worldclasses.themes.Theme;

public class ThemeEditor extends JPanel {

    /* ATTRIBUTES ___________________________________________________________ */
    private Theme theme;

    private JLabel themeImageLabel;
    private TextField themeTitleField;
    private TextField themeValueField;
    private TextArea themeDescriptionArea;
    private JTree filesTree;

    private JButton editThemeButton;

    /* CONSTRUCTORS _________________________________________________________ */
    public ThemeEditor(Theme theme) {
        this.theme = theme;

        this.initComponents();
        this.initEvents();
    }

    /* METHODS ______________________________________________________________ */
    private void initComponents() {
        JPanel northPanel;
        JPanel themePanel;
        JPanel buttonsPanel;

        JScrollPane scrollPane;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.themeImageLabel = new JLabel();
        this.themeTitleField = new TextField(this.getTheme().getTitle());
        this.themeValueField = new TextField(this.getTheme().getValue() + "");
        this.themeDescriptionArea = new TextArea(this.getTheme().getDescription());

        this.filesTree = new JTree(this.initTree());

        this.editThemeButton = new JButton("Editar");

        northPanel = new JPanel(new BorderLayout());
        themePanel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane(this.themeDescriptionArea);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        this.themeImageLabel.setPreferredSize(new Dimension(120, 120));

        scrollPane.getVerticalScrollBar().setUnitIncrement(7);

        // ---------------------------------------------------------------------
        northPanel.add(this.themeTitleField, BorderLayout.CENTER);
        northPanel.add(this.themeValueField, BorderLayout.EAST);

        themePanel.add(northPanel, BorderLayout.NORTH);
        themePanel.add(this.themeImageLabel, BorderLayout.WEST);
        themePanel.add(scrollPane, BorderLayout.CENTER);

        buttonsPanel.add(this.editThemeButton);

        this.add(themePanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /* ______________________________________________________________________ */
    private void initEvents() {
        // Components Events ---------------------------------------------------
        this.editThemeButton.addActionListener(ae -> {
            CreateThemeDialog createThemeDialog = new CreateThemeDialog();
            int result;
            Theme _theme;

            result = createThemeDialog.showDialog();
            if (result == DialogPane.OK_OPTION) {
                _theme = createThemeDialog.getTheme();
                if (_theme != null) {
                    this.setTheme(_theme);
                }
            }
        });
    }

    /* ______________________________________________________________________ */
    private DefaultTreeModel initTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.getTheme().getTitle());
        DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);

        return defaultTreeModel;
    }

    /* GETTERS ______________________________________________________________ */
    public Theme getTheme() {
        return this.theme;
    }

    /* SETTERS ______________________________________________________________ */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
