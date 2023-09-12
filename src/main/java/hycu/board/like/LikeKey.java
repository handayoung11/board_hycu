package hycu.board.like;

import hycu.board.post.Post;
import hycu.board.users.Users;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class LikeKey implements Serializable {

    @ManyToOne
    private Users user;

    @ManyToOne
    private Post post;
}
