package booksearch.dao.implementations;

import booksearch.configuration.TableNamesConfiguration;
import booksearch.dao.interfaces.CommentDao;
import booksearch.model.entity.comment.Comment;
import booksearch.service.factory.sql.SqlObjectsFactory;
import booksearch.service.sql.interfaces.EntitySqlExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class CustomCommentDao extends DefaultGenericDao<Long, Comment> implements CommentDao {

    private CustomCommentDao() {
        super(SqlObjectsFactory.getEntitySqlExecutor(), TableNamesConfiguration.COMMENT_TABLE_NAME);
    }

    public static CustomCommentDao getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomCommentDao INSTANCE = new CustomCommentDao();
    }

    @Override
    protected Optional<Comment> createEntity(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                Comment comment = new Comment();
                comment.setMessage(resultSet.getString("message"));
                comment.setMovieId(resultSet.getLong("movieId"));
                comment.setId(resultSet.getLong("id"));
                comment.setPublisherUsername(resultSet.getString("publisherUsername"));
                return Optional.of(comment);
            }
        } catch (SQLException e){

        }
        return Optional.empty();
    }

    @Override
    public List<Comment> findByUserId(Object id) {
        List<Comment> result = new ArrayList<>();
        Consumer<ResultSet> consumer = (resultSet)->{
            Optional<Comment> comment = createEntity(resultSet);
            while(comment.isPresent()){
                result.add(comment.get());
                comment = createEntity(resultSet);
            }
        };
        try {
            getEntitySqlExecutor().select(getTable(), List.of("userId"), List.of(id.toString()), consumer);
        } catch (SQLException sqlException){

        }
        return result;
    }
}
