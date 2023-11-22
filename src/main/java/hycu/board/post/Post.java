package hycu.board.post;

import hycu.board.base_entity.CreationBaseEntity;
import hycu.board.post.dto.UpdatePostReqDTO;
import hycu.board.post.dto.WritePostReqDTO;
import hycu.board.users.Users;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends CreationBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String contents;
    boolean active;

    public static Post createPost(WritePostReqDTO dto, Users creator) {
        Post p = new Post();
        p.title = dto.getTitle();
        p.contents = dto.getContents();
        p.active = true;
        p.creator = creator;
        return p;
    }

    public void update(UpdatePostReqDTO dto) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }
}
