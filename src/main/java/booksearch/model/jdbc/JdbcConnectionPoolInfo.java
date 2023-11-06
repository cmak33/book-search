package booksearch.model.jdbc;

public record JdbcConnectionPoolInfo(String url, String user, String password, int initialConnections) {
}
