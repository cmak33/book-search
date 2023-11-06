package booksearch.web.servlets.comment;

import booksearch.dao.interfaces.CommentDao;
import booksearch.model.attributesholder.implementation.HttpSessionAttributesHolder;
import booksearch.model.entity.comment.Comment;
import booksearch.service.comment.interfaces.CommentService;
import booksearch.service.factory.service.ServiceFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CommentServlet extends HttpServlet {

    private CommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        commentService = ServiceFactory.getCommentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Comment comment = new Comment();
        comment.setMessage(req.getParameter("message"));
        comment.setMovieId(Long.parseLong(req.getParameter("movieId")));
        commentService.saveCurrentUserComment(comment,new HttpSessionAttributesHolder(req.getSession()));
        resp.sendRedirect(req.getRequestURL().toString());
    }
}
