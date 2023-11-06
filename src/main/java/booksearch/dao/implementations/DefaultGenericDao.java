package booksearch.dao.implementations;

import booksearch.dao.interfaces.GenericDao;
import booksearch.model.entity.interfaces.Entity;
import booksearch.service.sql.interfaces.EntitySqlExecutor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public abstract class DefaultGenericDao<I,E extends Entity<I>> implements GenericDao<I,E> {

    private final EntitySqlExecutor entitySqlExecutor;
    private final String table;

    @Override
    public Optional<E> findById(I id) {
        var obj = new Object(){
                public Optional<E> entity;
        };
        Consumer<ResultSet> consumer = (resultSet)->{
            obj.entity = createEntity(resultSet);
        };
        try {
            entitySqlExecutor.select(table, id.toString(), consumer);
        } catch (SQLException sqlException){
            obj.entity = Optional.empty();
        }
        return obj.entity;
    }

    protected abstract Optional<E> createEntity(ResultSet resultSet);

    @Override
    public void update(E entity){
        try {
            entitySqlExecutor.update(table, entity);
        } catch (SQLException e){

        }
    }

    @Override
    public void save(E entity) {
        try {
            entitySqlExecutor.insert(table, entity);
        } catch (SQLException e){

        }
    }

    @Override
    public void deleteById(I id) {
        try {
            entitySqlExecutor.delete(table, id);
        } catch (SQLException e){

        }
    }
}
