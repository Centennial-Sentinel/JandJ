//TODO: see if there's a better way to implement BCrypt
//Stores information about a user's profile
public class UserProfile {

    private String username;
    private String password; // Hashed
    private String email;

    public UserProfile(String username, String password, String email) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        this.username = username;
        this.password = hashed;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = hashed;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
