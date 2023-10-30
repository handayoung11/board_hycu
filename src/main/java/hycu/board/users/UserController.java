package hycu.board.users;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
