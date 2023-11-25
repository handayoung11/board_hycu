package hycu.board.post;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hycu.board.like.Likes;
import hycu.board.post.dto.PostDetailResDTO;
import hycu.board.post.dto.PostResDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static hycu.board.like.QLikes.likes;
import static hycu.board.post.QPost.post;
import static hycu.board.reply.QReply.reply;

@RequiredArgsConstructor
public class PostDslRepoImpl implements PostDslRepo {

    private final JPAQueryFactory factory;

    @Override
    public List<PostResDTO> findActivePosts() {
        JPAQuery<PostResDTO> select = factory.select(Projections.constructor(PostResDTO.class, post, likes.likeKey.user.count()));
        List<PostResDTO> posts = getBaseQuery(select)
                .groupBy(post.id)
                .orderBy(post.createdAt.desc())
                .where(activeIsTrue()).fetch();

        List<Tuple> commentCount = factory.select(post.id, reply.count())
                .from(post)
                .leftJoin(reply).on(post.eq(reply.post))
                .orderBy(post.createdAt.desc())
                .groupBy(post.id)
                .where(activeIsTrue()).fetch();

        Map<Long, Long> map = commentCount.stream()
                .collect(Collectors.toMap(t -> t.get(post.id), t -> t.get(reply.count())));
        for (PostResDTO p : posts) p.updateCommentsCount(map.get(p.getId()));

        return posts;
    }

    @Override
    public Optional<PostDetailResDTO> findDetailById(long postId, long loggerId) {
        PostDetailResDTO dto = null;
        try {
            JPAQuery<PostDetailResDTO> select = factory.select(Projections.constructor(PostDetailResDTO.class, post, likes.likeKey.user.count()));
            dto = getBaseQuery(select)
                    .where(activeIsTrue().and(post.id.eq(postId)))
                    .fetchOne();

            Likes l = factory.selectFrom(likes)
                    .where(likes.likeKey.user.id.eq(loggerId)
                            .and(likes.likeKey.post.id.eq(dto.getId())))
                    .fetchOne();
            if (l != null) dto.updateClickLike();

        } catch (Exception e) {
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