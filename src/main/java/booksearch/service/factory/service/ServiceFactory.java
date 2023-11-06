package booksearch.service.factory.service;

import booksearch.dao.implementations.CustomCommentDao;
import booksearch.dao.interfaces.CommentDao;
import booksearch.service.comment.implementations.CustomCommentService;
import booksearch.service.comment.interfaces.CommentService;
import booksearch.service.movie.implementations.CustomMovieService;
import booksearch.service.movie.interfaces.MovieService;
import booksearch.service.user.implementations.CustomUserLoginService;
import booksearch.service.user.implementations.CustomUserRegisterService;
import booksearch.service.user.implementations.CustomUserRepositoryService;
import booksearch.service.user.interfaces.UserLoginService;
import booksearch.service.user.interfaces.UserRegisterService;
import booksearch.service.user.interfaces.UserRepositoryService;

public class ServiceFactory {

    private ServiceFactory(){
    }

    public static UserLoginService getUserLoginService(){
        return CustomUserLoginService.getInstance();
    }

    public static UserRegisterService getUserRegisterService(){
        return CustomUserRegisterService.getInstance();
    }

    public static UserRepositoryService getUserRepositoryService(){
        return CustomUserRepositoryService.getInstance();
    }

    public static MovieService getMovieService(){
        return CustomMovieService.getInstance();
    }

    public static CommentService getCommentService(){
        return CustomCommentService.getInstance();
    }
}
