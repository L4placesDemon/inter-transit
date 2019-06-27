package worldclasses.themes;

public class Tip {

    /* ATRIBUTES ____________________________________________________________ */
    private String title;
    private String content;

    /* CONSTRUCTORS _________________________________________________________ */
    public Tip(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "Tip{" + getTitle() + ": " + getContent() + '}';
    }

    /* GETTERS ______________________________________________________________ */
    public String getTitle() {
        return this.title;
    }

    /* ______________________________________________________________________ */
    public String getContent() {
        return this.content;
    }

    /* SETTERS ______________________________________________________________ */
    public void setTitle(String title) {
        this.title = title;
    }

    /* ______________________________________________________________________ */
    public void setContent(String content) {
        this.content = content;
    }
}
