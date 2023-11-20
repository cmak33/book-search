package booksearch.dao.implementations;

import booksearch.configuration.TableNamesConfiguration;
import booksearch.dao.interfaces.MovieDao;
import booksearch.model.entity.comment.Comment;
import booksearch.model.entity.movie.Movie;
import booksearch.service.factory.sql.SqlObjectsFactory;
import booksearch.service.sql.interfaces.EntitySqlExecutor;
import lombok.extern.java.Log;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Log
public class CustomMovieDao extends DefaultGenericDao<Long, Movie> implements MovieDao {

    private CustomMovieDao() {
        super(SqlObjectsFactory.getEntitySqlExecutor(), TableNamesConfiguration.MOVIE_TABLE_NAME);
    }

    public static CustomMovieDao getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<Movie> findByQuery(String query, int limit, int offset) {
        List<Movie> result = new ArrayList<>();
        query = "%"+query+"%";
        query = String.format("'%s'",query);
        String sql = String.format("SELECT * FROM %s WHERE title LIKE %s OR description LIKE %s LIMIT %d OFFSET %d",getTable(),query,query,limit,offset);
        Consumer<ResultSet> consumer = (resultSet)->{
            Optional<Movie> entity = createEntity(resultSet);
            while(entity.isPresent()){
                result.add(entity.get());
                entity = createEntity(resultSet);
            }
        };
        try {
            getEntitySqlExecutor().executeSql(sql, consumer);
        } catch (SQLException exception){
            log.warning(exception.getMessage());
        }
        return result;
    }

    private static class Holder {
        private static final CustomMovieDao INSTANCE = new CustomMovieDao();
    }

    @Override
    protected Optional<Movie> createEntity(ResultSet resultSet) {
        try{
            if (resultSet.next()) {
                Movie movie = new Movie();
                movie.setReleaseDate(Date.valueOf(resultSet.getString("releaseDate")));
                movie.setTitle(resultSet.getString("title"));
                movie.setImageUrl(resultSet.getString("imageUrl"));
                movie.setDescription(resultSet.getString("description"));
                movie.setId(Long.valueOf(resultSet.getString("id")));
                return Optional.of(movie);
            }
        } catch (SQLException e){
            log.warning(e.getMessage());
        }

        return Optional.empty();
    }
}
