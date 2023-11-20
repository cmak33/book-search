package booksearch.service.comment.implementations;

import booksearch.dao.interfaces.CommentDao;
import booksearch.model.attributesholder.interfaces.AttributesHolder;
import booksearch.model.entity.comment.Comment;
import booksearch.model.entity.movie.Movie;
import booksearch.service.comment.interfaces.CommentService;
import booksearch.service.factory.dao.DaoFactory;
import booksearch.service.factory.service.ServiceFactory;
import booksearch.service.movie.implementations.CustomMovieService;
import booksearch.service.user.interfaces.UserLoginService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomCommentService implements CommentService {

    private final CommentDao commentDao;
    private final UserLoginService userLoginService;

    private CustomCommentService() {
        commentDao = DaoFactory.getCommentDao();
        userLoginService = ServiceFactory.getUserLoginService();
    }

    public static CustomCommentService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomCommentService INSTANCE = new CustomCommentService();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        commentDao.deleteById(id);
    }

    @Override
    public List<Comment> findAllCommentsForMovie(Long id) {
        return commentDao.findByMovieId(id);
    }

    @Override
    public List<Comment> findCommentsForMovie(Long movieId, int limit, int offset) {
        return commentDao.findByMovieId(movieId,limit,offset);
    }

    @Override
    public List<Comment> findAll(int limit, int offset) {
        return commentDao.findAll(limit,offset);
    }

    @Override
    public void saveCurrentUserComment(Comment comment, AttributesHolder attributesHolder) {
        comment.setPublisherUsername(userLoginService.receiveLoggedUser(attributesHolder).getUsername());
        commentDao.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentDao.update(comment);
    }
}
