package by.clowns.dao;

import by.clowns.entity.BaseEntity;

import java.util.Set;

public interface Dao <T extends BaseEntity> {

    void save(T entity);

    Set<T> findAll();

    void update(T entity, long id);

    void delete(long id);

    T findById(long id);

    void close();
}
