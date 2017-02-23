package a404_notfound.sourceappwater.model;

import java.util.HashMap;
import java.util.Map;

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
    private Map<String, String> usinfo = new HashMap<>();

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User";
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("name", username);
        ret.put("addrs", address);
        ret.put("coord", coordinates);
        ret.put("logAtmps", loginAttemps);

        return ret;
    }
}
