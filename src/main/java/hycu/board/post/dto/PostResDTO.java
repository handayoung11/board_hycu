package hycu.board.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hycu.board.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResDTO {
    long id;
    String title;
    String contents;
    long like;
    String user;

    @JsonProperty("time_ago")
    LocalDateTime timeAgo;

    public PostResDTO(Post p, long like) {
        id = p.getId();
        title = p.getTitle();
        contents = p.getContents();
        user = p.getCreator().getNickname();
        this.like = like;
        timeAgo = p.getCreatedAt();
    }
}
