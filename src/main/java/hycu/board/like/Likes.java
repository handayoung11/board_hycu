package hycu.board.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes {

    @EmbeddedId
    LikeKey likeKey;

    public Likes(LikeKey likeKey) {
        this.likeKey = likeKey;
    }
}
