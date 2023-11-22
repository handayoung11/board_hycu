package hycu.board.users.dto;

import hycu.board.users.Users;
import lombok.Getter;

@Getter
public class MyInfoResDTO  {
    private long id;
    private String nickname;

    public MyInfoResDTO(Users u) {
        this.id = u.getId();
        this.nickname = u.getNickname();
    }
}
