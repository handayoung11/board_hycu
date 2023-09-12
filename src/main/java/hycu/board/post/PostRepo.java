package hycu.board.post;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = "creator")
    List<Post> findWithCreatorByActiveIsTrue();
}
