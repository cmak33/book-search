package booksearch.service.user.implementations;

import booksearch.dao.interfaces.UserDao;
import booksearch.exception.entity.EntityNotFoundException;
import booksearch.model.entity.user.User;
import booksearch.service.factory.auxiliary.AuxiliaryFactory;
import booksearch.service.factory.dao.DaoFactory;
import booksearch.service.user.interfaces.UserRepositoryService;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class CustomUserRepositoryService implements UserRepositoryService {

    private final UserDao userDao;

    private CustomUserRepositoryService() {
        userDao = DaoFactory.getUserDao();
    }

    private static class Holder {
        private static final CustomUserRepositoryService INSTANCE = new CustomUserRepositoryService();
    }

    public static CustomUserRepositoryService getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
