package hycu.board.post;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hycu.board.post.dto.PostResDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static hycu.board.like.QLikes.likes;
import static hycu.board.post.QPost.post;

@RequiredArgsConstructor
public class PostDslRepoImpl implements PostDslRepo {

    private final JPAQueryFactory factory;

    @Override
    public List<PostResDTO> findWithCreator() {
        return factory.select(Projections.constructor(PostResDTO.class, post, likes.likeKey.user.count())).from(post)
                .leftJoin(post.creator).fetchJoin()
                .leftJoin(likes).on(likes.likeKey.post.eq(post))
                .groupBy(post.id)
                .orderBy(post.createdAt.desc())
                .where(post.active.isTrue())
                .fetch();
    }
}
