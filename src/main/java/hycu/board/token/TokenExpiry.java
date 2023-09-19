package hycu.board.token;

public class TokenExpiry {
    public static final int JWT_EXPIRY_SEC = 60 * 30;
    public static final int REFRESH_EXPIRY_SEC = 60 * 60 * 24 * 365 / 4; //3개월
}
