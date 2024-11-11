package org.openjfx.views.admin;

import org.openjfx.schoolmanager.User;
import org.openjfx.schoolmanager.UserCrud;

public class AuthService {
    private static UserCrud usercrud = new UserCrud();

    public static User authenticate(String username, String password) {
        User user = usercrud.FindByEmail(username);
        if (user != null && user.getPassword().equals(hashPassword(password))) {
            return user;
        }
        return null;
    }

    private static String hashPassword(String password) {
        /*
        à faire
         */

        return password;
    }

}
