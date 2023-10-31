package hycu.board.users;

import hycu.board.users.dto.MyInfoResDTO;
import hycu.board.users.dto.SignUpReqDTO;
import hycu.board.utils.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserSvc userSvc;

    @GetMapping("/mail/{mail}")
    public boolean checkMail(@PathVariable String mail) {
        return !userSvc.checkMailDuplication(mail);
    }

    @GetMapping("/nickname/{nickname}")
    public boolean checkNickname(@PathVariable String nickname) {
        return !userSvc.checkNicknameDuplication(nickname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid SignUpReqDTO dto) {
        userSvc.signUp(dto);
    }

    @GetMapping("me")
    public MyInfoResDTO getMyInfo() {
        Long id = SecurityUtils.getId();
        return userSvc.getUser(id);
    }
}
