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

    /* CONSTRUCTORS _________________________________________________________ */
    public Theme(ImageIcon image, String title, ArrayList<Tip> tips, Integer progress) {
        this.image = image;
        this.title = title;
        this.tips = tips;
        this.progress = progress;
    }

    /* ______________________________________________________________________ */
    public Theme(ImageIcon image, String title, Integer progress) {
        this(image, title, new ArrayList<>(), progress);
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
}
