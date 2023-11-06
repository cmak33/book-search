package booksearch.service.user.implementations;

import booksearch.configuration.SessionAttributeNames;
import booksearch.dao.interfaces.UserDao;
import booksearch.exception.authentication.UserNotLoggedException;
import booksearch.model.attributesholder.interfaces.AttributesHolder;
import booksearch.model.encoder.interfaces.Encoder;
import booksearch.model.entity.user.Status;
import booksearch.model.entity.user.User;
import booksearch.service.factory.auxiliary.AuxiliaryFactory;
import booksearch.service.factory.dao.DaoFactory;
import booksearch.service.user.interfaces.UserLoginService;

import java.util.Optional;

public class CustomUserLoginService implements UserLoginService {

    private final UserDao userDao;
    private final Encoder encoder;

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
    public boolean login(String username, String password, AttributesHolder attributesHolder) {
        Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty() || user.get().getStatus().equals(Status.BLOCKED)) {
            return false;
        }
        attributesHolder.setAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME, username);
        return true;
    }

    @Override
    public void logout(AttributesHolder attributesHolder) {
        attributesHolder.deleteAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME);
    }
}
