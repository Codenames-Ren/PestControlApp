package pestcontrol.util;

import java.security.MessageDigest;

/**
 *
 * @author ren
 */
public class PasswordUtil {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyPassword(String rawPassword, String hashedPasswordFromDb) {
        String hashedInput = hashPassword(rawPassword);
        return hashedInput.equals(hashedPasswordFromDb);
    }
}
