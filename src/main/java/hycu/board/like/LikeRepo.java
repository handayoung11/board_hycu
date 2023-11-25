package hycu.board.like;


import hycu.board.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepo extends JpaRepository<Likes, Long> {

    @Query("select l from Likes l where l.likeKey.post = :post and l.likeKey.user.id = :userId")
    Optional<Likes> findByPostAndUserId(Post post, long userId);
}
