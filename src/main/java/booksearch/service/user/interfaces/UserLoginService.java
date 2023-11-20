package booksearch.service.user.interfaces;

import booksearch.model.attributesholder.interfaces.AttributesHolder;
import booksearch.model.entity.user.User;
import booksearch.service.user.implementations.LoginResult;

public interface UserLoginService {

    boolean isUserLogged(AttributesHolder attributesHolder);

    User receiveLoggedUser(AttributesHolder attributesHolder);

    boolean doesUserExist(String username, String password);

    LoginResult login(String username, String password, AttributesHolder attributesHolder);

    void logout(AttributesHolder attributesHolder);
}
