package booksearch.dao.interfaces;

import booksearch.model.entity.Entity;

import java.util.Optional;

public interface GenericDao<I,E extends Entity<I>> {

    Optional<E> findById(I id);

    void save(E entity);

    void deleteById(I id);
}
