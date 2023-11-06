package booksearch.dao.implementations;

import booksearch.configuration.TableNamesConfiguration;
import booksearch.dao.interfaces.UserDao;
import booksearch.model.entity.movie.Movie;
import booksearch.model.entity.user.Role;
import booksearch.model.entity.user.Status;
import booksearch.model.entity.user.User;
import booksearch.service.factory.sql.SqlObjectsFactory;
import booksearch.service.reflection.ReflectionFieldValuesGetter;
import booksearch.service.sql.implementations.CustomEntitySqlExecutor;
import booksearch.service.sql.interfaces.EntitySqlExecutor;

import javax.swing.text.TabableView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class CustomUserDao extends DefaultGenericDao<Long, User>  implements UserDao {

    private CustomUserDao() {
        super(SqlObjectsFactory.getEntitySqlExecutor(), TableNamesConfiguration.USER_TABLE_NAME);
    }

    public static CustomUserDao getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomUserDao INSTANCE = new CustomUserDao();
    }

    @Override
    protected Optional<User> createEntity(ResultSet resultSet) {
        try{
            if (resultSet.next()) {
                User user = new User();
                user.setBio(resultSet.getString("bio"));
                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getLong("id"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setStatus(Status.valueOf(resultSet.getString("status")));
                return Optional.of(user);
            }
        } catch (SQLException e){

        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var obj = new Object(){
            public Optional<User> entity;
        };
        Consumer<ResultSet> consumer = (resultSet)->{
            obj.entity = createEntity(resultSet);
        };
        try {
            getEntitySqlExecutor().select(getTable(), List.of("username"),List.of(username),consumer);
        } catch (SQLException sqlException){
            obj.entity = Optional.empty();
        }
        return obj.entity;
    }
}
