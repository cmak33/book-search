package booksearch.service.user.implementations;

import booksearch.dao.interfaces.UserDao;
import booksearch.model.encoder.interfaces.Encoder;
import booksearch.model.entity.user.User;
import booksearch.service.factory.auxiliary.AuxiliaryFactory;
import booksearch.service.factory.dao.DaoFactory;
import booksearch.service.user.interfaces.UserRegisterService;
import lombok.RequiredArgsConstructor;

public class CustomUserRegisterService implements UserRegisterService {

    private final UserDao userDao;
    private final Encoder encoder;

    private CustomUserRegisterService() {
        userDao = DaoFactory.getUserDao();
        encoder = AuxiliaryFactory.getEncoder();
    }

    public static CustomUserRegisterService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomUserRegisterService INSTANCE = new CustomUserRegisterService();
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return userDao.findByUsername(username).isEmpty();
    }

    @Override
    public void register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        userDao.save(user);
    }
}
