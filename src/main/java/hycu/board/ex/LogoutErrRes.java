package hycu.board.ex;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class LogoutErrRes implements Serializable {
    private String msg;
    private boolean doLogout = true;

    public LogoutErrRes(String msg) {
        this.msg = msg;
    }
}
