package pojos.GetAuthToken;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Get_Auth_Token {

    private String username;
    private String password;

    public Get_Auth_Token(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Get_Auth_Token() {
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
        this.password = password;
    }

    @Override
    public String toString() {
        return "Get_Auth_Token{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
