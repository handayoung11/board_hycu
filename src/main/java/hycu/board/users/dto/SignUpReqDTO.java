package hycu.board.users.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpReqDTO {
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "invalid email")
    private String email;
    @Size(min = 2, max = 10, message = "nickname should be between 2 and 10 letters")
    private String nickname;
    @Size(min = 8, max = 16, message = "pw should be between 8 and 16 letters")
    private String pw;

    public void updatePw(String pw) {
        this.pw = pw;
    }
}
