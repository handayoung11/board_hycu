package hycu.board.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSvc {

    private final UserRepo userRepo;

    public boolean checkMailDuplication(String mail) {
        return userRepo.findByEmailOrLoginId(mail, mail).isPresent();
    }
}
