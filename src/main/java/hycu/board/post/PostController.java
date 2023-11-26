package hycu.board.post;

import hycu.board.post.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostResDTO> getPosts(@RequestParam(required = false, defaultValue = "T") PostOrderBy orderBy) {
        return postService.getPosts(orderBy);
    }

    @GetMapping("{postId}")
    public PostDetailResDTO getPostDetail(@PathVariable long postId) {
        return postService.getPost(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody @Valid WritePostReqDTO dto) {
        postService.createPost(dto);
    }

    @PatchMapping("{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@RequestBody @Valid UpdatePostReqDTO dto, @PathVariable Long postId) {
        postService.updatePost(postId, dto);
    }

    @DeleteMapping("{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable long postId) {
        postService.deletePost(postId);
    }
}
