package hycu.board.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Likes {

    @EmbeddedId
    LikeKey likeKey;
}
