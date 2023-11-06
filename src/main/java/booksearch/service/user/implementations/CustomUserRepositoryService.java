package booksearch.service.user.implementations;

import booksearch.dao.interfaces.UserDao;
import booksearch.exception.entity.EntityNotFoundException;
import booksearch.model.entity.user.User;
import booksearch.service.factory.auxiliary.AuxiliaryFactory;
import booksearch.service.factory.dao.DaoFactory;
import booksearch.service.user.interfaces.UserRepositoryService;
import lombok.RequiredArgsConstructor;

public class CustomUserRepositoryService implements UserRepositoryService {

    private final UserDao userDao;

    private CustomUserRepositoryService() {
        userDao = DaoFactory.getUserDao();
    }

    public static CustomUserRepositoryService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomUserRepositoryService INSTANCE = new CustomUserRepositoryService();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id));
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
