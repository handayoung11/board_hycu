package hycu.board.post;

import hycu.board.post.dto.PostDetailResDTO;
import hycu.board.post.dto.PostResDTO;
import hycu.board.post.dto.WritePostReqDTO;
import hycu.board.reply.ReplyRepo;
import hycu.board.users.Users;
import hycu.board.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final ReplyRepo replyRepo;

    public List<PostResDTO> getPosts() {
        return postRepo.findWithCreator();
    }

    public PostDetailResDTO getPost(long postId) {
        PostDetailResDTO dto = postRepo.findDetailById(postId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "존재하지 않거나 삭제된 게시글입니다."));
        dto.configComments(replyRepo.findWithCreatorByPostIdOrderByCreatedAtDesc(postId));
        return dto;
    }

    public void createPost(WritePostReqDTO dto) {
        postRepo.save(Post.createPost(dto, Users.createProxy(SecurityUtils.getId())));
    }
}
