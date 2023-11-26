package hycu.board.post;

import hycu.board.post.dto.*;
import hycu.board.reply.ReplyRepo;
import hycu.board.users.Users;
import hycu.board.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final ReplyRepo replyRepo;

    public List<PostResDTO> getPosts(PostOrderBy o) {
        return postRepo.findActivePosts(o);
    }

    public PostDetailResDTO getPost(long postId) {
        PostDetailResDTO dto = postRepo.findDetailById(postId, SecurityUtils.getIdWithoutEx())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "존재하지 않거나 삭제된 게시글입니다."));
        dto.configComments(replyRepo.findWithCreatorByPostIdOrderByCreatedAtDesc(postId));
        return dto;
    }

    public void createPost(WritePostReqDTO dto) {
        postRepo.save(Post.createPost(dto, Users.createProxy(SecurityUtils.getId())));
    }

    @Transactional
    public void updatePost(Long postId, UpdatePostReqDTO dto) {
        Post post = postRepo.findById(postId).orElseThrow(() ->
                new HttpClientErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."));
        if (!post.getCreator().getId().equals(SecurityUtils.getIdWithoutEx())) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
        post.update(dto);
    }

    @Transactional
    public void deletePost(long postId) {
        Post post = postRepo.findById(postId).orElse(null);
        if (post == null) return;

        if (post.getCreator().getId().equals(SecurityUtils.getIdWithoutEx())) post.delete();
        else throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "글 작성자가 아닙니다.");
    }
}
