package hycu.board.post;

import hycu.board.post.dto.PostResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;

    public List<PostResDTO> getPosts() {
        return postRepo.findWithCreator();
    }
}
