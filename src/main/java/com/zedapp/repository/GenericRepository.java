package com.zedapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GenericRepository<T> extends CrudRepository<T, Long> {
    @Override
    List<T> findAll();

    Optional<T> findById(long id);

    @Override
    <S extends T> S save(S entity);

    void delete(T entity);

    default T findOrThrow(long id) throws EntityNotFoundException {
        T object = findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id: " + " was not found!"));
        return object;
    }
}
