package hycu.board.users;

import hycu.board.users.dto.MyInfoResDTO;
import hycu.board.users.dto.SignUpReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class UserSvc {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public boolean checkMailDuplication(String mail) {
        return userRepo.findByEmailOrLoginId(mail, mail).isPresent();
    }

    public boolean checkNicknameDuplication(String nickname) {
        return userRepo.findByNickname(nickname).isPresent();
    }

    public void signUp(SignUpReqDTO dto) {
        dto.updatePw(encoder.encode(dto.getPw()));
        Users user = Users.createUser(dto);
        try {
            userRepo.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "이메일이나 닉네임을 다시 확인해주세요.");
        }
    }

    public MyInfoResDTO getUser(Long id) {
        Users u = userRepo.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "잘못된 계정입니다. 로그아웃 후 다시 시도해주세요."));
        return new MyInfoResDTO(u);
    }
}
