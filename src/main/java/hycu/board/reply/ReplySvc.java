package hycu.board.reply;

import hycu.board.post.Post;
import hycu.board.post.PostRepo;
import hycu.board.reply.dto.WriteReplyReqDTO;
import hycu.board.users.Users;
import hycu.board.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class ReplySvc {

    private final ReplyRepo replyRepo;
    private final PostRepo postRepo;

    public void write(WriteReplyReqDTO dto) {
        Post post = postRepo.findById(dto.getPostId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."));
        Reply reply = new Reply(post, dto.getContents(), Users.createProxy(SecurityUtils.getId()));
        replyRepo.save(reply);
    }
}
