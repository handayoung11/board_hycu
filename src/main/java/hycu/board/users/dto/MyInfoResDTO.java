package hycu.board.users.dto;

import hycu.board.users.Users;
import lombok.Getter;

@Getter
public class MyInfoResDTO  {
    private String nickname;

    public MyInfoResDTO(Users u) {
        this.nickname = u.getNickname();
    }
}
