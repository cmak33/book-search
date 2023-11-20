package booksearch.web.servlets.movie;

import booksearch.configuration.FetchingConfiguration;
import booksearch.configuration.SessionAttributeNames;
import booksearch.model.attributesholder.implementation.HttpSessionAttributesHolder;
import booksearch.model.attributesholder.interfaces.AttributesHolder;
import booksearch.model.entity.comment.Comment;
import booksearch.model.entity.comment.CommentDto;
import booksearch.model.entity.movie.Movie;
import booksearch.service.comment.interfaces.CommentService;
import booksearch.service.factory.service.ServiceFactory;
import booksearch.service.movie.interfaces.MovieService;
import booksearch.service.user.interfaces.UserLoginService;
import booksearch.service.utility.Parser;
import booksearch.service.utility.RequestUtility;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MovieServlet extends HttpServlet {

    private MovieService movieService;
    private CommentService commentService;
    private UserLoginService userLoginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        movieService = ServiceFactory.getMovieService();
        commentService = ServiceFactory.getCommentService();
        userLoginService = ServiceFactory.getUserLoginService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Long> optionalId = RequestUtility.readResourceId(req.getPathInfo().substring(1),resp);
        if(optionalId.isPresent()) {
            Long id = optionalId.get();
            Optional<Movie> movie = movieService.findById(id);
            String pageStr = req.getParameter("page");
            if (movie.isPresent()) {
                req.setAttribute("movie", movie.get());
                int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
                int offset = (page - 1) * FetchingConfiguration.ENTITIES_SELECT_COUNT;
                String username = userLoginService.receiveLoggedUser(new HttpSessionAttributesHolder(req.getSession())).getUsername();
                List<CommentDto> comments = commentService.findCommentsForMovie(id, FetchingConfiguration.ENTITIES_SELECT_COUNT, offset).stream().map(x -> new CommentDto(x, x.getPublisherUsername().equals(username))).toList();
                req.setAttribute("comments", comments);
                req.setAttribute("page", page);
                req.setAttribute("isAdmin", req.getSession().getAttribute(SessionAttributeNames.IS_USER_ADMIN_ATTRIBUTE_NAME));
                req.getRequestDispatcher("/WEB-INF/pages/movie/movie.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
