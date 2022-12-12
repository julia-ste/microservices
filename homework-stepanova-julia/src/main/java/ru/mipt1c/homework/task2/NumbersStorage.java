package ru.mipt1c.homework.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.mipt1c.homework.task2.exceptions.MalformedDataException;
import ru.mipt1c.homework.task2.models.NumberEntity;
import ru.mipt1c.homework.task2.repositories.IRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Component("numbersStorage")
public class NumbersStorage implements KeyValueStorage<Integer, Double> {
    @Autowired
    @Qualifier("numbersRepository")
    private IRepository<Integer, NumberEntity> repository;
    private boolean isClosed = false;

    private void checkIsClosed() {
        if (isClosed) {
            throw new MalformedDataException("Access to closed database file");
        }
    }

    @Override
    public Double read(Integer key) {
        checkIsClosed();
        return repository.findByKey(key).map(NumberEntity::getValue).orElse(null);
    }

    @Override
    public boolean exists(Integer key) {
        checkIsClosed();
        return repository.existsByKey(key);
    }

    @Override
    public void write(Integer key, Double value) {
        checkIsClosed();
        Optional<NumberEntity> found = repository.findByKey(key);
        NumberEntity entity;
        if (found.isPresent()) {
            entity = found.get();
            entity.setValue(value);
        } else {
            entity = new NumberEntity(key, value);
        }
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Integer key) {
        checkIsClosed();
        repository.deleteByKey(key);
    }

    @Override
    public Iterator<Integer> readKeys() {
        checkIsClosed();
        Iterator<NumberEntity> iterator = repository.findAll().iterator();

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!iterator.hasNext()) {
                    return null;
                }
                return iterator.next().getKey();
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
