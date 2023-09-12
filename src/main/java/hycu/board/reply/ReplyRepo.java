package hycu.board.reply;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepo extends JpaRepository<Reply, Long> {

    @EntityGraph(attributePaths = "creator")
    List<Reply> findWithCreatorByPostIdOrderByCreatedAtDesc(long postId);
}
