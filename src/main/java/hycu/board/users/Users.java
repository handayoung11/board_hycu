package hycu.board.users;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private LocalDateTime signUpDate;
}
