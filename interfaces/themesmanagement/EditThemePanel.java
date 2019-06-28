package interfaces.themesmanagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.components.TextArea;
import tools.components.TextField;

import worldclasses.themes.Theme;
import worldclasses.themes.Tip;

public class EditThemePanel extends JPanel {

    private Theme theme;

    private TextField themeNameField;
    private TextArea themeDescriptionArea;

    private JPanel centerPanel;
    private JButton cancelButton;
    private JButton saveButton;

    public EditThemePanel() {
        this.initComponents();
        this.initEvents();
    }

    public EditThemePanel(Theme theme) {
        this();
        this.theme = theme;
    }

    private void initComponents() {
        JPanel northPanel;
        JPanel southPanel;

        // Set up Panel --------------------------------------------------------
        this.setLayout(new BorderLayout());

        // Set up Components ---------------------------------------------------
        this.themeNameField = new TextField("campo");
        this.themeDescriptionArea = new TextArea("area");

        this.centerPanel = new JPanel();
        this.cancelButton = new JButton("Cancelar");
        this.saveButton = new JButton("Guardar");

        northPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ---------------------------------------------------------------------
        this.centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------------------------
        northPanel.add(this.themeNameField, BorderLayout.NORTH);
        northPanel.add(this.themeDescriptionArea, BorderLayout.CENTER);

        southPanel.add(this.cancelButton);
        southPanel.add(this.saveButton);

//        this.add(northPanel, BorderLayout.NORTH);
        this.add(this.centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    private void initEvents() {
        this.cancelButton.addActionListener(ae -> {
            this.restore();
        });

        this.saveButton.addActionListener(ae -> {
            this.save();
        });
    }

    public void restore() {

    }

    public void save() {

    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        System.out.println("tema");

        this.theme = theme;
        this.themeNameField.setText(this.getTheme().getTitle());
        this.themeDescriptionArea.setText(this.getTheme().getDescription());

        this.centerPanel.removeAll();
        this.centerPanel.updateUI();

        for (Tip tip : this.getTheme().getTips()) {
            TipPanel tipPanel = new TipPanel(tip);

            tipPanel.getTitleButton().addActionListener(ae -> {
                this.centerPanel.updateUI();
            });
            this.centerPanel.add(tipPanel);
        }
    }
}
