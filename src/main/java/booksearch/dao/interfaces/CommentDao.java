package booksearch.dao.interfaces;

import booksearch.model.entity.comment.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Long, Comment> {

    List<Comment> findByMovieId(Long id);

    List<Comment> findByMovieId(Long id, int limit, int offset);
}
