package worldclasses.themes;

public class Tip extends Theme {

    /* CONSTRUCTORS _________________________________________________________ */
    public Tip(String title, String content) {
        super(title, content);
    }

    /* ______________________________________________________________________ */
    public Tip(String image, String title, String content) {
        super(image, title, content);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "Tip{image=" + (this.getImage() != null ? this.getImage() : "")
                + ", title=" + super.getTitle()
                + ", content=\n" + this.getDescription() + "\n}";
    }
}
