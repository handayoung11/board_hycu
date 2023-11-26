package hycu.board.reply;

import hycu.board.reply.dto.UpdateReplyReqDTO;
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
    public void writeReply(@RequestBody WriteReplyReqDTO dto) {
        replySvc.writeReply(dto);
    }

    @DeleteMapping("{replyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReply(@PathVariable long replyId) {
        replySvc.deleteReply(replyId);
    }

    @PostMapping("{replyId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateReply(@PathVariable long replyId, @RequestBody UpdateReplyReqDTO dto) {
        replySvc.updateReply(replyId, dto.getContents());
    }
}
