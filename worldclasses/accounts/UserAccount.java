package worldclasses.accounts;

import java.util.ArrayList;
import java.util.Random;

public class UserAccount extends Account {

    /* ATTRIBUTTES __________________________________________________________ */
    private static final long serialVersionUID = -1121815081591018371L;

    private int ID;
    private Integer level;
    private Integer points;

    private ArrayList<Integer> viewedThemes;

    /* CONSTRUCTORS _________________________________________________________ */
    public UserAccount(Integer ID, String username, String nickname, String password,
            String image, Integer level, Integer points, ArrayList<Integer> viewedThemes) {
        super(username, nickname, password, image);
        this.ID = ID;

        this.level = level;
        this.points = points;
        this.viewedThemes = viewedThemes;
    }

    public UserAccount(String username, String nickname, String password,
            String image, Integer level, Integer points, ArrayList<Integer> viewedThemes) {
        this(new Random().nextInt(9999) + 1, username, nickname, password, image, level, points, viewedThemes);
    }

    /* ______________________________________________________________________ */
    public UserAccount(String username, String nickname, String password,
            String image, Integer level, Integer points) {
        this(username, nickname, password, image, level, points, new ArrayList<>());
    }

    /* ______________________________________________________________________ */
    public UserAccount(String username, String nickname, String password,
            String image) {
        this(username, nickname, password, image, 1, 0);
    }

    /* ______________________________________________________________________ */
    public UserAccount(String username, String nickname, String password) {
        this(username, nickname, password, null);
    }

    /* GETTERS ______________________________________________________________ */
    public int getID() {
        return this.ID;
    }

    /* ______________________________________________________________________ */
    public Integer getLevel() {
        return level;
    }

    /* ______________________________________________________________________ */
    public Integer getPoints() {
        return points;
    }

    /* ______________________________________________________________________ */
    public ArrayList<Integer> getViewedThemes() {
        return this.viewedThemes;
    }

    /* SETTERS ______________________________________________________________ */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /* ______________________________________________________________________ */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /* ______________________________________________________________________ */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /* ______________________________________________________________________ */
    public void setViewedThemes(ArrayList<Integer> viewedThemes) {
        this.viewedThemes = viewedThemes;
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        String superString = super.toString().substring(8);
        return "UserAccount{ID=" + this.getID() + ", "
                + superString.substring(0, superString.length() - 1) + ", "
                + this.getLevel() + ", "
                + this.getPoints() + ", "
                + this.getViewedThemes() + "}";
    }
}
