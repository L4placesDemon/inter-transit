package worldclasses.themes;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Theme {

    /* ATRIBUTES ____________________________________________________________ */
    private ImageIcon image;
    private String title;
    private String description;
    private ArrayList<Tip> tips;
    private Integer progress;
    private Double value;
    private Integer views;

    /* CONSTRUCTORS _________________________________________________________ */
    public Theme(
            ImageIcon image,
            String title,
            String description,
            ArrayList<Tip> tips,
            Integer progress,
            Double value,
            Integer views) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.tips = tips;
        this.progress = progress;
        this.value = value;
        this.views = views;
    }

    /* ______________________________________________________________________ */
    public Theme(ImageIcon image,
            String title,
            String description,
            Integer progress,
            Double value,
            Integer views) {
        this(image, title, description, new ArrayList<>(), progress, value, views);
    }

    /* ______________________________________________________________________ */
    public Theme() {
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "Theme{" + "image=" + getImage()
                + ", "+ getTitle()
                + ": " + getDescription()
                + " -> " + getTips()
                + ", " + getProgress() + '%'
                + ", $" + getValue()
                + ", " + getViews() + " views}";
    }

    /* GETTERS ______________________________________________________________ */
    public ImageIcon getImage() {
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
    public ArrayList<Tip> getTips() {
        return this.tips;
    }

    /* ______________________________________________________________________ */
    public Integer getProgress() {
        return this.progress;
    }

    /* ______________________________________________________________________ */
    public Double getValue() {
        return this.value;
    }

    /* ______________________________________________________________________ */
    public Integer getViews() {
        return this.views;
    }

    /* SETTERS ______________________________________________________________ */
    public void setImage(ImageIcon image) {
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
    public void setTips(ArrayList<Tip> tips) {
        this.tips = tips;
    }

    /* ______________________________________________________________________ */
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    /* ______________________________________________________________________ */
    public void setValue(Double value) {
        this.value = value;
    }

    /* ______________________________________________________________________ */
    public void setViews(Integer views) {
        this.views = views;
    }
}
