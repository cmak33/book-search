package booksearch.service.user.implementations;

import booksearch.dao.interfaces.UserDao;
import booksearch.model.encoder.interfaces.Encoder;
import booksearch.model.user.User;
import booksearch.service.user.interfaces.UserRegisterService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserRegisterService implements UserRegisterService {

    private final UserDao userDao;
    private final Encoder encoder;

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
