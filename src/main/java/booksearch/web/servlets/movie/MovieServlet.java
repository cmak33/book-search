package booksearch.web.servlets.movie;

import booksearch.model.entity.movie.Movie;
import booksearch.service.comment.interfaces.CommentService;
import booksearch.service.factory.service.ServiceFactory;
import booksearch.service.movie.interfaces.MovieService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class MovieServlet extends HttpServlet {

    private MovieService movieService;
    private CommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        movieService = ServiceFactory.getMovieService();
        commentService = ServiceFactory.getCommentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getPathInfo().substring(1));
        Optional<Movie> movie = movieService.findById(id);
        if(movie.isPresent()){
            req.setAttribute("movie",movie.get());
            req.setAttribute("comments",commentService.findAllCommentsForMovie(id));
            req.getRequestDispatcher("/WEB-INF/pages/movie/movie.jsp").forward(req,resp);
        } else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
