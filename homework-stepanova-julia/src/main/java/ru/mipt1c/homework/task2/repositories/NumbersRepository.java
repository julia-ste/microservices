package ru.mipt1c.homework.task2.repositories;

import org.springframework.stereotype.Repository;
import ru.mipt1c.homework.task2.models.NumberEntity;

@Repository("numbersRepository")
public interface NumbersRepository extends IRepository<Integer, NumberEntity> {
}
