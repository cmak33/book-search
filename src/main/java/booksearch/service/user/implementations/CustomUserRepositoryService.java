package booksearch.service.user.implementations;

import booksearch.dao.interfaces.UserDao;
import booksearch.exception.entity.EntityNotFoundException;
import booksearch.model.user.User;
import booksearch.service.user.interfaces.UserRepositoryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserRepositoryService implements UserRepositoryService {

    private final UserDao userDao;

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
