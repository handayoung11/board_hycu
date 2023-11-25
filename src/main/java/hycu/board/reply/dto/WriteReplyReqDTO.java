package hycu.board.reply.dto;

import lombok.Getter;

@Getter
public class WriteReplyReqDTO {
    Long postId;
    String contents;
}
