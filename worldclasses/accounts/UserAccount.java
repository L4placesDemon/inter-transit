package worldclasses.accounts;

import java.util.Random;

public class UserAccount extends Account {

    /* ATTRIBUTTES __________________________________________________________ */
    private static final long serialVersionUID = -1121815081591018371L;

    private final int ID;
    private Integer level;
    private Integer points;

    /* CONSTRUCTORS _________________________________________________________ */
    public UserAccount(String username, String nickname, String password, String image, Integer level, Integer points) {
        super(username, nickname, password, image);
        this.ID = new Random().nextInt(9999) + 1;

        this.level = level;
        this.points = points;
    }

    /* ______________________________________________________________________ */
    public UserAccount(String username, String nickname, String password, String image) {
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

    /* SETTERS ______________________________________________________________ */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /* ______________________________________________________________________ */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        String superString = super.toString().substring(8);
        return "UserAccount{ID=" + this.getID() + ", "
                + superString.substring(0, superString.length() - 1) + ", "
                + this.getLevel() + ", "
                + this.getPoints() + "}";
    }
}
