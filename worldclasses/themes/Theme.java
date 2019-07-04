package worldclasses.themes;

import java.util.ArrayList;

public class Theme {

    /* ATRIBUTES ____________________________________________________________ */
    protected String image;
    protected String title;
    protected String description;
    private Double value;
    private Integer views;
    private ArrayList<Theme> files;

    /* CONSTRUCTORS _________________________________________________________ */
    public Theme(String image, String title, String description, Double value, Integer views, ArrayList<Theme> files) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.value = value;
        this.views = views;
        this.files = files;
    }

    /* ______________________________________________________________________ */
    public Theme(String title, String description) {
        this.title = title;
        this.description = description;
        this.value = 0.0;
        this.views = 0;
        this.files = new ArrayList<>();
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "Theme{image=" + (this.getImage() != null ? this.getImage().substring(8, 16) : "")
                + ", title=" + this.getTitle()
                + ", description:\n" + this.getDescription()
                + "\n, $" + this.getValue()
                + ", " + this.getViews() + " views"
                + ", tips=\n" + this.getFiles() + "}";
    }

    /* GETTERS ______________________________________________________________ */
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
}
