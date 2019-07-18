package interfaces.workshops.createtheme;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import tools.Tools;
import tools.components.DialogPane;
import tools.components.TextArea;
import tools.components.TextField;
import worldclasses.themes.Theme;

public class ThemeEditor extends JPanel {

	/* ATTRIBUTES ___________________________________________________________ */
	private static final long serialVersionUID = -3262044934925318895L;

	private Theme theme;

	protected JLabel imageLabel;
	protected TextField titleField;
	protected TextArea descriptionArea;
	private JTree filesTree;

	private JButton editButton;

	/* CONSTRUCTORS _________________________________________________________ */
	public ThemeEditor(Theme theme) {
		this.theme = theme;

		this.initComponents();
		this.initEvents();
	}

	/* METHODS ______________________________________________________________ */
	protected void initComponents() {
		JPanel northPanel;
		JPanel imagePanel;
		JPanel themePanel;
		JPanel buttonsPanel;

		JScrollPane scrollPane;

		// Set up Panel --------------------------------------------------------
		this.setLayout(new BorderLayout());

		// Set up Components ---------------------------------------------------
		this.imageLabel = new JLabel();
		this.titleField = new TextField(this.getTheme().getTitle());
		this.descriptionArea = new TextArea(this.getTheme().getDescription());

		this.filesTree = new JTree(this.initTree());

		this.editButton = new JButton("Editar");

		northPanel = new JPanel(new BorderLayout());
		imagePanel = new JPanel();
		themePanel = new JPanel(new BorderLayout());
		scrollPane = new JScrollPane(this.descriptionArea);

		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		// ---------------------------------------------------------------------
		if (this.getTheme() != null) {
			if (this.getTheme().getImage() != null) {

				this.imageLabel.setPreferredSize(new Dimension(120, 120));

				this.imageLabel.setIcon(Tools.getAbsoluteImageIcon(this.getTheme().getImage(),
						120, 120));
			}
		}

		this.titleField.setEditable(false);
		this.descriptionArea.setEditable(false);

		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
		scrollPane.getVerticalScrollBar().setUnitIncrement(7);

		// ---------------------------------------------------------------------
		northPanel.add(this.titleField, BorderLayout.CENTER);

		imagePanel.add(this.imageLabel);

		themePanel.add(northPanel, BorderLayout.NORTH);
		themePanel.add(imagePanel, BorderLayout.WEST);
		themePanel.add(scrollPane, BorderLayout.CENTER);

		buttonsPanel.add(this.editButton);

		this.add(themePanel, BorderLayout.CENTER);
		this.add(this.filesTree, BorderLayout.WEST);
		this.add(buttonsPanel, BorderLayout.SOUTH);
	}

	/* ______________________________________________________________________ */
	protected void initEvents() {
		// Components Events ---------------------------------------------------
		this.editButton.addActionListener(ae -> {
			createTheme();
		});
	}

	/* ______________________________________________________________________ */
	private void createTheme() {
		EditThemeDialog createThemeDialog = new EditThemeDialog(this.getTheme());
		int result;
		Theme _theme;

		result = createThemeDialog.showDialog();
		if (result == DialogPane.OK_OPTION) {
			_theme = createThemeDialog.getTheme();
			if (_theme != null) {
				this.setTheme(_theme);
			}
		}

		this.filesTree.setModel(this.initTree());
	}

	/* ______________________________________________________________________ */
	private DefaultTreeModel initTree() {
		return new DefaultTreeModel(this.initNode(this.getTheme()));
	}

	/* ______________________________________________________________________ */
	private DefaultMutableTreeNode initNode(Theme theme) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(theme.getTitle());

		for (Theme file : theme.getFiles()) {
			node.add(this.initNode(file));
		}

		return node;
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
