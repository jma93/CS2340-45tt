package a404_notfound.sourceappwater.model;

import java.util.HashMap;

/**
 * Created by Joshua on 2/14/2017.
 *
 * For Password Verification
 */



public class UsersAndPasswords {
    private static HashMap<String, String> usp = new HashMap<>();

    public static void insert(String user, String pass) {
        usp.put(user, pass);
    }

    public static String returnPassword(String user) {
        return usp.get(user);
    }

    public static boolean isContained(String email) {
        return usp.containsKey(email);
    }

    public static boolean passwordMatch(String email, String pass) {
        String compPass = usp.get(email);
        return compPass.equals(pass);
    }
}
