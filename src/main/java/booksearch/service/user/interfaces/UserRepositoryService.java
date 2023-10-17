package booksearch.service.user.interfaces;

import booksearch.exception.entity.EntityNotFoundException;
import booksearch.model.user.User;

public interface UserRepositoryService {

    User findById(Long id) throws EntityNotFoundException;

    void save(User user);

    void deleteById(Long id);
}
