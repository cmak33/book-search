package booksearch.service.movie.interfaces;

import booksearch.model.entity.comment.Comment;
import booksearch.model.entity.movie.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    void save(Movie movie);

    void deleteById(Long id);

    void update(Movie movie);

    Optional<Movie> findById(Long id);
}
