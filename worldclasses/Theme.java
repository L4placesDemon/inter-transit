package worldclasses;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Theme {

    /* ATRIBUTES ____________________________________________________________ */
    private ImageIcon image;
    private String title;
    private String description;
    private ArrayList<Tip> tips;

    /* CONSTRUCTORS _________________________________________________________ */
    public Theme(ImageIcon image, String title, ArrayList<Tip> tips) {
        this.image = image;
        this.title = title;
        this.tips = tips;
    }

    /* ______________________________________________________________________ */
    public Theme(ImageIcon image, String title) {
        this(image, title, new ArrayList<>());
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
}
