package booksearch.web.servlets.movie;

import booksearch.model.entity.movie.Movie;
import booksearch.service.factory.service.ServiceFactory;
import booksearch.service.movie.interfaces.MovieService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

public class MovieCreationServlet extends HttpServlet {

    private MovieService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        movieService = ServiceFactory.getMovieService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/movie/movieCreation.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String imageUrl = req.getParameter("imageUrl");
        Date releaseDate = new Date(req.getParameter("releaseDate"));
        Movie movie = new Movie();
        movie.setDescription(description);
        movie.setTitle(title);
        movie.setImageUrl(imageUrl);
        movie.setReleaseDate(releaseDate);
        movieService.save(movie);
        resp.sendRedirect(String.format("%s/movie/create",req.getContextPath()));
    }
}
