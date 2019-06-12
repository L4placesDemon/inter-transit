package worldclasses;

import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Serializable {

    /* ATTRIBUTES ___________________________________________________________ */
    private String username;
    private String nickname;
    private String password;
    private String image;

    /* CONSTRUCTORS _________________________________________________________ */
    public Account(
            String username,
            String nickname,
            String password,
            String image
    ) {
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
                + (this.getImage() != null ? this.image.substring(16, 24) : "") + "}";
    }

    /* ______________________________________________________________________ */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /* ______________________________________________________________________ */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
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
