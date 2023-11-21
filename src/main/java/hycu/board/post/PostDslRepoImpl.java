package hycu.board.post;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hycu.board.post.dto.PostDetailResDTO;
import hycu.board.post.dto.PostResDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static hycu.board.like.QLikes.likes;
import static hycu.board.post.QPost.post;
import static hycu.board.reply.QReply.reply;

@RequiredArgsConstructor
public class PostDslRepoImpl implements PostDslRepo {

    private final JPAQueryFactory factory;

    @Override
    public List<PostResDTO> findActivePosts() {
        JPAQuery<PostResDTO> select = factory.select(Projections.constructor(PostResDTO.class, post, likes.likeKey.user.count(), reply.count()));
        return getBaseQuery(select)
                .leftJoin(reply).on(reply.post.eq(post))
                .groupBy(post.id)
                .orderBy(post.createdAt.desc())
                .where(activeIsTrue()).fetch();
    }

    @Override
    public Optional<PostDetailResDTO> findDetailById(long postId) {
        PostDetailResDTO dto = null;
        try {
            JPAQuery<PostDetailResDTO> select = factory.select(Projections.constructor(PostDetailResDTO.class, post, likes.likeKey.user.count()));
            dto = getBaseQuery(select)
                    .where(activeIsTrue().and(post.id.eq(postId)))
                    .fetchOne();
        } catch(Exception e) {
            e.fillInStackTrace();
        }
        return Optional.ofNullable(dto);
    }

    public BooleanExpression activeIsTrue() {
        return post.active.isTrue();
    }

    public <T> JPAQuery<T> getBaseQuery(JPAQuery<T> select) {
        return select.from(post)
                .leftJoin(post.creator).fetchJoin()
                .leftJoin(likes).on(likes.likeKey.post.eq(post));
    }
}
