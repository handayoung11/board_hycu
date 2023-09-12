package hycu.board.post;

import hycu.board.post.dto.PostResDTO;

import java.util.List;

public interface PostDslRepo {
    List<PostResDTO> findWithCreator();
}
