    package worldclasses.accounts;

public class AdminAccount extends Account {

    /* CONSTRUCTORS _________________________________________________________ */
    public AdminAccount(String username, String nickname, String password, String image) {
        super(username, nickname, password, image);
    }

    /* ______________________________________________________________________ */
    public AdminAccount(String username, String nickname, String password) {
        super(username, nickname, password);
    }

    /* ______________________________________________________________________ */
    public AdminAccount(Account account) {
        this(account.getUsername(),
                account.getNickname(),
                account.getPassword(),
                account.getImage()
        );
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "Admin" + super.toString();
    }
}
