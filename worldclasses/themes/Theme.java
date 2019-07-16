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

    protected Integer ID;
    private Integer views;
    private ArrayList<Theme> files;
    private ArrayList<Pair<Integer, Integer>> accounts;

    /* CONSTRUCTORS _________________________________________________________ */
    public Theme(String image, String title, String description, Integer views, ArrayList<Theme> files, ArrayList<Pair<Integer, Integer>> accounts) {
        this.ID = new Random().nextInt(9999) + 1;

        this.image = image;
        this.title = title;
        this.description = description;

        this.views = views;
        this.files = files;
        this.accounts = accounts;
    }

    /* ______________________________________________________________________ */
    public Theme(String image, String title, String description, Integer views, ArrayList<Theme> files) {
        this(image, title, description, views, files, new ArrayList<>());
    }

    /* ______________________________________________________________________ */
    public Theme(String image, String title, String description, Integer views) {
        this(image, title, description, views, new ArrayList());
    }

    /* ______________________________________________________________________ */
    public Theme(String image, String title, String description) {
        this(image, title, description, 0);
    }

    /* ______________________________________________________________________ */
    public Theme(String title, String description) {
        this(null, title, description);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "Theme{ID=" + this.getID()
                + ", image=" + (this.getImage() != null ? this.getImage() : "")
                + ", title=" + this.getTitle()
                + ", description:\n" + this.getDescription()
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
    public void setID(Integer ID) {
        this.ID = ID;
    }

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
