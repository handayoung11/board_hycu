package hycu.board.post;

import hycu.board.post.dto.PostDetailResDTO;
import hycu.board.post.dto.PostResDTO;
import hycu.board.reply.ReplyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        PostDetailResDTO dto = postRepo.findDetailById(postId);
        dto.configComments(replyRepo.findWithCreatorByPostIdOrderByCreatedAtDesc(postId));
        return dto;
    }
}
