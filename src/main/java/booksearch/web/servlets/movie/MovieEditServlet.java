package booksearch.web.servlets.movie;

import booksearch.model.entity.movie.Movie;
import booksearch.service.factory.service.ServiceFactory;
import booksearch.service.movie.interfaces.MovieService;
import booksearch.service.utility.Parser;
import booksearch.service.utility.RequestUtility;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Optional;

public class MovieEditServlet extends HttpServlet {

    private MovieService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        movieService = ServiceFactory.getMovieService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Long> optionalId = RequestUtility.readResourceId(req.getPathInfo().substring(1),resp);
        if(optionalId.isPresent()) {
            Optional<Movie> movieOptional = movieService.findById(optionalId.get());
            if (movieOptional.isPresent()) {
                req.setAttribute("movie", movieOptional.get());
                req.getRequestDispatcher("/WEB-INF/pages/movie/movieEdit.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Long> optionalId = RequestUtility.readResourceId(req.getPathInfo().substring(1),resp);
        if(optionalId.isPresent()) {
            Long id = optionalId.get();
            Optional<Movie> movieOptional = movieService.findById(id);
            if (movieOptional.isPresent()) {
                String title = req.getParameter("title");
                String description = req.getParameter("description");
                String imageUrl = req.getParameter("imageUrl");
                Date releaseDate = Date.valueOf(req.getParameter("releaseDate"));
                Movie movie = movieOptional.get();
                movie.setDescription(description);
                movie.setTitle(title);
                movie.setImageUrl(imageUrl);
                movie.setReleaseDate(releaseDate);
                movieService.update(movie);
                resp.sendRedirect(String.format("%s/movie/edit/%d", req.getContextPath(), id));
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
