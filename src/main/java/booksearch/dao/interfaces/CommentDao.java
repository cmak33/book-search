package booksearch.dao.interfaces;

import booksearch.model.entity.comment.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Long, Comment> {

    List<Comment> findByUserId(Object id);
}
