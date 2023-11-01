package booksearch.service.user.interfaces;

import booksearch.model.attributesholder.interfaces.AttributesHolder;
import booksearch.model.entity.user.User;

public interface UserLoginService {

    boolean isUserLogged(AttributesHolder attributesHolder);

    User receiveLoggedUser(AttributesHolder attributesHolder);

    boolean doesUserExist(String username, String password);

    boolean login(String username, String password, AttributesHolder attributesHolder);

    void logout(AttributesHolder attributesHolder);
}
