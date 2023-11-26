package hycu.board.reply;

import hycu.board.post.Post;
import hycu.board.post.PostRepo;
import hycu.board.reply.dto.WriteReplyReqDTO;
import hycu.board.users.Users;
import hycu.board.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class ReplySvc {

    private final ReplyRepo replyRepo;
    private final PostRepo postRepo;

    public void writeReply(WriteReplyReqDTO dto) {
        Post post = postRepo.findById(dto.getPostId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."));
        Reply reply = new Reply(post, dto.getContents(), Users.createProxy(SecurityUtils.getId()));
        replyRepo.save(reply);
    }

    public void deleteReply(long replyId) {
        Reply reply = replyRepo.findById(replyId).orElse(null);
        if (reply == null) return;

        if (reply.getCreator().getId().equals(SecurityUtils.getIdWithoutEx())) replyRepo.delete(reply);
        else throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "댓글 작성자가 아닙니다.");
    }

    @Transactional
    public void updateReply(long replyId, String contents) {
        Reply reply = replyRepo.findById(replyId).orElseThrow(() ->
                new HttpClientErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다."));
        if (!reply.getCreator().getId().equals(SecurityUtils.getIdWithoutEx())) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
        reply.updateContents(contents);
    }
}
