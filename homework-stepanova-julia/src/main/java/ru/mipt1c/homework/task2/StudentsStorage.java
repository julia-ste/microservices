package ru.mipt1c.homework.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.mipt1c.homework.task2.exceptions.MalformedDataException;
import ru.mipt1c.homework.task2.models.StudentEntity;
import ru.mipt1c.homework.task2.models.StudentId;
import ru.mipt1c.homework.task2.repositories.StudentsRepository;
import ru.mipt1c.homework.tests.task2.Student;
import ru.mipt1c.homework.tests.task2.StudentKey;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Component("studentsStorage")
public class StudentsStorage implements KeyValueStorage<StudentKey, Student> {
    @Autowired
    @Qualifier("studentsRepository")
    private StudentsRepository repository;
    private boolean isClosed = false;

    private void checkIsClosed() {
        if (isClosed) {
            throw new MalformedDataException("Access to closed database file");
        }
    }

    StudentId getIdByKey(StudentKey key) {
        StudentId id = new StudentId();
        id.setGroupId(key.getGroupId());
        id.setName(key.getName());
        return id;
    }

    @Override
    public Student read(StudentKey key) {
        checkIsClosed();
        Optional<StudentEntity> found = repository.findById(getIdByKey(key));
        if (found.isPresent()) {
            StudentEntity entity = found.get();
            return new Student(
                    entity.getGroupId(),
                    entity.getName(),
                    entity.getHometown(),
                    entity.getBirthDate(),
                    entity.isHasDormitory(),
                    entity.getAverageScore()
            );
        }
        return null;
    }

    @Override
    public boolean exists(StudentKey key) {
        checkIsClosed();
        return repository.existsById(getIdByKey(key));
    }

    @Override
    public void write(StudentKey key, Student value) {
        checkIsClosed();
        Optional<StudentEntity> found = repository.findById(getIdByKey(key));
        StudentEntity entity;
        if (found.isPresent()) {
            entity = found.get();
            entity.setGroupId(key.getGroupId());
            entity.setName(key.getName());
            entity.setHometown(value.getHometown());
            entity.setBirthDate(value.getBirthDate());
            entity.setHasDormitory(value.isHasDormitory());
            entity.setAverageScore(value.getAverageScore());
        } else {
            entity = new StudentEntity(
                    key.getGroupId(),
                    key.getName(),
                    value.getHometown(),
                    value.getBirthDate(),
                    value.isHasDormitory(),
                    value.getAverageScore()
            );
        }
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(StudentKey key) {
        checkIsClosed();
        repository.deleteById(getIdByKey(key));
    }

    @Override
    public Iterator<StudentKey> readKeys() {
        checkIsClosed();
        Iterator<StudentEntity> iterator = repository.findAll().iterator();

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public StudentKey next() {
                if (!iterator.hasNext()) {
                    return null;
                }
                StudentEntity nextEntity = iterator.next();
                return new StudentKey(nextEntity.getGroupId(), nextEntity.getName());
            }
        };
    }

    @Override
    public int size() {
        checkIsClosed();
        return (int) repository.count();
    }

    @Override
    public void close() throws IOException {
        checkIsClosed();
        isClosed = true;
    }
}
