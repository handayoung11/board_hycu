package hycu.board.like;

import hycu.board.post.Post;
import hycu.board.users.Users;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeKey implements Serializable {

    @ManyToOne
    private Users user;

    @ManyToOne
    private Post post;
}
