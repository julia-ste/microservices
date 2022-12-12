package ru.mipt1c.homework.task2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mipt1c.homework.task2.models.StudentEntity;
import ru.mipt1c.homework.task2.models.StudentId;

@Repository("studentsRepository")
public interface StudentsRepository extends JpaRepository<StudentEntity, StudentId> {
}
