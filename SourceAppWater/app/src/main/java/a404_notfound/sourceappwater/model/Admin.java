package a404_notfound.sourceappwater.model;

/**
 * Created by Joshua on 2/21/2017.
 */

public class Admin {
    private String username;
    private String address;
    private String coordinates;
    private int loginAttemps;

    public Admin() {
        username = "No Name";
        coordinates = "None";
        address = "None";
        loginAttemps = 0;
    }

    public Admin(String name) {
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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        return "Admin";
    }
}
