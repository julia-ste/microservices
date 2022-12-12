package ru.mipt1c.homework.task2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface IRepository<K, Entity> extends JpaRepository<Entity, Integer> {
    Optional<Entity> findByKey(K key);

    Boolean existsByKey(K key);

    void deleteByKey(K key);
}
