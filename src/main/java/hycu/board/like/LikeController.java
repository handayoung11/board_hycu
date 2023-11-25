package hycu.board.like;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeSvc likeSvc;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void likeOrUnlikePost(@RequestBody long postId) {
        likeSvc.likeOrUnlikePost(postId);
    }
}
