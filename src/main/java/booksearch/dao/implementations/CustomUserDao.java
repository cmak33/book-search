package booksearch.dao.implementations;

import booksearch.configuration.TableNamesConfiguration;
import booksearch.dao.interfaces.UserDao;
import booksearch.model.entity.user.Role;
import booksearch.model.entity.user.Status;
import booksearch.model.entity.user.User;
import booksearch.service.factory.sql.SqlObjectsFactory;
import lombok.extern.java.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Log
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
            log.warning(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        username = String.format("'%s'",username);
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
            log.warning(sqlException.getMessage());
        }
        return obj.entity;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        Consumer<ResultSet> consumer = (resultSet)->{
            Optional<User> user = createEntity(resultSet);
            while(user.isPresent()){
                result.add(user.get());
                user = createEntity(resultSet);
            }
        };
        try {
            getEntitySqlExecutor().selectAll(getTable(),consumer);
        } catch (SQLException sqlException){
            log.warning(sqlException.getMessage());
        }
        return result;
    }
}
