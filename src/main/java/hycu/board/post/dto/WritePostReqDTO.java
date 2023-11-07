package hycu.board.post.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class WritePostReqDTO {
    @NotEmpty(message = "title is required")
    String title;
    @NotEmpty(message = "contents is required")
    String contents;
}
