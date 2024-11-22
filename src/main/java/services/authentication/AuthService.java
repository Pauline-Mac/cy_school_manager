package services.authentication;

import models.User;
import org.apache.commons.codec.digest.DigestUtils;

public class AuthService {
    public boolean authenticate(User user, String input_email, String input_password) {
        String email = user.getEmail();
        Boolean email_match = email.equals(input_email);
        String hashed_input_password = user.getPassword();
        String hashed_password = DigestUtils.sha256Hex(input_password);
        Boolean password_match = hashed_password.equals(hashed_input_password);
        return email_match && password_match;
    }
}
