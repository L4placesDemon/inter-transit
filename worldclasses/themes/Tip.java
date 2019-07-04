package worldclasses.themes;

public class Tip extends Theme {

    /* CONSTRUCTORS _________________________________________________________ */
    public Tip(String title, String content) {
        super(title, content);
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "Tip{image=" + (this.getImage() != null ? this.getImage().substring(8, 16) : "")
                + ", title=" + super.getTitle()
                + ", content=\n" + this.getDescription() + "\n}";
    }
}
