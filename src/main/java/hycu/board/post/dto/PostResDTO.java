package hycu.board.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hycu.board.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResDTO {
    long id;
    String title;
    long like;
    String user;

    @JsonProperty("comments_count")
    long commentsCount;

    @JsonProperty("time_ago")
    LocalDateTime timeAgo;

    public PostResDTO(Post p, long like, long commentsCount) {
        id = p.getId();
        title = p.getTitle();
        this.commentsCount = commentsCount;
        user = p.getCreator().getNickname();
        this.like = like;
        timeAgo = p.getCreatedAt();
    }
}
