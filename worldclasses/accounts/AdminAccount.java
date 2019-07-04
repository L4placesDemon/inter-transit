package worldclasses.accounts;

import java.util.ArrayList;
import tools.binaryfilemanager.BinaryFileManager;
import worldclasses.Settings;

public class AdminAccount extends Account {

    /* ATTRIBUTES ___________________________________________________________ */
    private static final long serialVersionUID = -3552751394699439101L;

    private int LEVEL;

    /* CONSTRUCTORS _________________________________________________________ */
    public AdminAccount(int LEVEL, String username, String nickname, String password, String image) {
        super(username, nickname, password, image);

        this.LEVEL = LEVEL;
    }

    /* ______________________________________________________________________ */
    public AdminAccount(String username, String nickname, String password, String image) {
        super(username, nickname, password, image);

        this.LEVEL = this.generateLevel();
    }

    /* ______________________________________________________________________ */
    public AdminAccount(String username, String nickname, String password) {
        this(username, nickname, password, null);
    }

    /* ______________________________________________________________________ */
    public AdminAccount(Account account) {
        this(account.getUsername(), account.getNickname(), account.getPassword(), account.getImage());
        if (account instanceof AdminAccount) {
            this.LEVEL = ((AdminAccount) account).getLevel();
        }
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "AdminAccout{" + "LEVEL=" + this.getLevel() + ", "
                + super.toString().substring(8);
    }

    /* ______________________________________________________________________ */
    private int generateLevel() {
        ArrayList<Object> objects = new BinaryFileManager(Settings.ACCOUNTS_PATH_FILE).read();
        int higher = 0;
        int level;

        for (Object object : objects) {
            if (object instanceof AdminAccount) {
                level = ((AdminAccount) object).getLevel();
                if (higher < level) {
                    higher = level;
                }
            }
        }

        return higher + 1;
    }

    /* GETTERS ______________________________________________________________ */
    public int getLevel() {
        return this.LEVEL;
    }
}
