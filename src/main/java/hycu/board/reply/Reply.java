package hycu.board.reply;

import hycu.board.base_entity.CreationBaseEntity;
import hycu.board.post.Post;
import hycu.board.users.Users;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends CreationBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    String contents;

    public Reply(Post post, String contents, Users creator) {
        this.post = post;
        this.contents = contents;
        this.creator = creator;
    }
}
