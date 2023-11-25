package hycu.board.like;

import hycu.board.post.Post;
import hycu.board.post.PostRepo;
import hycu.board.users.Users;
import hycu.board.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeSvc {

    private final LikeRepo likeRepo;
    private final PostRepo postRepo;

    public void likeOrUnlikePost(long postId) {
        Post post = postRepo.findById(postId).orElseThrow(null);
        if (post == null) return;

        Long userId = SecurityUtils.getIdWithoutEx();
        likeRepo.findByPostAndUserId(post, userId)
                .ifPresentOrElse(likeRepo::delete, () ->
                        likeRepo.save(new Likes(new LikeKey(Users.createProxy(userId), post)))
                );
    }
}
