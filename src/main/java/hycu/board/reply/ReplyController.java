package hycu.board.reply;

import hycu.board.reply.dto.WriteReplyReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplySvc replySvc;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void likeOrUnlikePost(@RequestBody WriteReplyReqDTO dto) {
        replySvc.write(dto);
    }
}
