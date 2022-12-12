package ru.mipt1c.homework.task2.repositories;

import org.springframework.stereotype.Repository;
import ru.mipt1c.homework.task2.models.StringEntity;

@Repository("stringsRepository")
public interface StringsRepository extends IRepository<String, StringEntity> {
}
