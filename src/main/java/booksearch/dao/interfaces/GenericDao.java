package booksearch.dao.interfaces;

import booksearch.model.entity.interfaces.Entity;

import java.util.Optional;

public interface GenericDao<I,E extends Entity<I>> {

    Optional<E> findById(I id);

    void update(E entity);

    void save(E entity);

    void deleteById(I id);
}
