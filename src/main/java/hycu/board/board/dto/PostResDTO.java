package hycu.board.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hycu.board.board.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResDTO {
    long id;
    String title;
    String contents;
    int like;
    String user;

    @JsonProperty("time_ago")
    LocalDateTime timeAgo;

    public PostResDTO(Post p) {
        //TODO like 처리
        id = p.getId();
        title = p.getTitle();
        contents = p.getContents();
        user = p.getCreator().getNickname();
        like = 10;
        timeAgo = p.getCreatedAt();
    }
}
