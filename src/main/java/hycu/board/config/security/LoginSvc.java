package hycu.board.config.security;

import hycu.board.users.UserRepo;
import hycu.board.users.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginSvc implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Users user = userRepo.findByLoginIdWithCaseSensitive(userId)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정"));

        return new User(user.getId().toString(), user.getPw(),
                List.of(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
