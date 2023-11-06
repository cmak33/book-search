package booksearch.service.comment.interfaces;

import booksearch.model.attributesholder.interfaces.AttributesHolder;
import booksearch.model.entity.comment.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(Long id);

    void deleteById(Long id);

    List<Comment> findAllCommentsForMovie(Long movieId);

    void saveCurrentUserComment(Comment comment, AttributesHolder attributesHolder);

    void update(Comment comment);
}
