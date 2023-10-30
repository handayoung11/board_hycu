package hycu.board.users;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    @Query(value = "SELECT * from users where binary login_id = :id and active is true", nativeQuery = true)
    Optional<Users> findByLoginIdWithCaseSensitive(String id);
    Optional<Users> findByEmailOrLoginId(String email, String id);
    Optional<Users> findByNickname(String nickname);
}
