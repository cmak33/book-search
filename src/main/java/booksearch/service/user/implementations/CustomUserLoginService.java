package booksearch.service.user.implementations;

import booksearch.configuration.SessionAttributeNames;
import booksearch.dao.interfaces.UserDao;
import booksearch.exception.authentication.UserNotLoggedException;
import booksearch.model.attributesholder.interfaces.AttributesHolder;
import booksearch.model.encoder.interfaces.Encoder;
import booksearch.model.entity.user.Role;
import booksearch.model.entity.user.Status;
import booksearch.model.entity.user.User;
import booksearch.service.factory.auxiliary.AuxiliaryFactory;
import booksearch.service.factory.dao.DaoFactory;
import booksearch.service.user.interfaces.UserLoginService;
import lombok.extern.java.Log;

import java.util.Optional;

@Log
public class CustomUserLoginService implements UserLoginService {

    private UserDao userDao;
    private Encoder encoder;

    private CustomUserLoginService() {
        userDao = DaoFactory.getUserDao();
        encoder = AuxiliaryFactory.getEncoder();
    }

    public static CustomUserLoginService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomUserLoginService INSTANCE = new CustomUserLoginService();
    }

    @Override
    public boolean isUserLogged(AttributesHolder attributesHolder) {
        return attributesHolder.containsAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME);
    }

    @Override
    public User receiveLoggedUser(AttributesHolder attributesHolder) throws UserNotLoggedException {
        User user = (User) attributesHolder.getAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME);
        if (user == null) {
            log.severe("User is not logged");
            throw new UserNotLoggedException();
        }
        return user;
    }

    @Override
    public boolean doesUserExist(String username, String password) {
        Optional<User> user = userDao.findByUsername(username);
        String encodedPassword = encoder.encode(password);
        return user.isPresent() && user.get().getPassword().equals(encodedPassword);
    }

    @Override
    public LoginResult login(String username, String password, AttributesHolder attributesHolder) {
        Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty() || !isPasswordCorrect(user.get(),password)) {
            return LoginResult.WRONG_USERNAME_OR_PASSWORD;
        } else if(user.get().getStatus().equals(Status.BLOCKED)){
            return LoginResult.USER_IS_BLOCKED;
        }
        attributesHolder.setAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME, user.get());
        attributesHolder.setAttribute(SessionAttributeNames.IS_USER_ADMIN_ATTRIBUTE_NAME,user.get().getRole().equals(Role.ADMIN));
        return LoginResult.SUCCESS;
    }

    private boolean isPasswordCorrect(User user,String password){
        return encoder.encode(password).equals(user.getPassword());
    }

    @Override
    public void logout(AttributesHolder attributesHolder) {
        attributesHolder.deleteAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME);
    }
}
