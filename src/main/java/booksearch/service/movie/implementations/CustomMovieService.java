package booksearch.service.movie.implementations;

import booksearch.dao.interfaces.MovieDao;
import booksearch.model.entity.comment.Comment;
import booksearch.model.entity.movie.Movie;
import booksearch.service.factory.auxiliary.AuxiliaryFactory;
import booksearch.service.factory.dao.DaoFactory;
import booksearch.service.movie.interfaces.MovieService;
import booksearch.service.user.implementations.CustomUserLoginService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

public class CustomMovieService implements MovieService {

    private final MovieDao movieDao;

    private CustomMovieService() {
        movieDao = DaoFactory.getMovieDao();
    }

    public static CustomMovieService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomMovieService INSTANCE = new CustomMovieService();
    }

    @Override
    public void save(Movie movie) {
        movieDao.save(movie);
    }

    @Override
    public void deleteById(Long id) {
        movieDao.deleteById(id);
    }

    @Override
    public void update(Movie movie) {
        movieDao.update(movie);
    }

    @Override
    public List<Movie> findByQuery(String query, int limit, int offset) {
        return movieDao.findByQuery(query,limit,offset);
    }

    @Override
    public List<Movie> findAll(int limit, int offset) {
        return movieDao.findAll(limit,offset);
    }


    @Override
    public Optional<Movie> findById(Long id) {
        return movieDao.findById(id);
    }
}
