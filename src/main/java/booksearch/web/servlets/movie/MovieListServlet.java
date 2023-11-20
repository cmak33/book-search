package booksearch.web.servlets.movie;

import booksearch.configuration.FetchingConfiguration;
import booksearch.configuration.SessionAttributeNames;
import booksearch.model.entity.movie.Movie;
import booksearch.service.factory.service.ServiceFactory;
import booksearch.service.movie.interfaces.MovieService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


public class MovieListServlet extends HttpServlet {

    private MovieService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        movieService = ServiceFactory.getMovieService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        String pageString = req.getParameter("page");
        int page = pageString==null?1:Integer.parseInt(pageString);
        List<Movie> movies = findMovies(query,page);
        req.setAttribute("movies",movies);
        req.setAttribute("page",page);
        req.setAttribute("isAdmin",req.getSession().getAttribute(SessionAttributeNames.IS_USER_ADMIN_ATTRIBUTE_NAME));
        req.getRequestDispatcher("/WEB-INF/pages/movie/movieList.jsp").forward(req,resp);
    }

    private List<Movie> findMovies(String query, int page){
        int offset = (page-1)* FetchingConfiguration.ENTITIES_SELECT_COUNT;
        if(query == null){
            return movieService.findAll(FetchingConfiguration.ENTITIES_SELECT_COUNT,offset);
        } else{
            return movieService.findByQuery(query,FetchingConfiguration.ENTITIES_SELECT_COUNT,offset);
        }
    }
}
