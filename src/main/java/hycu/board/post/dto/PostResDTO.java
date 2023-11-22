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

    public PostResDTO(Post p, long like) {
        id = p.getId();
        title = p.getTitle();
        user = p.getCreator().getNickname();
        this.like = like;
        timeAgo = p.getCreatedAt();
    }

    public void updateCommentsCount(long commentsCount) {
        this.commentsCount = commentsCount;
    }
}
