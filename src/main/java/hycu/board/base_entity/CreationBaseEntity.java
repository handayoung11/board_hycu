package hycu.board.base_entity;

import hycu.board.users.Users;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class CreationBaseEntity {

    @JoinColumn(name = "creator")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users creator;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
