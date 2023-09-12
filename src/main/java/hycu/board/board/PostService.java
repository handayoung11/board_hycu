package hycu.board.board;

import hycu.board.board.dto.PostResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;

    public List<PostResDTO> getPosts() {
        return postRepo.findAll().stream().map(p -> new PostResDTO(p)).toList();
    }
}
