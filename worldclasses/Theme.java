package worldclasses;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Theme {

    private ImageIcon image;
    private String title;
    private ArrayList<Tip> tips;

    public Theme(ImageIcon image, String title, ArrayList<Tip> tips) {
        this.image = image;
        this.title = title;
        this.tips = tips;
    }

    public Theme(ImageIcon image, String title) {
        this(image, title, new ArrayList<>());
    }

    public ImageIcon getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public ArrayList<Tip> getTips() {
        return this.tips;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTips(ArrayList<Tip> tips) {
        this.tips = tips;
    }
}
