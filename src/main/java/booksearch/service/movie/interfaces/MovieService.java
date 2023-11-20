package booksearch.service.movie.interfaces;

import booksearch.model.entity.comment.Comment;
import booksearch.model.entity.movie.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    void save(Movie movie);

    void deleteById(Long id);

    void update(Movie movie);

    List<Movie> findByQuery(String query, int limit, int offset);

    List<Movie> findAll(int limit, int offset);

    Optional<Movie> findById(Long id);
}
