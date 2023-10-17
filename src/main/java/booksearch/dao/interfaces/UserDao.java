package booksearch.dao.interfaces;

import booksearch.dao.interfaces.GenericDao;
import booksearch.model.user.User;
import booksearch.service.sql.interfaces.SqlExecutor;

import java.util.Optional;

public interface UserDao extends GenericDao<Long, User> {


}
