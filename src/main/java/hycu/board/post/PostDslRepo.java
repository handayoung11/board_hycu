package hycu.board.post;

import hycu.board.post.dto.PostDetailResDTO;
import hycu.board.post.dto.PostOrderBy;
import hycu.board.post.dto.PostResDTO;

import java.util.List;
import java.util.Optional;

public interface PostDslRepo {
    List<PostResDTO> findActivePosts(PostOrderBy o);
    Optional<PostDetailResDTO> findDetailById(long postId, long loggerId);
}
