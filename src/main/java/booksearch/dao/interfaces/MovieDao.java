package booksearch.dao.interfaces;

import booksearch.model.entity.movie.Movie;

import java.util.List;

public interface MovieDao extends GenericDao<Long, Movie>{

    List<Movie> findByQuery(String query, int limit, int offset);
}
