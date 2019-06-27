package worldclasses.accounts;

import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Serializable {

    /* ATTRIBUTES ___________________________________________________________ */
    private String username;
    private String nickname;
    private String password;
    private String image;

    /* CONSTRUCTORS _________________________________________________________ */
    public Account(String username, String nickname, String password, String image) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.image = image;
    }

    /* ______________________________________________________________________ */
    public Account(String username, String nickname, String password) {
        this(username, nickname, password, null);
    }

    /* ______________________________________________________________________ */
    public Account(Account account) {
        this(
                account.getUsername(),
                account.getNickname(),
                account.getPassword(),
                account.getImage()
        );
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "Account{" + this.getUsername() + ", "
                + this.getNickname() + ", "
                + this.getPassword() + ", "
                + (this.getImage() != null ? this.getImage().substring(8, 16) : "") + "}";
    }

    /* ______________________________________________________________________ */
    @Override   
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.getUsername());
        hash = 43 * hash + Objects.hashCode(this.getNickname());
        hash = 43 * hash + Objects.hashCode(this.getPassword());
        hash = 43 * hash + Objects.hashCode(this.getImage());
        return hash;
    }

    /* ______________________________________________________________________ */
    @Override
    public boolean equals(Object obj) {
        final Account other = (Account) obj;
        if (this == obj) {
            return true;
        }
        return !(other == null
                || getClass() != other.getClass()
                || !Objects.equals(this.getUsername(), other.getUsername())
                || !Objects.equals(this.getNickname(), other.getNickname())
                || !Objects.equals(this.getPassword(), other.getPassword())
                || !Objects.equals(this.getImage(), other.getImage()));
    }

    /* GETTERS ______________________________________________________________ */
    public String getImage() {
        return this.image;
    }

    /* ______________________________________________________________________ */
    public String getUsername() {
        return this.username;
    }

    /* ______________________________________________________________________ */
    public String getNickname() {
        return this.nickname;
    }

    /* ______________________________________________________________________ */
    public String getPassword() {
        return this.password;
    }

    /* SETTERS ______________________________________________________________ */
    public void setImage(String image) {
        this.image = image;
    }

    /* ______________________________________________________________________ */
    public void setUsername(String username) {
        this.username = username;
    }

    /* ______________________________________________________________________ */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /* ______________________________________________________________________ */
    public void setPassword(String password) {
        this.password = password;
    }
}
