package worldclasses.themes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import tools.Pair;

public class Theme implements Serializable {

    /* ATRIBUTES ____________________________________________________________ */
    protected String image;
    protected String title;
    protected String description;

    private final Integer ID;
    private Double value;
    private Integer views;
    private ArrayList<Theme> files;
    private ArrayList<Pair<Integer, Integer>> accounts;

    /* CONSTRUCTORS _________________________________________________________ */
    public Theme(String image, String title, String description, Double value,
            Integer views, ArrayList<Theme> files, ArrayList<Pair<Integer, Integer>> accounts) {
        this.ID = new Random().nextInt(9999) + 1;

        this.image = image;
        this.title = title;
        this.description = description;

        this.value = value;
        this.views = views;
        this.files = files;
        this.accounts = accounts;
    }

    /* ______________________________________________________________________ */
    public Theme(String image, String title, String description, Double value,
            Integer views, ArrayList<Theme> files) {
        this(image, title, description, value, views, files, new ArrayList<>());
    }

    /* ______________________________________________________________________ */
    public Theme(String image, String title, String description, Double value,
            Integer views) {
        this(image, title, description, value, views, new ArrayList());
    }

    /* ______________________________________________________________________ */
    public Theme(String image, String title, String description) {
        this(image, title, description, 0.0, 0);
    }

    /* ______________________________________________________________________ */
    public Theme(String title, String description) {
        this(null, title, description);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "Theme{ID=" + this.getID()
                + "image=" + (this.getImage() != null ? this.getImage().substring(8, 16) : "")
                + ", title=" + this.getTitle()
                + ", description:\n" + this.getDescription()
                + "\n, $" + this.getValue()
                + ", " + this.getViews() + " views"
                + ", tips=\n" + this.getFiles() + "}";
    }

    /* GETTERS ______________________________________________________________ */
    public Integer getID() {
        return this.ID;
    }

    /* ______________________________________________________________________ */
    public String getImage() {
        return this.image;
    }

    /* ______________________________________________________________________ */
    public String getTitle() {
        return this.title;
    }

    /* ______________________________________________________________________ */
    public String getDescription() {
        return this.description;
    }

    /* ______________________________________________________________________ */
    public Double getValue() {
        return this.value;
    }

    /* ______________________________________________________________________ */
    public Integer getViews() {
        return this.views;
    }

    /* ______________________________________________________________________ */
    public ArrayList<Theme> getFiles() {
        return this.files;
    }

    /* ______________________________________________________________________ */
    public ArrayList<Pair<Integer, Integer>> getAccounts() {
        return this.accounts;
    }

    /* SETTERS ______________________________________________________________ */
    public void setImage(String image) {
        this.image = image;
    }

    /* ______________________________________________________________________ */
    public void setTitle(String title) {
        this.title = title;
    }

    /* ______________________________________________________________________ */
    public void setDescription(String description) {
        this.description = description;
    }

    /* ______________________________________________________________________ */
    public void setValue(Double value) {
        this.value = value;
    }

    /* ______________________________________________________________________ */
    public void setViews(Integer views) {
        this.views = views;
    }

    /* ______________________________________________________________________ */
    public void setFiles(ArrayList<Theme> files) {
        this.files = files;
    }

    /* ______________________________________________________________________ */
    public void setAccount(ArrayList<Pair<Integer, Integer>> accounts) {
        this.accounts = accounts;
    }
}
