package by.clowns.service;

import java.util.Set;

public interface ServiceInterface<T> {

    void create(T entity);

    Set<T> read();

    void update(T entity, long id);

    void delete(long id);

    T get(long id);

}