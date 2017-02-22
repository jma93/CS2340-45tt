package a404_notfound.sourceappwater.model;

/**
 * Created by Lase on 2/12/2017.
 *
 * A registered user in the model.
 */

public class RegisteredUser {
    private String username;
    private String address;
    private String coordinates;
    private int loginAttemps;

    public RegisteredUser() {
        username = "No Name";
        coordinates = "None";
        address = "None";
        loginAttemps = 0;
    }

    public RegisteredUser(String name) {
        this();
        username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public int getLoginAttemps() {
        return loginAttemps;
    }

    public void setLoginAttemps(int loginAttemps) {
        this.loginAttemps = loginAttemps;
    }

    @Override
    public String toString() {
        return "User";
    }
}
