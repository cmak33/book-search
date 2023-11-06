package booksearch.service.factory.dao;

import booksearch.dao.implementations.CustomCommentDao;
import booksearch.dao.implementations.CustomMovieDao;
import booksearch.dao.implementations.CustomUserDao;
import booksearch.dao.interfaces.CommentDao;
import booksearch.dao.interfaces.MovieDao;
import booksearch.dao.interfaces.UserDao;

public class DaoFactory {

    private DaoFactory(){}

    public static MovieDao getMovieDao(){
        return CustomMovieDao.getInstance();
    }

    public static CommentDao getCommentDao(){
        return CustomCommentDao.getInstance();
    }

    public static UserDao getUserDao(){
        return CustomUserDao.getInstance();
    }
}
