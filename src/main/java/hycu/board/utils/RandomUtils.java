package hycu.board.utils;

import java.security.SecureRandom;

public class RandomUtils {
    private static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public static String randomAlphaNumeric(int len) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            char randomChar = CHARS[random.nextInt(CHARS.length)];
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
