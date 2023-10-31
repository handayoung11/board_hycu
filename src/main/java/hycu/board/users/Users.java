package hycu.board.users;

import hycu.board.users.dto.SignUpReqDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TINYINT")
    private boolean active;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String loginId;
    private String pw;
    private String nickname;
    private String email;
    @CreationTimestamp
    private LocalDateTime signUpDate;

    public static Users createUser(SignUpReqDTO dto) {
        Users u = new Users();
        u.active = true;
        u.role = UserRole.MEMBER;
        u.loginId = dto.getEmail();
        u.email = dto.getEmail();
        u.pw = dto.getPw();
        u.nickname = dto.getNickname();
        return u;
    }
}
