package booksearch.dao.implementations;

import booksearch.configuration.TableNamesConfiguration;
import booksearch.dao.interfaces.MovieDao;
import booksearch.model.entity.comment.Comment;
import booksearch.model.entity.movie.Movie;
import booksearch.service.factory.sql.SqlObjectsFactory;
import booksearch.service.sql.interfaces.EntitySqlExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class CustomMovieDao extends DefaultGenericDao<Long, Movie> implements MovieDao {

    private CustomMovieDao() {
        super(SqlObjectsFactory.getEntitySqlExecutor(), TableNamesConfiguration.MOVIE_TABLE_NAME);
    }

    public static CustomMovieDao getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomMovieDao INSTANCE = new CustomMovieDao();
    }

    @Override
    protected Optional<Movie> createEntity(ResultSet resultSet) {
        try{
            if (resultSet.next()) {
                Movie movie = new Movie();
                movie.setReleaseDate(new Date(resultSet.getString("releaseDate")));
                movie.setTitle(resultSet.getString("title"));
                movie.setImageUrl(resultSet.getString("imageUrl"));
                movie.setDescription(resultSet.getString("description"));
                return Optional.of(movie);
            }
        } catch (SQLException e){

        }

        return Optional.empty();
    }
}
