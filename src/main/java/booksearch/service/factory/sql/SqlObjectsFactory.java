package booksearch.service.factory.sql;

import booksearch.service.jdbc.implementations.CustomJdbcConnectionPool;
import booksearch.service.jdbc.interfaces.JdbcConnectionPool;
import booksearch.service.sql.implementations.CustomEntitySqlExecutor;
import booksearch.service.sql.implementations.CustomSqlExecutor;
import booksearch.service.sql.interfaces.EntitySqlExecutor;
import booksearch.service.sql.interfaces.SqlExecutor;

public class SqlObjectsFactory {
    private SqlObjectsFactory(){}

    public static JdbcConnectionPool getJdbcConnectionPool(){
        return CustomJdbcConnectionPool.getInstance();
    }

    public static SqlExecutor getSqlExecutor(){
        return CustomSqlExecutor.getInstance();
    }

    public static EntitySqlExecutor getEntitySqlExecutor(){
        return CustomEntitySqlExecutor.getInstance();
    }
}
