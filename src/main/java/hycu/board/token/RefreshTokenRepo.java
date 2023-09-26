package hycu.board.token;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    @Query("select r from RefreshToken r where HEX(r.value) = :value and CONCAT(r.id, r.jti) = :jti")
    Optional<RefreshToken> findByValueAndJti(String value, String jti);

    @Modifying
    @Transactional
    @Query("delete from RefreshToken r where HEX(r.value) = :value")
    void deleteByValue(String value);
}
