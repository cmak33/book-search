package booksearch.service.sql.interfaces;

import booksearch.model.pair.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public interface SqlExecutor {

    void update(String table, Collection<String> columns, Pair<String,String> id, Collection<String> newValues) throws SQLException;

    void insert(String table, Collection<String> columns, Collection<String> values) throws SQLException;

    void select(String table, Collection<String> columns, Collection<String> values, Consumer<ResultSet> resultSetConsumer) throws SQLException;

    void delete(String table, Collection<String> columns, Collection<String> values) throws SQLException;

    void executeNonReturn(String sql) throws SQLException;

    void executeSql(String sql, Consumer<ResultSet> resultSetConsumer) throws SQLException;
}
