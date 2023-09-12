package hycu.board.board;

import hycu.board.board.dto.PostResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostResDTO> getPosts() {
        return postService.getPosts();
    }
}
