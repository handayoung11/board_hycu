package hycu.board.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hycu.board.post.Post;
import hycu.board.reply.Reply;
import hycu.board.users.Users;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostDetailResDTO {

    long id;
    String title;
    String content;
    long like;
    boolean clickLike = false;
    CreatorDTO creator;
    List<ReplyDTO> comments;

    @JsonProperty("time_ago")
    LocalDateTime timeAgo;

    public PostDetailResDTO(Post post, long like) {
        id = post.getId();
        title = post.getTitle();
        content = post.getContents();
        this.like = like;
        this.creator = new CreatorDTO(post.getCreator());
        timeAgo = post.getCreatedAt();
    }

    public void configComments(List<Reply> replies) {
        comments = replies.stream().map(ReplyDTO::new).toList();
    }

    public void updateClickLike() {
        clickLike = true;
    }

    @Getter
    private class ReplyDTO {
        long id;
        String content;
        int level = 0;
        String user;

        @JsonProperty("time_ago")
        LocalDateTime timeAgo;

        public ReplyDTO(Reply r) {
            id = r.getId();
            content = r.getContents();
            user = r.getCreator().getNickname();
            timeAgo = r.getCreatedAt();
        }
    }

    @Getter
    private class CreatorDTO {
        long id;
        String nickname;

        public CreatorDTO(Users user) {
            id = user.getId();
            nickname = user.getNickname();
        }
    }
}
