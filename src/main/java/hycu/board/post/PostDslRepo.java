package hycu.board.post;

import hycu.board.post.dto.PostDetailResDTO;
import hycu.board.post.dto.PostResDTO;

import java.util.List;
import java.util.Optional;

public interface PostDslRepo {
    List<PostResDTO> findActivePosts();
    Optional<PostDetailResDTO> findDetailById(long postId);
}
